package dto;

import entity.Lesson;

public class PriorityLessonDto {
    private Lesson lesson;
    private int value;

    public PriorityLessonDto() {
    }

    public PriorityLessonDto(Lesson lesson, int value) {
        this.lesson = lesson;
        this.value = value;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
