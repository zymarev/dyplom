package service.impl;

import dao.UserDao;
import dao.util.TransactionManager;
import entity.User;
import service.UserService;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

    private TransactionManager transactionManager;
    private UserDao userDao;

    public UserServiceImpl(TransactionManager transactionManager, UserDao userDao){
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }
    @Override
    public User getUserById(int id) {
        return transactionManager.doInTransaction(connection->userDao.getUserById(connection, id));
    }
    @Override
    public List<User> getAll(){
        return transactionManager.doInTransaction(connection ->userDao.getAll(connection));
    }
    @Override
    public List<User> getUsersWithPriorityValueByLessonId(int lessonId, int priorityValue){
        return transactionManager.doInTransaction(connection->userDao.getUsersWithPriorityValueByLessonId(connection, lessonId, priorityValue));
    }

    @Override
    public boolean addUserToLessonGroup(int userId, int lessonId) {
        return transactionManager.doInTransaction(connection -> userDao.addUserToLessonGroup(connection, userId, lessonId));
    }
}
