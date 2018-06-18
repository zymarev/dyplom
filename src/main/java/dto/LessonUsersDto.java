package dto;

import entity.Lesson;
import entity.User;

import java.util.List;

public class LessonUsersDto {
    private Lesson lesson;
    private List<User> users;

    public LessonUsersDto() {
    }

    public LessonUsersDto(Lesson lesson, List<User> users) {
        this.lesson = lesson;
        this.users = users;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
