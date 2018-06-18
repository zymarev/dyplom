package dao.impl;

import dao.UserDao;
import db.DbConnector;
import dto.LessonGroupDto;
import dto.LessonUserPair;
import entity.Role;
import entity.User;
import exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DbConnector implements UserDao{

    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_ADD_USER_TO_LESSON_GROUP = "INSERT INTO group_lesson (user_id, lesson_id) VALUES (?, ?)";
    private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_GET_USERS_BY_COURSE_NAME = "SELECT users.id, users.course_id, users.first_name, users.last_name, users.email, users.password, users.average_mark, users.role, course.name FROM users, course WHERE users.course_id=course.id AND course.name = ?";
    private static final String SQL_GET_GROUP_FOR_USER = "SELECT * FROM group_lesson WHERE group_lesson.lesson_id = (SELECT group_lesson.lesson_id FROM group_lesson WHERE group_lesson.user_id = ?)";

    @Override
    public boolean addUserToLessonGroup(int userId, int lessonId) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER_TO_LESSON_GROUP)){
            int i=0;
            preparedStatement.setInt(++i, userId);
            preparedStatement.setInt(++i, lessonId);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        }catch(SQLException ex){
            throw new PersistException("Can't add user to lesson group");
        }
    }

    @Override
    public User findByEmail(String email) {
        User user=null;
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_GET_USER_BY_EMAIL)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = extractUser(resultSet);
            }
            return user;
        }catch(SQLException ex){
            throw new PersistException("Can't get user by email");
        }

    }

    @Override
    public List<User> getUsersByCourseName(String courseName) {
        List<User> users = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USERS_BY_COURSE_NAME)){
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = extractUser(resultSet);
                users.add(user);
            }
            return users;
        }catch (SQLException ex){
            throw new PersistException("Can't get users by course name");
        }
    }

    @Override
    public List<LessonUserPair> getGroupForUser(User user) {
        List<LessonUserPair> group = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_GROUP_FOR_USER)){
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LessonUserPair pair = new LessonUserPair();
                pair.setLessonId(resultSet.getInt("lesson_id"));
                pair.setUserId(resultSet.getInt("user_id"));
                group.add(pair);
            }
        }catch(SQLException ex){
            throw new PersistException("can't get group for user");
        }
        return group;
    }

    @Override
    public User getUserById(int id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL_GET_USER_BY_ID)){
            User user = new User();
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = extractUser(rs);
            }
            return user;
        }catch (SQLException ex){
            throw new PersistException("Can't get user by id from db");
        }

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_USERS);
            while(rs.next()){
                User user = extractUser(rs);
                if(user.getLastName().equals("admin")){
                    continue;
                }
                users.add(user);
            }
            return users;
        }catch (SQLException ex){
            throw new PersistException("Can't get all users");
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAvgMark(rs.getDouble("average_mark"));
        user.setEmail(rs.getString("email"));
        user.setRole(Role.of(rs.getInt("role")));
        user.setCourseId(rs.getInt("course_id"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
