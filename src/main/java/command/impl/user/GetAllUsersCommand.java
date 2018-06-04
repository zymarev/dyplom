package command.impl.user;

import command.Command;
import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUsersCommand implements Command {
    private UserService userService;

    public GetAllUsersCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers = userService.getAll();
        return "";
    }
}
