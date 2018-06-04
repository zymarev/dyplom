package command.impl.priority;

import command.Command;
import entity.Priority;
import service.PriorityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetPriorityByUserIdCommand implements Command {
    private PriorityService priorityService;

    public GetPriorityByUserIdCommand(PriorityService priorityService){
        this.priorityService = priorityService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Priority> priorityList = priorityService.getPrioritiesByUserId(userId);
        return "";
    }
}
