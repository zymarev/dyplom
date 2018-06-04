package dao;

import entity.Lesson;

import java.sql.Connection;
import java.util.List;

public interface LessonDao extends Dao{

    Lesson getLessonById(Connection connection, int id);

    Lesson getLessonByName(Connection connection, String name);
    List<Lesson> getAll(Connection connection);
    int getMaxCountByLessonId(Connection connection, int lessonId);
    void addLesson(Connection connection, String name, int maxCount);
}
