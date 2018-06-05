package service;

import entity.Course;

import java.util.List;

public interface CourseService extends Service {

    Course getIdByName(String name);
    List<Course> getAll();
}
