package service;

import dto.LessonGroupDto;
import dto.LessonUserPair;
import entity.User;

import java.util.List;

public interface UserService extends Service{
    User getUserById(int id);
    List<User> getAll();
    boolean addUserToLessonGroup(int userId, int lessonId);
    User findByEmail(String email);

    List<User> getUsersByCourseName(String courseName);

    List<LessonUserPair> getGroupForUser(User user);
}
