package dto;

import java.util.HashMap;
import java.util.Map;

public class UserPriority {
    private int userId;
    private Map<Integer, Integer> priorities;

    public UserPriority() {
        priorities = new HashMap<>();
    }

    public UserPriority(int userId, Map<Integer, Integer> priorities) {
        this.userId = userId;
        this.priorities = priorities;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<Integer, Integer> getPriorities() {
        return priorities;
    }

    public void setPriorities(Map<Integer, Integer> priorities) {
        this.priorities = priorities;
    }
}
