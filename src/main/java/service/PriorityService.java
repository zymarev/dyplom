package service;

import entity.Priority;

import java.sql.Connection;
import java.util.List;

public interface PriorityService extends Service {
    List<Priority> getPrioritiesByUserId(int id);
}
