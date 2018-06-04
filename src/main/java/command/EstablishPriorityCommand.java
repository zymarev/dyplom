package command;

import command.Command;
import entity.Lesson;
import entity.User;
import service.LessonService;
import service.PriorityService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EstablishPriorityCommand implements Command {

    private PriorityService priorityService;

    public EstablishPriorityCommand(PriorityService priorityService){
        this.priorityService = priorityService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user  = (User)request.getSession().getAttribute("user");
        List<Lesson> lessonList = (List<Lesson>)request.getSession().getAttribute("userLessons");
        for(Lesson lesson:lessonList){
            int priority = Integer.parseInt(request.getParameter(lesson.getId()+""));
            priorityService.setPriorityPerUserByLessonId(user.getId(), lesson.getId(), priority);
        }
        return Path.PAGE_USERS_SUCCESS_PRIORITY;
    }
}
