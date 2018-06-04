package service.impl;

import dao.PriorityDao;
import dao.util.TransactionManager;
import entity.Priority;
import service.PriorityService;

import java.util.List;

public class PriorityServiceImpl implements PriorityService {
    private TransactionManager transactionManager;
    private PriorityDao priorityDao;

    public PriorityServiceImpl(TransactionManager transactionManager, PriorityDao priorityDao){
        this.priorityDao = priorityDao;
        this.transactionManager = transactionManager;
    }
    @Override
    public List<Priority> getPrioritiesByUserId(int id) {
        return transactionManager.doInTransaction(connection -> priorityDao.getPrioritiesByUserId(connection, id));
    }
}
