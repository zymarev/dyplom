package dao;

import entity.User;

import java.util.List;

public interface UserDao extends Dao{

    User getUserById(int id);
    List<User> getAll();
    boolean addUserToLessonGroup(int userId, int lessonId);
    User findByEmail(String email);

    List<User> getUsersByCourseName(String courseName);
}
