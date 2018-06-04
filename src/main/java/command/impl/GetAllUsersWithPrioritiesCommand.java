package command.impl;

import command.Command;
import dto.UserPriority;
import entity.Priority;
import entity.User;
import service.PriorityService;
import service.UserService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsersWithPrioritiesCommand implements Command {

    private UserService userService;
    private PriorityService priorityService;

    public GetAllUsersWithPrioritiesCommand(UserService userService, PriorityService priorityService){
        this.userService = userService;
        this.priorityService = priorityService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> allUsers = userService.getAll();
        List<UserPriority> usersWithPriority = new ArrayList<>();
        for(User user:allUsers){
            List<Priority> prioritiesByUser = priorityService.getPrioritiesByUserId(user.getId());
            Map<Integer, Integer> priorities = new HashMap<>();
            prioritiesByUser.forEach(priority -> priorities.put(priority.getLessonId(), priority.getUserId()));
            prioritiesByUser.forEach(priority -> usersWithPriority.add(new UserPriority(user.getId(), priorities)));
        }
        request.setAttribute("usersWithPriorities", usersWithPriority);
        return Path.PAGE_USERS_WITH_PRIORITIES;
    }
}
