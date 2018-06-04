package command.impl.lesson;

import command.Command;
import service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMaxCountByLessonIdCommand implements Command {
    private LessonService lessonService;

    public GetMaxCountByLessonIdCommand(LessonService lessonService){
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        int maxCount = lessonService.getMaxCountByLessonId(lessonId);
        return "";
    }
}
