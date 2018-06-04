package command.impl.user;

import command.Command;
import entity.User;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
