package service;

import entity.User;

import java.util.List;

public interface UserService extends Service{
    User getUserById(int id);
    List<User> getAll();
    List<User> getUsersWithPriorityValueByLessonId(int lessonId, int priorityValue);
    boolean addUserToLessonGroup(int userId, int lessonId);
    User findByEmail(String email);

    List<User> getUsersByCourseName(String courseName);
}
