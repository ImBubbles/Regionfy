package me.bubbles.regionfy.regions;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.regions.flags.Flag;
import me.bubbles.regionfy.regions.flags.Flags;
import me.bubbles.regionfy.regions.flags.Presets;
import me.bubbles.regionfy.regions.shape.Vector3D;
import me.bubbles.regionfy.regions.shape.WorldCuboid;
import me.bubbles.regionfy.session.PlayerSession;
import me.bubbles.regionfy.session.Session;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashSet;
import java.util.stream.Collectors;

public class Region extends WorldCuboid {

    private final HashSet<Flag> flags = new HashSet<>();

    public Region(World world, Vector3D corner1, Vector3D corner2) {
        super(world, corner1, corner2);
    }

    public HashSet<Flag> getFlags() {
        return flags;
    }

    public void addFlag(Flags flag) {
        this.flags.add(flag.getFlag());
    }

    public void addFlags(Flags... flags) {
        for(Flags flag : flags) {
            this.flags.add(flag.getFlag());
        }
    }

    public void loadPreset(Presets preset) {
        for(Flags flag : preset.getFlags()) {
            addFlag(flag);
        }
    }

    public HashSet<Session> getInside() {
        return Regionfy.getInstance().getSessionManager().getByRegion(this);
    }

    public HashSet<Session> getFlaggable() {
        return getInside().stream().filter(Session::affectedByFlags).collect(Collectors.toCollection(HashSet::new));
    }

    public boolean isFlaggable(Player player) {
        return isFlaggable(Regionfy.getInstance().getSessionManager().getPlayerSession(player));
    }

    public boolean isFlaggable(PlayerSession playerSession) {
        if(!isInside(playerSession)) {
            return false;
        }
        return playerSession.affectedByFlags();
    }

    protected void onEvent(Event event) {
        flags.forEach(flag -> flag.onEvent(this, event));
        /*flags.stream()
                .filter(flag -> flag.getEvents().contains(event.getClass()))
                .collect(Collectors.toList())
                .forEach(flag -> flag.onEvent(this, event));*/
        //flags.forEach(flag -> flag.onEvent(event));
    }

}
