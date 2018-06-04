package command.impl.lesson;

import command.Command;
import entity.Lesson;
import service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLessonByIdCommand implements Command {

    private LessonService lessonService;

    public GetLessonByIdCommand(LessonService lessonService){
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Lesson lesson = lessonService.getLessonById(lessonId);
        return "";
    }
}
