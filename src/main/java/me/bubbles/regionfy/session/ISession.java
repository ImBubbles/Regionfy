package me.bubbles.regionfy.session;

import me.bubbles.regionfy.regions.shape.Vector3D;
import org.bukkit.World;

public interface ISession {

    default World getWorld() {
        return null;
    }
    default Vector3D getVector3D() {
        return null;
    }

}
