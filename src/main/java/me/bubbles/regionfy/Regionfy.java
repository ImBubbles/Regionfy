package me.bubbles.regionfy;

import me.bubbles.regionfy.commands.manager.CommandManager;
import me.bubbles.regionfy.configs.ConfigManager;
import me.bubbles.regionfy.events.manager.EventManager;
import me.bubbles.regionfy.regions.RegionManager;
import me.bubbles.regionfy.session.SessionManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Regionfy extends JavaPlugin {

    private static Regionfy instance;
    private ConfigManager configManager;
    private SessionManager sessionManager;
    private RegionManager regionManager;
    private EventManager eventManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance=this;
        this.configManager=new ConfigManager();
        configManager.addConfig(
                "config.yml"
        );
        this.sessionManager=new SessionManager();
        this.eventManager=new EventManager();
        this.regionManager=new RegionManager();
        this.commandManager=new CommandManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Regionfy getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public RegionManager getRegionManager() {
        return regionManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

}
