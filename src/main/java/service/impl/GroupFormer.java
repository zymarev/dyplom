package service.impl;

import dao.LessonDao;
import dao.PriorityDao;
import dao.UserDao;
import dao.util.TransactionManager;
import dto.UserPriority;
import entity.Lesson;
import entity.Priority;
import entity.User;
import service.LessonService;
import service.Service;
import service.UserService;

import java.util.*;
import java.util.stream.Collectors;

public class GroupFormer implements Service {
    private TransactionManager transactionManager;
    private UserDao userDao;
    private LessonDao lessonDao;
    private PriorityDao priorityDao;
    private List<User> users;
    private List<Priority> priorities;
    private List<Lesson> lessons;
    private Map<Integer, User[]> groups;

    public GroupFormer(TransactionManager transactionManager, UserDao userDao, LessonDao lessonDao, PriorityDao priorityDao){
        this.lessonDao = lessonDao;
        this.priorityDao = priorityDao;
        this.userDao = userDao;
        this.transactionManager = transactionManager;
        init();
    }

    private void init() {
        users = new ArrayList<>();
        lessons = new ArrayList<>();
        priorities = new ArrayList<>();
        //users = userDao.getAll();
                //transactionManager.doInTransaction(connection -> userDao.getAll(connection));
        lessons = transactionManager.doInTransaction(connection -> lessonDao.getAll(connection));
        priorities = transactionManager.doInTransaction(connection -> priorityDao.getAll(connection));
    }
    public List<UserPriority> formGroups(){
        List<UserPriority> userPriorities = new ArrayList<>();
        List<User> users = transactionManager.doInTransaction(connection->userDao.getAll(connection));
        for(User user :users){
            Map<Integer, Integer> prioritiesPerUser = new HashMap<>();
            transactionManager.doInTransaction(connection->priorityDao.getPrioritiesByUserId(connection, user.getId())).forEach((priority->prioritiesPerUser.put(priority.getLessonId(), priority.getValue())));
            userPriorities.add(new UserPriority(user.getId(), prioritiesPerUser));
        }
        return userPriorities;



    }
}
