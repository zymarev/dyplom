package dao.impl;

import dao.LessonDao;
import db.DbConnector;
import entity.Lesson;
import exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl extends DbConnector implements LessonDao {

    private static final String SQL_GET_LESSON_BY_ID = "SELECT * FROM lessons WHERE id =?";
    private static final String SQL_GET_LESSON_BY_NAME = "SELECT * FROM lessons WHERE name =?";
    private static final String SQL_GET_ALL_LESSONS = "SELECT * FROM lessons";
    private static final String SQL_GET_MAX_COUNT_BY_LESSON_ID = "SELECT max_count FROM lessons WHERE lessons.id = ?";
    private static final String SQL_ADD_LESSON = "INSERT INTO lessons (name, max_count, course_id, professor) VALUES(?, ?, ?, ?)";
    private static final String SQL_GET_LESSONS_BY_COURSE_ID = "SELECT * FROM lessons WHERE course_id = ?";

    @Override
    public Lesson getLessonById(int id) {
        Lesson lesson = new Lesson();
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(SQL_GET_LESSON_BY_ID)){
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
    public Lesson getLessonByName(String name) {
        Lesson lesson = new Lesson();
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_GET_LESSON_BY_NAME)){
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
    public List<Lesson> getAll() {
        List<Lesson> lessons = new ArrayList<>();
        try(Statement statement = dataSource.getConnection().createStatement()){
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
    public int getMaxCountByLessonId(int lessonId) {
        int maxCount=0;
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_GET_MAX_COUNT_BY_LESSON_ID)){
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
    public void addLesson(String name, int maxCount, int courseId, String professor ) {
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_ADD_LESSON)){
            int i=0;
            preparedStatement.setString(++i, name);
            preparedStatement.setInt(++i, maxCount);
            preparedStatement.setInt(++i, courseId);
            preparedStatement.setString(++i, professor);
            preparedStatement.execute();
        }catch (SQLException ex){
            throw new PersistException("Can't add new lesson");
        }
    }

    @Override
    public List<Lesson> getLessonsByCourceId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SQL_GET_LESSONS_BY_COURSE_ID)){
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Lesson lesson = extractLesson(resultSet);
                lessons.add(lesson);
            }
            return lessons;
        }catch (SQLException ex){
            throw new PersistException("Can't get lessons by course id");
        }
    }

    private Lesson extractLesson(ResultSet rs) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(rs.getInt("id"));
        lesson.setProfessor(rs.getString("professor"));
        lesson.setName(rs.getString("name"));
        lesson.setMaxCount(rs.getInt("max_count"));
        return lesson;
    }
}
