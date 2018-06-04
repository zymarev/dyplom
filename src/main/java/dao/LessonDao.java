package dao;

import entity.Lesson;

import java.util.List;

public interface LessonDao extends Dao{

    Lesson getLessonById(int id);

    Lesson getLessonByName(String name);
    List<Lesson> getAll();
    int getMaxCountByLessonId(int lessonId);
    void addLesson(String name, int maxCount);

    List<Lesson> getLessonsByCourceId(int courseId);
}
