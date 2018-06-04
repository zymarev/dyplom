package command.container;

import command.Command;

import java.util.Map;

public class CommandContainer {

    private Map<String, Command> commands;

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public  Command get(String commandName) {
        return commands.get(commandName);
    }

}