package me.bubbles.regionfy.events.manager;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.events.Join;
import me.bubbles.regionfy.events.Leave;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class EventManager {
    private final HashSet<Event> events = new HashSet<>();

    public EventManager() {

        registerListener(); // REGISTER EVENT LISTENERS
        Collections.addAll(this.events, // REGISTER EVENT HANDLERS
                new Join(),
                new Leave()
        );
    }

    public void addEvent(Event... events) {
        Collections.addAll(this.events, events);
    }

    public void removeEvent(Event... events) {
        for(Event event : events) {
            this.events.remove(event);
        }
    }

    public void onEvent(org.bukkit.event.Event event) {
        events.stream()
                .filter(eventObj -> eventObj.getEvents().contains(event.getClass()))
                .collect(Collectors.toList())
                .forEach(eventObj -> eventObj.onEvent(event));
        Regionfy.getInstance().getRegionManager().onEvent(event);
    }

    public HashSet<Event> getEvents() {
        return events;
    }

    public void registerListener() {
        Regionfy.getInstance().getServer().getPluginManager().registerEvents(new Listeners(this), Regionfy.getInstance());
    }

}
