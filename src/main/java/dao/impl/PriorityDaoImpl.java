package dao.impl;

import dao.PriorityDao;
import entity.Priority;
import exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriorityDaoImpl implements PriorityDao {

    private static final String SQL_GET_PRIORITIES_BY_USER = "SELECT * FROM priorities WHERE user_id = ?";
    private static final String SQL_ADD_PRIORITY = "INSERT INTO priorities (user_id, lesson_id, value) VALUES(?, ?, ?)";
    private static final String SQL_GET_ALL_PRIORITIES = "SELECT * FROM priorities";
    @Override
    public List<Priority> getPrioritiesByUserId(Connection connection, int id) {
        List<Priority> priorities = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PRIORITIES_BY_USER)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                priorities.addAll(extractPriority(rs));
            }
            return priorities;
        }catch (SQLException ex){
            throw new PersistException("Can't get priorities by user");
        }
    }

    @Override
    public void addPriority(Connection connection, int userId, int lessonId, int value) {
        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL_ADD_PRIORITY)){
            int i=1;
            preparedStatement.setInt(i++, userId);
            preparedStatement.setInt(i++, lessonId);
            preparedStatement.setInt(i++, value);
            preparedStatement.execute();
        }catch (SQLException ex){
            throw new PersistException("Can't add new priority");
        }
    }

    @Override
    public List<Priority> getAll(Connection connection) {
        List<Priority> priorities = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_PRIORITIES)){
            ResultSet resultSet = preparedStatement.executeQuery();
            priorities = extractPriority(resultSet);

            return priorities;
        }catch (SQLException ex){
            throw new PersistException("Can't get all priorities");
        }
    }

    private List<Priority> extractPriority(ResultSet rs) throws SQLException {
        List<Priority> priorities = new ArrayList<>();
        while(rs.next()){
            Priority priority = new Priority();
            priority.setLessonId(rs.getInt("lesson_id"));
            priority.setUserId(rs.getInt("user_id"));
            priority.setValue(rs.getInt("value"));
            priorities.add(priority);
        }
        return priorities;
    }
}
