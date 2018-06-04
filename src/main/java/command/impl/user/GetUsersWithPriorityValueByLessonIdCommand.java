package command.impl.user;

import command.Command;
import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetUsersWithPriorityValueByLessonIdCommand implements Command {
    private UserService userService;

    public GetUsersWithPriorityValueByLessonIdCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        int value = Integer.parseInt(request.getParameter("value"));
        List<User> users = userService.getUsersWithPriorityValueByLessonId(lessonId, value);
        return "";
    }
}
