package command.impl.lesson;

import command.Command;
import service.CourseService;
import service.LessonService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddLessonCommand implements Command {

    private LessonService lessonService;
    private CourseService courseService;

    public AddLessonCommand(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String professor = request.getParameter("professor");
        String courseName = request.getParameter("courseName");
        int maxValue = Integer.parseInt(request.getParameter("maxValue"));
        String lessonName = request.getParameter("lessonName");
        int courseId = courseService.getIdByName(courseName).getId();
        lessonService.addLesson(lessonName, maxValue, courseId, professor);
        return Path.PAGE_LESSONS_BY_COURSE;
        ////////
    }
}