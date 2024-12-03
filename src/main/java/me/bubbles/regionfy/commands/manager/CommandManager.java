package me.bubbles.regionfy.commands.manager;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.commands.Bypass;

import java.util.ArrayList;
import java.util.HashSet;

public class CommandManager {
    private HashSet<Command> commands;

    public CommandManager() {
        this.commands=new HashSet<>();
        registerCommands();
    }

    public void registerCommands() {
        addCommand(
                new Bypass()
        );
    }

    public void addCommand(Command... commands) {
        for(Command command : commands) {
            try {
                Regionfy.getInstance().getCommand(command.getCommand()).setExecutor(command);
                Regionfy.getInstance().getCommand(command.getCommand()).setTabCompleter(command);
                this.commands.add(command);
                if(!command.getArguments().isEmpty()) {
                    registerArguments(command.getArguments());
                }
            } catch (NullPointerException e) {
                Regionfy.getInstance().getLogger().warning("Command /"+command.getCommand()+", could not be registered. Most likely due to improper plugin.yml");
            }
        }
    }

    public void registerArguments(ArrayList<Argument> arguments) {
        for(Argument argument : arguments) {
            if(argument.getAlias()!=null) {
                try {
                    Regionfy.getInstance().getCommand(argument.getAlias()).setExecutor(argument);
                } catch (NullPointerException e) {
                    Regionfy.getInstance().getLogger().warning("Command /"+argument.getAlias()+", could not be registered. Most likely due to improper plugin.yml");
                }
            }
            if(!argument.getArguments().isEmpty()) {
                registerArguments(argument.getArguments());
            }
        }
    }

    public HashSet<Command> getCommands() {
        return commands;
    }

}