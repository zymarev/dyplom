package command.impl;

import command.Command;
import entity.User;
import service.UserService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsersByCourseCommand implements Command {

    private UserService userService;

    public GetUsersByCourseCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String courseName = request.getParameter("courseName");
        List<User> users = userService.getUsersByCourseName(courseName);
        request.setAttribute("userByCourse", users);
        return Path.PAGE_USERS_BY_COURSE;
    }
}
