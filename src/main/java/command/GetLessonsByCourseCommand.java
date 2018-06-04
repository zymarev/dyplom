package command;

import entity.Lesson;
import entity.Priority;
import entity.User;
import service.LessonService;
import service.PriorityService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetLessonsByCourseCommand implements Command {

    private LessonService lessonService;
    private PriorityService priorityService;

    public GetLessonsByCourseCommand(LessonService lessonService, PriorityService priorityService) {
        this.lessonService = lessonService;
        this.priorityService = priorityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User)request.getSession().getAttribute("user");
        List<Priority> priorities = priorityService.getPrioritiesByUserId(user.getId());
        if(priorities.size()!=0){
            return Path.PAGE_USERS_SUCCESS_PRIORITY;
        }
        List<Lesson> lessonList = lessonService.getLessonsByCourseId(user.getCourseId());
        request.getSession().setAttribute("userLessons", lessonList);
        return Path.PAGE_USERS_LESSONS;
    }
}
