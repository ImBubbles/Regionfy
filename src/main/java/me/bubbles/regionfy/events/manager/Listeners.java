package me.bubbles.regionfy.events.manager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    private final EventManager eventManager;

    public Listeners(EventManager eventManager) {
        this.eventManager=eventManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        eventManager.onEvent(e);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        eventManager.onEvent(e);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        eventManager.onEvent(e);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        eventManager.onEvent(e);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        eventManager.onEvent(e);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        eventManager.onEvent(e);
    }

}

