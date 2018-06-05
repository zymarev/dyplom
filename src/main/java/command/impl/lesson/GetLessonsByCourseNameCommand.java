package command.impl.lesson;

import command.Command;
import entity.Lesson;
import service.CourseService;
import service.LessonService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetLessonsByCourseNameCommand implements Command {

    private LessonService lessonService;
    private CourseService courseService;

    public GetLessonsByCourseNameCommand(LessonService lessonService, CourseService courseService){
        this.courseService = courseService;
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String courseName = request.getParameter("courseName");
        int courseId = courseService.getIdByName(courseName).getId();
        List<Lesson> lessonsByCourseName = lessonService.getLessonsByCourseId(courseId);
        request.setAttribute("lessonsByCourseName", lessonsByCourseName);
        return Path.PAGE_LESSONS_BY_COURSE;
    }
}
