package service;

import entity.Lesson;

import java.util.List;

public interface LessonService extends Service{

    Lesson getLessonById(int id);

    Lesson getLessonByName(String name);
    int getMaxCountByLessonId(int lessonId);
    List<Lesson> getLessonsByCourseId(int courseId);
    void addLesson(String name, int maxValue, int courseId, String professor);
    List<Lesson> getAll();
}
