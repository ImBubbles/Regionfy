package me.bubbles.regionfy.commands;

import me.bubbles.regionfy.commands.manager.Command;
import org.bukkit.command.CommandSender;

public class Bypass extends Command {

    public Bypass() {
        super("bypass");
        setPermission("bypass");
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        super.run(sender, args);

        if(!permissionCheck()) {
            return;
        }

        boolean result = utilSender.getPlayerSession().setBypassFlags();
        utilSender.sendMessage("%prefix% %primary%Bypass mode set to: %secondary%"+result+"%primary%.");

    }

}
