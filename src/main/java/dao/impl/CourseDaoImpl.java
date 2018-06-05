package dao.impl;

import dao.CourseDao;
import db.DbConnector;
import entity.Course;
import exception.PersistException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl extends DbConnector implements CourseDao {

    private static final String SQL_GET_COURSE_ID_BY_NAME = "SELECT * FROM course WHERE name = ?";
    private static final String SQL_GET_ALL_COURSES = "SELECT * FROM course";

    @Override
    public Course getIdByName(String name) {
        Course course = null;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COURSE_ID_BY_NAME)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                course = exctractCourse(resultSet);
            }
            return course;
        }catch (SQLException ex){
            throw new PersistException("Can't get course by name");
        }
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_COURSES);
            while (resultSet.next()) {
                Course course = exctractCourse(resultSet);
                courses.add(course);
            }
            return courses;
        }catch (SQLException ex){
            throw new PersistException("Can't get all courses");
        }
    }

    private Course exctractCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("id"));
        course.setName(resultSet.getString("name"));
        return course;
    }
}
