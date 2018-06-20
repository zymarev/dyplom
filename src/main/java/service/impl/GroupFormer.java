package service.impl;

import dao.CourseDao;
import dao.LessonDao;
import dao.PriorityDao;
import dao.UserDao;
import entity.Course;
import entity.Lesson;
import entity.Priority;
import entity.User;
import service.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupFormer implements Service {

    private UserDao userDao;
    private LessonDao lessonDao;
    private PriorityDao priorityDao;
    private CourseDao courseDao;
    private Map<Integer, User[]> groups = new HashMap<>();
    private Map<Integer, List<User>> usersPerLesson = new HashMap<>();

    public GroupFormer(UserDao userDao, LessonDao lessonDao, PriorityDao priorityDao, CourseDao courseDao) {
        this.lessonDao = lessonDao;
        this.priorityDao = priorityDao;
        this.userDao = userDao;
        this.courseDao = courseDao;

    }

    private void AddUserToGroup(User user, int priorityValue) {
        int lessonId = getLessonIdMaxPriorityByUser(priorityValue, user);
        boolean flag = true;
        while (flag) {
            usersPerLesson.get(lessonId).add(user);
            usersPerLesson.get(lessonId).sort((user1, user2) -> (int) (user2.getAvgMark() - user1.getAvgMark()));
            if (usersPerLesson.get(lessonId).size() > lessonDao.getMaxCountByLessonId(lessonId)) {
                User tempUser = usersPerLesson.get(lessonId).get(usersPerLesson.get(lessonId).size() - 1);
                usersPerLesson.get(lessonId).remove(usersPerLesson.get(lessonId).size() - 1);
                List<Priority> prioritiesForTempUser = priorityDao.getPrioritiesByUserId(tempUser.getId());
                for (Priority priority : prioritiesForTempUser) {
                    if (priority.getLessonId() == lessonId) {
                        priorityValue = priority.getValue();
                        break;
                    }
                }
                AddUserToGroup(tempUser, ++priorityValue);
            }
            return;
        }
    }

    public Map<Integer, User[]> formGroups(String courseName) {

        Course course = courseDao.getIdByName(courseName);
        List<User> allUsersForGroup = userDao.getUsersByCourseName(courseName);
        clearTempGoups(course.getId());
        for (User user : allUsersForGroup) {
            boolean flag = false;
            for (Lesson lesson : lessonDao.getLessonsByCourceId(course.getId())) {
                if (checkUserInGroup(lesson.getId(), user)) {
                    flag = true;
                }
            }
            if (!flag) {
                AddUserToGroup(user, 1);
            }
        }
        usersPerLesson.forEach((lessonId, users) -> groups.put(lessonId, users.toArray(new User[0])));
        writeToDb();
        return groups;
    }
    private void writeToDb(){
        groups.forEach((key, value) -> {
            for (User user : value) {
                userDao.addUserToLessonGroup(user.getId(), key);}});
    }
    private boolean checkUserInGroup(int lessonId, User user) {
        return usersPerLesson.get(lessonId).contains(user);
    }

    private void clearTempGoups(int courseId) {
        for (Lesson lesson : lessonDao.getLessonsByCourceId(courseId)) {
            usersPerLesson.put(lesson.getId(), new ArrayList<>());

        }
    }

    private int getLessonIdMaxPriorityByUser(int priorityValue, User user) {
        List<Priority> prioritiesByUser = priorityDao.getPrioritiesByUserId(user.getId());
        for (Priority priority : prioritiesByUser) {
            if (priority.getValue() == priorityValue) {
                return priority.getLessonId();
            }
        }
        return -1;
    }
}
