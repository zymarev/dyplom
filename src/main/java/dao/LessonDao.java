package dao;

import dto.LessonUserPair;
import entity.Lesson;

import java.util.List;

public interface LessonDao extends Dao{

    Lesson getLessonById(int id);

    Lesson getLessonByName(String name);
    List<Lesson> getAll();
    int getMaxCountByLessonId(int lessonId);
    void addLesson(String name, int maxCount, int courseId, String professor);
    List<LessonUserPair> getAllGroupsByCourseId(int courseId);

    List<Lesson> getLessonsByCourceId(int courseId);
}
