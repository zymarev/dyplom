package dao;

import entity.Priority;

import java.util.List;

public interface PriorityDao extends Dao{

    List<Priority> getPrioritiesByUserId(int id);
    void addPriority(int userId, int lessonId, int value);
    List<Priority> getAll();

    void setPriorityPerUserByLessonId(int userId, int lessonId, int value);
}
