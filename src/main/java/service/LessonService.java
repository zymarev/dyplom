package service;

import entity.Lesson;

public interface LessonService extends Service{

    Lesson getLessonById(int id);

    Lesson getLessonByName(String name);
    int getMaxCountByLessonId(int lessonId);
}
