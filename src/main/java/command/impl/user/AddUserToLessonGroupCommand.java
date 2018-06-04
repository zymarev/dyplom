package command.impl.user;

import command.Command;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserToLessonGroupCommand implements Command {
    private UserService userService;

    public AddUserToLessonGroupCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int lessonId = Integer.parseInt("lessonId");
        userService.addUserToLessonGroup(userId, lessonId);
        return "";
    }
}
