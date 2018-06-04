package controller;

import command.container.CommandContainer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final String COMMAND_CONTAINER = "commandContainer";
    private CommandContainer commandContainer;

    @Override
    public void init(ServletConfig appConfig){
        ServletContext appContext = appConfig.getServletContext();
        commandContainer = (CommandContainer) appContext.getAttribute(COMMAND_CONTAINER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter("command");
        String path = commandContainer.get(commandName).execute(request, response);
        if (path != null) {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }


}
