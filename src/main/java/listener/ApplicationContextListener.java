package listener;

import command.Command;
import command.GetAllUsersCommand;
import command.GetLessonsByCourseCommand;
import command.GetPrioritiesByUserCommand;
import command.LoginCommand;
import command.LogoutCommand;
import command.container.CommandContainer;
import command.impl.GetUsersByCourseCommand;
import command.impl.lesson.FormGroupCommand;
import command.impl.user.GetUserByIdCommand;
import dao.Dao;
import dao.LessonDao;
import dao.PriorityDao;
import dao.UserDao;
import command.EstablishPriorityCommand;
import dao.impl.LessonDaoImpl;
import dao.impl.PriorityDaoImpl;
import dao.impl.UserDaoImpl;
import service.LessonService;
import service.PriorityService;
import service.Service;
import service.UserService;
import service.impl.GroupFormer;
import service.impl.LessonServiceImpl;
import service.impl.PriorityServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContextListener implements ServletContextListener {
    private Map<String, Command> commands;
    private Map<Class, Service> services;
    private Map<Class, Dao> daos;



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext appContext = servletContextEvent.getServletContext();
        initDaos();
        initServices();
        CommandContainer commandContainer = new CommandContainer();
        initCommands();
        commandContainer.setCommands(commands);

        appContext.setAttribute("commandContainer", commandContainer);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
    private void initServices(){
        services = new HashMap<>();
        services.put(UserServiceImpl.class, new UserServiceImpl((UserDao)daos.get(UserDaoImpl.class)));
        services.put(LessonServiceImpl.class, new LessonServiceImpl((LessonDao)daos.get(LessonDaoImpl.class)));
        services.put(PriorityServiceImpl.class, new PriorityServiceImpl((PriorityDao)daos.get(PriorityDaoImpl.class)));
        services.put(GroupFormer.class, new GroupFormer((UserDao)daos.get(UserDaoImpl.class), (LessonDao)daos.get(LessonDaoImpl.class), (PriorityDao)daos.get(PriorityDaoImpl.class)));
    }
    private void initDaos(){
        daos = new HashMap<>();
        daos.put(UserDaoImpl.class, new UserDaoImpl());
        daos.put(LessonDaoImpl.class, new LessonDaoImpl());
        daos.put(PriorityDaoImpl.class, new PriorityDaoImpl());
    }
    private void initCommands(){
        commands = new HashMap<>();
        commands.put("getUserById", new GetUserByIdCommand((UserService)services.get(UserServiceImpl.class)));
        commands.put("groupFormer", new FormGroupCommand((GroupFormer)services.get(GroupFormer.class)));
        commands.put("login", new LoginCommand((UserService)services.get(UserServiceImpl.class)));
        commands.put("userLessons", new GetLessonsByCourseCommand((LessonService)services.get(LessonServiceImpl.class), (PriorityService)services.get(PriorityServiceImpl.class)));
        commands.put("setPriority", new EstablishPriorityCommand((PriorityService)services.get(PriorityServiceImpl.class)));
        commands.put("logout", new LogoutCommand());
        commands.put("prioritiesByUser", new GetPrioritiesByUserCommand((PriorityService)services.get(PriorityServiceImpl.class), (LessonService)services.get(LessonServiceImpl.class)));
        commands.put("allUsers", new GetAllUsersCommand((UserService)services.get(UserServiceImpl.class)));
        commands.put("usersByCourse", new GetUsersByCourseCommand((UserService)services.get(UserServiceImpl.class)));
    }


}

