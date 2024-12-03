package me.bubbles.regionfy.events;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.events.manager.Event;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave extends Event {

    public Leave() {
        super(PlayerQuitEvent.class);
    }

    @Override
    public void onEvent(org.bukkit.event.Event event) {
        PlayerQuitEvent e = (PlayerQuitEvent) event;
        Regionfy.getInstance().getSessionManager().getPlayerSession(e.getPlayer()).delete();
    }

}