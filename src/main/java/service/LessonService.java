package service;

import entity.Lesson;

import java.util.List;

public interface LessonService extends Service{

    Lesson getLessonById(int id);

    Lesson getLessonByName(String name);
    int getMaxCountByLessonId(int lessonId);
    List<Lesson> getLessonsByCourseId(int courseId);
}
