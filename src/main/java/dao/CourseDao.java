package dao;

import entity.Course;

import java.util.List;

public interface CourseDao extends Dao {

    Course getIdByName(String name);
    List<Course> getAll();
}
