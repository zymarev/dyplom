package service.impl;

import dao.LessonDao;
import dao.util.TransactionManager;
import entity.Lesson;
import service.LessonService;

public class LessonServiceImpl implements LessonService {

    private TransactionManager transactionManager;
    private LessonDao lessonDao;
    public LessonServiceImpl(TransactionManager transactionManager, LessonDao lessonDao){
        this.transactionManager =transactionManager;
        this.lessonDao =lessonDao;
    }
    @Override
    public Lesson getLessonById(int id) {
        return transactionManager.doInTransaction(connection->lessonDao.getLessonById(connection, id));
    }

    @Override
    public Lesson getLessonByName(String name) {
        return transactionManager.doInTransaction(connection -> lessonDao.getLessonByName(connection, name));
    }

    @Override
    public int getMaxCountByLessonId(int lessonId) {
        return transactionManager.doInTransaction(connection->lessonDao.getMaxCountByLessonId(connection, lessonId));
    }
}
