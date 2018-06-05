package service.impl;

import dao.LessonDao;
import entity.Lesson;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {


    private LessonDao lessonDao;
    public LessonServiceImpl(LessonDao lessonDao){

        this.lessonDao =lessonDao;
    }
    @Override
    public Lesson getLessonById(int id) {
        return lessonDao.getLessonById(id);
    }

    @Override
    public Lesson getLessonByName(String name) {
        return lessonDao.getLessonByName(name);
    }

    @Override
    public int getMaxCountByLessonId(int lessonId) {
        return lessonDao.getMaxCountByLessonId(lessonId);
    }

    @Override
    public List<Lesson> getLessonsByCourseId(int courseId) {
        return lessonDao.getLessonsByCourceId(courseId);
    }

    @Override
    public void addLesson(String name, int maxValue, int courseId, String professor) {
        lessonDao.addLesson(name, maxValue, courseId, professor);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonDao.getAll();
    }
}
