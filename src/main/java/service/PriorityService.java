package service;

import entity.Priority;

import java.util.List;

public interface PriorityService extends Service {
    List<Priority> getPrioritiesByUserId(int id);
    void setPriorityPerUserByLessonId(int userId, int lessonId, int value);
}
