package listener;

import command.Command;
import command.impl.lesson.FormGroupCommand;
import command.impl.user.GetUserByIdCommand;
import dao.Dao;
import dao.LessonDao;
import dao.PriorityDao;
import dao.UserDao;
import dao.impl.LessonDaoImpl;
import dao.impl.PriorityDaoImpl;
import dao.impl.UserDaoImpl;
import dao.util.ConnectionManager;
import dao.util.TransactionManager;
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

    private static final String USER_SERVICE = "userService";
    private static final String LESSON_SERVICE = "lessonService";
    private static final String PRIORITY_SERVICE = "priorityService";
    private Map<String, Command> commands;
    private Map<Class, Service> services;
    private Map<Class, Dao> daos;



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext appContext = servletContextEvent.getServletContext();
        ConnectionManager connectionManager = new ConnectionManager();
        TransactionManager transactionManager = new TransactionManager(connectionManager);
        initDaos();
        initServices(transactionManager);
        initCommands();


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
    private void initServices(TransactionManager transactionManager){
        services = new HashMap<>();
        services.put(UserServiceImpl.class, new UserServiceImpl(transactionManager, (UserDao)daos.get(UserDaoImpl.class)));
        services.put(LessonService.class, new LessonServiceImpl(transactionManager, (LessonDao)daos.get(LessonDaoImpl.class)));
        services.put(PriorityService.class, new PriorityServiceImpl(transactionManager, (PriorityDao)daos.get(PriorityDaoImpl.class)));
        services.put(GroupFormer.class, new GroupFormer(transactionManager, (UserDao)daos.get(UserDaoImpl.class), (LessonDao)daos.get(LessonDaoImpl.class), (PriorityDao)daos.get(PriorityDaoImpl.class)));
    }
    private void initDaos(){
        daos = new HashMap<>();
        daos.put(UserDaoImpl.class, new UserDaoImpl());
        daos.put(LessonServiceImpl.class, new LessonDaoImpl());
        daos.put(PriorityDaoImpl.class, new PriorityDaoImpl());
    }
    private void initCommands(){
        commands = new HashMap<>();
        commands.put("getUserById", new GetUserByIdCommand((UserService)services.get(UserServiceImpl.class)));
        commands.put("groupFormer", new FormGroupCommand((GroupFormer)services.get(GroupFormer.class)));
    }


}

