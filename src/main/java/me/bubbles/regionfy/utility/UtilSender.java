package me.bubbles.regionfy.utility;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.session.PlayerSession;
import me.bubbles.regionfy.utility.string.UtilString;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UtilSender {

    private CommandSender sender;

    public UtilSender(CommandSender sender) {
        this.sender=sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void sendMessage(String message) {
        if(isPlayer()) {
            getPlayer().sendMessage(UtilString.colorFillPlaceholders(message));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(UtilString.colorFillPlaceholders(message.replace("\n","\n&f")));
    }

    public Player getPlayer() {
        if(!isPlayer()){
            return null;
        }
        return (Player) sender;
    }

    public boolean hasPermission(String permission) {
        if(!isPlayer()) {
            return true;
        }
        return getPlayer().hasPermission(permission);
    }

    public PlayerSession getPlayerSession() {
        if(!isPlayer()) {
            return null;
        }
        return Regionfy.getInstance().getSessionManager().getPlayerSession(getPlayer());
    }

}
