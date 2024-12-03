package me.bubbles.regionfy.events;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.events.manager.Event;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join extends Event {

    public Join() {
        super(PlayerJoinEvent.class);
    }

    @Override
    public void onEvent(org.bukkit.event.Event event) {
        PlayerJoinEvent e = (PlayerJoinEvent) event;
        Regionfy.getInstance().getSessionManager().getPlayerSession(e.getPlayer());
    }

}