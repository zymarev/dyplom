package dao;

import entity.Priority;

import java.sql.Connection;
import java.util.List;

public interface PriorityDao extends Dao{

    List<Priority> getPrioritiesByUserId(Connection connection, int id);
    void addPriority(Connection connection, int userId, int lessonId, int value);
    List<Priority> getAll(Connection connection);
}
