package command.impl.lesson;

import command.Command;
import dto.UserPriority;
import service.impl.GroupFormer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FormGroupCommand implements Command {

    private GroupFormer groupFormer;

    public FormGroupCommand(GroupFormer groupFormer){
        this.groupFormer = groupFormer;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<UserPriority> list = groupFormer.formGroups();
        list.forEach(item-> System.out.println(item.getUserId()+ " ->" + item.getPriorities().toString()));
        return "";
    }
}
