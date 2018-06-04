package command.impl.user;

import command.Command;
import entity.User;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserByIdCommand extends HttpServlet implements Command {

    private static final String USER_SERVICE = "userService";
    private UserService userService;

    public GetUserByIdCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        return "";
    }

}
