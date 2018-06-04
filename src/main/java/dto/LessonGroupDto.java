package dto;

import entity.User;

import java.util.List;

public class LessonGroupDto {
    private int lessonId;
    private List<User> users;

    public LessonGroupDto() {
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
