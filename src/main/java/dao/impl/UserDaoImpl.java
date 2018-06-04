package dao.impl;

import dao.UserDao;
import entity.User;
import exception.PersistException;

import javax.tools.StandardJavaFileManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_GET_USERS_WITH_PRIORITY_VALUE_BY_LESSON = "SELECT priorities.lesson_id, priorities.value, users.id, users.first_name, users.last_name, users.average_mark FROM users, priorities WHERE users.id = priorities.user_id and priorities.lesson_id = ? and priorities.value = ?";
private static final String SQL_ADD_USER_TO_LESSON_GROUP = "INSERT INTO group_lesson (user_id, lesson_id) VALUES (?, ?)";
    public List<User> getUsersWithPriorityValueByLessonId(Connection connection, int lessonId, int priorityValue){
        try(PreparedStatement pstmt = connection.prepareStatement(SQL_GET_USERS_WITH_PRIORITY_VALUE_BY_LESSON)){
            List<User> users = new ArrayList<>();
            int i=1;
            pstmt.setInt(i++, lessonId);
            pstmt.setInt(i++, priorityValue);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                User user = extractUser(resultSet);
                users.add(user);
            }
            return users;
        }catch (SQLException ex){
            throw new PersistException("Can't get users with priority value by lesson id");
        }
    }

    @Override
    public boolean addUserToLessonGroup(Connection connection, int userId, int lessonId) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER_TO_LESSON_GROUP)){
            int i=0;
            preparedStatement.setInt(++i, userId);
            preparedStatement.setInt(++i, lessonId);
            preparedStatement.execute();
            return true;
        }catch(SQLException ex){
            throw new PersistException("Can't add user to lesson group");
        }
    }

    @Override
    public User getUserById(Connection connection, int id) {
        try(PreparedStatement pstmt = connection.prepareStatement(SQL_GET_USER_BY_ID)){
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
    public List<User> getAll(Connection connection) {
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_USERS);
            while(rs.next()){
                User user = extractUser(rs);
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
        return user;
    }
}
