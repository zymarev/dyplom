package command.impl.lesson;

import command.Command;
import service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLessonByNameCommand implements Command {

    private LessonService lessonService;

    public GetLessonByNameCommand(LessonService lessonService){
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lessonName = request.getParameter("lessonName");
        lessonService.getLessonByName(lessonName);
        return "";
    }
}
