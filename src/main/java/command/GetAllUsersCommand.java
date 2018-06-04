package command;

import entity.User;
import service.UserService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUsersCommand implements Command {

    private UserService userService;

    public GetAllUsersCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> allUsers = userService.getAll();
        request.setAttribute("allUsers", allUsers);
        return Path.PAGE_ALL_USERS;
    }
}
