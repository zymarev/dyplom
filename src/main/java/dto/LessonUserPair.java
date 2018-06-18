package dto;

public class LessonUserPair {
    private int userId;
    private int lessonId;

    public LessonUserPair() {
    }

    public LessonUserPair(int userId, int lessonId) {
        this.userId = userId;
        this.lessonId = lessonId;
    }

    public int getUserId() {
        return userId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
}
