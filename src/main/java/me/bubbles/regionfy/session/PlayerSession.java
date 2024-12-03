package me.bubbles.regionfy.session;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.regions.shape.Vector3D;
import me.bubbles.regionfy.utility.Adapter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerSession extends Session {

    private final Player player;

    public PlayerSession(Player player) {
        this.player=player;
    }

    public Player getBukkitPlayer() {
        return player;
    }

    @Override
    public World getWorld() {
        return player.getWorld();
    }

    @Override
    public Vector3D getVector3D() {
        Location location = player.getLocation();
        return Adapter.locationToVector3D(location);
        //return new Vector3D(location.getX(), location.getY(), location.getZ());
    }

}
