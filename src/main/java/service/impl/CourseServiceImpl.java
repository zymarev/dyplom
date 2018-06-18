package service.impl;

import dao.CourseDao;
import dto.LessonUserPair;
import entity.Course;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao){
        this.courseDao=courseDao;
    }
    @Override
    public Course getIdByName(String name) {
        return courseDao.getIdByName(name);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

}
