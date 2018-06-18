package command.impl.lesson;

import command.Command;
import dto.LessonUserPair;
import dto.LessonUsersDto;
import entity.User;
import service.CourseService;
import service.LessonService;
import service.UserService;
import service.impl.GroupFormer;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class GetGroupsCommand implements Command {
    CourseService courseService;
    LessonService lessonService;
    UserService userService;

    public GetGroupsCommand(CourseService courseService, LessonService lessonService, UserService userService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String courseName = request.getParameter("courseNameForGroups");
        int courseId = courseService.getIdByName(courseName).getId();
        List<LessonUserPair> pairs = lessonService.getAllGroupsByCourseId(courseId);
        if(pairs.isEmpty()){
            return Path.PAGE_NOT_FORMED_GROUPS;
        }
        Map<Integer, List<User>> map = new HashMap<>();
        List<LessonUsersDto> groupsForCourse = new ArrayList<>();
        pairs.forEach(pair->{
            if(map.containsKey(pair.getLessonId())){
                map.get(pair.getLessonId()).add(userService.getUserById(pair.getUserId()));
            }else{
                ArrayList<User> list = new ArrayList<>();
                list.add(userService.getUserById(pair.getUserId()));
                map.put(pair.getLessonId(), list);
            }
        });
        map.forEach((key, value)->groupsForCourse.add(new LessonUsersDto(lessonService.getLessonById(key), value)));
        request.setAttribute("groupsForCourse", groupsForCourse);
        return Path.PAGE_WITH_FORMED_GROUPS;


    }
}
