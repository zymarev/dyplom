package service;

import entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserService extends Service{
    User getUserById(int id);
    List<User> getAll();
    List<User> getUsersWithPriorityValueByLessonId(int lessonId, int priorityValue);
    boolean addUserToLessonGroup(int userId, int lessonId);
}
