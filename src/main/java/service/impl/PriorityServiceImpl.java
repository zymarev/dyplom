package service.impl;

import dao.PriorityDao;
import entity.Priority;
import service.PriorityService;

import java.util.List;

public class PriorityServiceImpl implements PriorityService {
    private PriorityDao priorityDao;

    public PriorityServiceImpl(PriorityDao priorityDao){
        this.priorityDao = priorityDao;

    }
    @Override
    public List<Priority> getPrioritiesByUserId(int id) {
        return priorityDao.getPrioritiesByUserId(id);
    }

    @Override
    public void setPriorityPerUserByLessonId(int userId, int lessonId, int value) {
        priorityDao.setPriorityPerUserByLessonId(userId, lessonId, value);
    }
}
