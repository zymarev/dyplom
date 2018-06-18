package command.impl.lesson;

import command.Command;
import dto.LessonUsersDto;
import dto.UserPriority;
import entity.Lesson;
import entity.User;
import service.LessonService;
import service.impl.GroupFormer;
import web.path.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class FormGroupCommand implements Command {

    private GroupFormer groupFormer;
    private LessonService lessonService;

    public FormGroupCommand(GroupFormer groupFormer, LessonService lessonService){
        this.groupFormer = groupFormer;
        this.lessonService = lessonService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, User[]> groups = groupFormer.formGroups("pi-14");
        Map<Lesson, User[]> groupsByLesson = new HashMap<>();
        groups.forEach((key, value)->groupsByLesson.put(lessonService.getLessonById(key), value));
        List<LessonUsersDto> lessonUsersDtos = new ArrayList<>();
        groups.forEach((key, value)->lessonUsersDtos.add(new LessonUsersDto(lessonService.getLessonById(key), Arrays.asList(value))));
        List<Lesson> lessons = new ArrayList<>();
        groups.forEach((key, value)->lessons.add(lessonService.getLessonById(key)));
        request.setAttribute("lessons", lessons);
        request.setAttribute("groups", lessonUsersDtos);
        return Path.PAGE_WITH_FORMED_GROUPS;
    }
}
