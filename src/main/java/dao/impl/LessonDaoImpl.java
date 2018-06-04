package dao.impl;

import dao.LessonDao;
import entity.Lesson;
import exception.PersistException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {

    private static final String SQL_GET_LESSON_BY_ID = "SELECT * FROM lessons WHERE id =?";
    private static final String SQL_GET_LESSON_BY_NAME = "SELECT * FROM lessons WHERE name =?";
    private static final String SQL_GET_ALL_LESSONS = "SELECT * FROM lessons";
    private static final String SQL_GET_MAX_COUNT_BY_LESSON_ID = "SELECT max_count FROM lessons WHERE lessons.id = ?";
    private static final String SQL_ADD_LESSON = "INSERT INTO lessons (name, max_count) VALUES(?, ?)";
    @Override
    public Lesson getLessonById(Connection connection, int id) {
        Lesson lesson = new Lesson();
        try(PreparedStatement pstmt = connection.prepareStatement(SQL_GET_LESSON_BY_ID)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lesson = extractLesson(rs);
            }
            return lesson;
        }catch(SQLException ex){
            throw new PersistException("Can't get lesson by id");
        }
    }

    @Override
    public Lesson getLessonByName(Connection connection, String name) {
        Lesson lesson = new Lesson();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LESSON_BY_NAME)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                lesson = extractLesson(resultSet);
            }
            return lesson;
        }catch (SQLException ex){
            throw new PersistException("Can't get lesson by name");
        }
    }

    @Override
    public List<Lesson> getAll(Connection connection) {
        List<Lesson> lessons = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_LESSONS);
            while(rs.next()){
                Lesson lesson = extractLesson(rs);
                lessons.add(lesson);
            }
            return lessons;
        }catch (SQLException ex){
            throw new PersistException("Can't get all lessons");
        }
    }

    @Override
    public int getMaxCountByLessonId(Connection connection, int lessonId) {
        int maxCount=0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MAX_COUNT_BY_LESSON_ID)){
            preparedStatement.setInt(1, lessonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                maxCount = resultSet.getInt("max_count");
            }
            return maxCount;
        }catch(SQLException ex){
            throw new PersistException("Can't get max count by lesson id");
        }
    }

    @Override
    public void addLesson(Connection connection, String name, int maxCount) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_LESSON)){
            int i=0;
            preparedStatement.setString(++i, name);
            preparedStatement.setInt(++i, maxCount);
            preparedStatement.execute();
        }catch (SQLException ex){
            throw new PersistException("Can't add new lesson");
        }
    }

    private Lesson extractLesson(ResultSet rs) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setName(rs.getString("name"));
        lesson.setMaxCount(rs.getInt("max_count"));
        return lesson;
    }
}
