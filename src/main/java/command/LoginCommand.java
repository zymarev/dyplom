package command;

import entity.User;
import service.UserService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.findByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user == null || !user.getPassword().equals(password)) {
            if (user == null || !user.getPassword().equals(password)) {
                request.setAttribute("errorMessage", "Invalid login or password");
            }
            return Path.PAGE_INDEX;
        }
        return Path.PAGE_DASHBOARD;
    }

}

