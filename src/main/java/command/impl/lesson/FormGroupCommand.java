package command.impl.lesson;

import command.Command;
import dto.LessonUsersDto;
import dto.UserPriority;
import entity.Lesson;
import entity.User;
import service.LessonService;
import service.impl.CourseServiceImpl;
import service.impl.GroupFormer;
import web.path.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class FormGroupCommand implements Command {

    private GroupFormer groupFormer;
    private LessonService lessonService;
    private GetGroupsCommand command;

    public FormGroupCommand(GroupFormer groupFormer, LessonService lessonService, GetGroupsCommand command){
        this.groupFormer = groupFormer;
        this.lessonService = lessonService;
        this.command = command;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String courseName = request.getParameter("courseNameForGroups");
        Map<Integer, User[]> groups = groupFormer.formGroups(courseName);
        Map<Lesson, User[]> groupsByLesson = new HashMap<>();
        groups.forEach((key, value)->groupsByLesson.put(lessonService.getLessonById(key), value));
        List<LessonUsersDto> lessonUsersDtos = new ArrayList<>();
        groups.forEach((key, value)->lessonUsersDtos.add(new LessonUsersDto(lessonService.getLessonById(key), Arrays.asList(value))));
        List<Lesson> lessons = new ArrayList<>();
        groups.forEach((key, value)->lessons.add(lessonService.getLessonById(key)));
        request.setAttribute("lessons", lessons);
        request.setAttribute("groups", lessonUsersDtos);
        return command.execute(request, response);
    }
}
