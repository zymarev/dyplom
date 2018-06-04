package command;

import dto.PriorityLessonDto;
import entity.Priority;
import entity.User;
import service.LessonService;
import service.PriorityService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPrioritiesByUserCommand implements Command {

    private PriorityService priorityService;
    private LessonService lessonService;

    public GetPrioritiesByUserCommand(PriorityService priorityService, LessonService lessonService){
        this.priorityService = priorityService;
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User)request.getSession().getAttribute("user");
        List<Priority> priorities = priorityService.getPrioritiesByUserId(user.getId());
        List<PriorityLessonDto> priorityLessonDtoList = new ArrayList<>();
        priorities.forEach(priority -> priorityLessonDtoList.add(new PriorityLessonDto(lessonService.getLessonById(priority.getLessonId()), priority.getValue())));
        request.setAttribute("userPriorities", priorityLessonDtoList);
        return Path.PAGE_USERS_PRIORITIES;
    }
}
