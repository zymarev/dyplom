package dao.impl;

import dao.PriorityDao;
import db.DbConnector;
import entity.Priority;
import exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriorityDaoImpl extends DbConnector implements PriorityDao {

    private static final String SQL_GET_PRIORITIES_BY_USER = "SELECT * FROM priorities WHERE user_id = ?";
    private static final String SQL_ADD_PRIORITY = "INSERT INTO priorities (user_id, lesson_id, value) VALUES(?, ?, ?)";
    private static final String SQL_GET_ALL_PRIORITIES = "SELECT * FROM priorities";
    private static final String SQL_SET_PRIORITY = "INSERT INTO priorities (user_id, lesson_id, value) VALUES (?, ?, ?)";

    @Override
    public List<Priority> getPrioritiesByUserId(int id) {
        List<Priority> priorities = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_GET_PRIORITIES_BY_USER)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                priorities.add(extractPriority(rs));
            }
            return priorities;
        } catch (SQLException ex) {
            throw new PersistException("Can't get priorities by user");
        }
    }

    @Override
    public void addPriority(int userId, int lessonId, int value) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_ADD_PRIORITY)) {
            int i = 1;
            preparedStatement.setInt(i++, userId);
            preparedStatement.setInt(i++, lessonId);
            preparedStatement.setInt(i++, value);
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new PersistException("Can't add new priority");
        }
    }

    @Override
    public List<Priority> getAll() {
        List<Priority> priorities = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_GET_ALL_PRIORITIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Priority priority = extractPriority(resultSet);
                priorities.add(priority);
            }
            return priorities;
        } catch (SQLException ex) {
            throw new PersistException("Can't get all priorities");
        }
    }

    @Override
    public void setPriorityPerUserByLessonId(int userId, int lessonId, int value) {
        try(Connection con = dataSource.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SQL_SET_PRIORITY)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, lessonId);
            preparedStatement.setInt(3, value);
            preparedStatement.executeUpdate();
            con.commit();
        }catch (SQLException ex){
            throw new PersistException("Can't set priority");
        }
    }

    private Priority extractPriority(ResultSet rs) throws SQLException {

        Priority priority = new Priority();
        priority.setLessonId(rs.getInt("lesson_id"));
        priority.setUserId(rs.getInt("user_id"));
        priority.setValue(rs.getInt("value"));

        return priority;
    }
}
