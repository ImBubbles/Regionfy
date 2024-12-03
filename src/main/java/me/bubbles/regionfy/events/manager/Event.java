package me.bubbles.regionfy.events.manager;

import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Event implements Listener {
    private List<Class> event;

    public Event(List<Class> event) {
        this.event=event;
    }

    public Event(Class event) {
        List<Class> classes = new ArrayList<>();
        classes.add(event);
        this.event=classes;
    }

    public void onEvent(org.bukkit.event.Event event) {

    }

    public List<Class> getEvents() {
        return event;
    }

}
