package me.bubbles.regionfy.regions.flags;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.regions.Region;
import me.bubbles.regionfy.session.Session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Flag {

    private final FlagRunnable flagRunnable;
    private Region region;
    private List<Class> events;

    public Flag(List<Class> event, FlagRunnable flagRunnable) {
        this.events=event;
        this.flagRunnable=flagRunnable;
    }

    public Flag(Class event, FlagRunnable flagRunnable) {
        List<Class> classes = new ArrayList<>();
        classes.add(event);
        this.events=classes;
        this.flagRunnable=flagRunnable;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void onEvent(Region region, org.bukkit.event.Event event) {
        if(region==null) {
            return;
        }
        if(!events.contains(event.getClass())) {
            return;
        }
        flagRunnable.run(event, region);
    }

    public static HashSet<Session> getInside(Region region) {
        return Regionfy.getInstance().getSessionManager().getByRegion(region);
    }

    public List<Class> getEvents() {
        return events;
    }

}
