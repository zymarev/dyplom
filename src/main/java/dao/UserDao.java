package dao;

import entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao extends Dao{

    User getUserById(Connection connection, int id);
    List<User> getAll(Connection connection);
    List<User> getUsersWithPriorityValueByLessonId(Connection connection, int lessonId, int priorityValue);
    boolean addUserToLessonGroup(Connection connection, int userId, int lessonId);
}
