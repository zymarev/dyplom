package service.impl;

import dao.LessonDao;
import dao.PriorityDao;
import dao.UserDao;
import dto.UserPriority;
import entity.Lesson;
import entity.Priority;
import entity.User;
import service.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupFormer implements Service {

    private UserDao userDao;
    private LessonDao lessonDao;
    private PriorityDao priorityDao;
    private Map<Integer, User[]> groups = new HashMap<>();
    private Map<Integer, List<User>> usersPerLesson = new HashMap<>();

    public GroupFormer(UserDao userDao, LessonDao lessonDao, PriorityDao priorityDao){
        /*this.lessonDao = lessonDao;
        this.priorityDao = priorityDao;
        this.userDao = userDao;
        for(Lesson lesson:lessonDao.getAll()){
            List<User> users = userDao.getUsersWithPriorityValueByLessonId(lesson.getId(), 1);
            usersPerLesson.put(lesson.getId(), users);
            groups.put(lesson.getId(), new User[lesson.getMaxCount()]);
        }*/
    }

    public List<UserPriority> formGroups(){

        usersPerLesson.forEach((key, value)-> value.stream().sorted((o1, o2) -> (o1.getAvgMark()>o2.getAvgMark())?1:0));

        /////////////////////
        List<UserPriority> userPriorities = new ArrayList<>();
        List<User> users = userDao.getAll();
        for(User user :users){
            Map<Integer, Integer> prioritiesPerUser = new HashMap<>();
            priorityDao.getPrioritiesByUserId(user.getId()).forEach((priority->prioritiesPerUser.put(priority.getLessonId(), priority.getValue())));
            userPriorities.add(new UserPriority(user.getId(), prioritiesPerUser));
        }
        for(Lesson lesson:lessonDao.getAll()){
            List<User> usersForLesson = userDao.getUsersWithPriorityValueByLessonId(lesson.getId(), 1);
            usersForLesson.stream().sorted((o1, o2) -> (o1.getAvgMark()>o2.getAvgMark())?1:0);
            User[] variable1 = groups.get(lesson.getId());
            int restIndex =0;
            for(int i=0;i<variable1.length;i++){
                variable1[i] = usersForLesson.get(i);
                restIndex=i;
            }
            usersForLesson = usersForLesson.subList(restIndex, usersForLesson.size()-1);

        }

        return userPriorities;



    }
}
