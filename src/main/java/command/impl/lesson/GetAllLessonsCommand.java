package command.impl.lesson;

import command.Command;
import entity.Lesson;
import service.LessonService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllLessonsCommand implements Command {
    private LessonService lessonService;

    public GetAllLessonsCommand(LessonService lessonService){
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Lesson> allLessons = lessonService.getAll();
        request.setAttribute("allLessons", allLessons);
        return Path.PAGE_ALL_LESSONS;
    }
}
