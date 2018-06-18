package service.impl;

import dao.CourseDao;
import dao.LessonDao;
import dao.PriorityDao;
import dao.UserDao;
import dto.LessonUsersDto;
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


    public Map<Integer, User[]> formGroups(String courseName) {

        Course course = courseDao.getIdByName(courseName);
        for (Lesson lesson : lessonDao.getLessonsByCourceId(course.getId())) {
            groups.put(lesson.getId(), new User[lesson.getMaxCount()]);
        }
        clearTempGoups(course.getId());
        int counter = 1;
        List<User> leftUsers = userDao.getUsersByCourseName(courseName);
        while (counter <= lessonDao.getLessonsByCourceId(1).size() || leftUsers.size() != 0) {
            clearTempGoups(course.getId());
            for (User user : leftUsers) {
                usersPerLesson.get(getLessonIdMaxPriorityByUser(counter, user)).add(user);
            }
            leftUsers = new ArrayList<>();
            usersPerLesson.forEach((key, value) -> value.sort((o1, o2) -> (int) (o2.getAvgMark() - o1.getAvgMark())));
            List<LessonUsersDto> lessonUsersDtos = new ArrayList<>();
            usersPerLesson.forEach((key, value) -> lessonUsersDtos.add(new LessonUsersDto(lessonDao.getLessonById(key), value)));
            for (LessonUsersDto lessonUsersDto : lessonUsersDtos) {
                if (lessonUsersDto.getLesson().getMaxCount() <= lessonUsersDto.getUsers().size()) {
                    for (int i = 0; i < groups.get(lessonUsersDto.getLesson().getId()).length; i++) {
                        groups.get(lessonUsersDto.getLesson().getId())[i] = lessonUsersDto.getUsers().get(i);
                    }
                    leftUsers.addAll(lessonUsersDto.getUsers().subList(lessonUsersDto.getLesson().getMaxCount(), lessonUsersDto.getUsers().size()));
                }
            }
            counter++;
        }
        groups.forEach((key,value)-> {
            for(User user:value){
                userDao.addUserToLessonGroup(user.getId(), key);
            }
        });
        return groups;
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

    private int getUsersWithPriorityOneToLesson(int lessonId) {
        List<Priority> priorities = priorityDao.getAll();
        List<Integer> usersPerLessonWithPriorityOne = new ArrayList<>();
        priorities.forEach(priority -> {
            if (priority.getValue() == 1 && priority.getLessonId() == lessonId) {
                usersPerLessonWithPriorityOne.add(priority.getUserId());
            }
        });
        return usersPerLessonWithPriorityOne.size();
    }
}
