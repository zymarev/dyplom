package command.impl.user;

import command.Command;
import dto.LessonGroupDto;
import dto.LessonUserPair;
import dto.LessonUsersDto;
import entity.Lesson;
import entity.User;
import service.LessonService;
import service.UserService;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetGroupForUserCommand implements Command {

    private UserService userService;
    private LessonService lessonService;

    public GetGroupForUserCommand(UserService userService, LessonService lessonService) {
        this.userService = userService;
        this.lessonService = lessonService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user  = (User)request.getSession().getAttribute("user");
        List<LessonUserPair> pairs = userService.getGroupForUser(user);
        if(pairs.isEmpty()){
            request.setAttribute("group", null);
            return Path.PAGE_WITH_GROUP_FOR_USER;
        }
        LessonUsersDto group = new LessonUsersDto();
        List<User> usersInGroup = new ArrayList<>();
        pairs.forEach(pair->usersInGroup.add(userService.getUserById(pair.getUserId())));
        group.setLesson(lessonService.getLessonById(pairs.get(0).getLessonId()));
        group.setUsers(usersInGroup);
        request.setAttribute("group", group);
        return Path.PAGE_WITH_GROUP_FOR_USER;
    }
}
