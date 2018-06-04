package service.impl;

import dao.UserDao;
import entity.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    @Override
    public List<User> getAll(){
        return userDao.getAll();
    }
    @Override
    public List<User> getUsersWithPriorityValueByLessonId(int lessonId, int priorityValue){
        return userDao.getUsersWithPriorityValueByLessonId(lessonId, priorityValue);
    }

    @Override
    public boolean addUserToLessonGroup(int userId, int lessonId) {
        return userDao.addUserToLessonGroup(userId, lessonId);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getUsersByCourseName(String courseName) {
        return userDao.getUsersByCourseName(courseName);
    }
}
