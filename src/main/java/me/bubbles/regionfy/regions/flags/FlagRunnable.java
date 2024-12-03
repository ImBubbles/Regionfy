package me.bubbles.regionfy.regions.flags;

import me.bubbles.regionfy.regions.Region;
import org.bukkit.event.Event;

@FunctionalInterface
public interface FlagRunnable {

    void run(Event event, Region region);

}
