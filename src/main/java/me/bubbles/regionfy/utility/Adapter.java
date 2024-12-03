package me.bubbles.regionfy.utility;

import me.bubbles.regionfy.regions.shape.Vector3D;
import org.bukkit.Location;

public class Adapter {

    public static Vector3D locationToVector3D(Location location) {
        return new Vector3D(location.getX(), location.getY(), location.getZ());
    }

}
