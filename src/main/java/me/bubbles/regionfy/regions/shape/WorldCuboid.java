package me.bubbles.regionfy.regions.shape;

import me.bubbles.regionfy.session.PlayerSession;
import org.bukkit.Location;
import org.bukkit.World;

public class WorldCuboid extends Cuboid {

    private World world;

    public WorldCuboid(World world, Vector3D corner1, Vector3D corner2) {
        super(corner1, corner2);
        this.world=world;
    }

    public boolean isInside(PlayerSession playerSession) {
        return isInside(playerSession.getBukkitPlayer().getLocation());
    }

    public boolean isInside(Location location) {
        return isInside(location.getWorld(), location.getX(), location.getY(), location.getZ());
    }

    public boolean isInside(World world, Vector3D vector3D) {
        return isInside(world, vector3D.getX(), vector3D.getY(), vector3D.getZ());
    }

    public boolean isInside(World world, double x, double y, double z) {
        if(world!=this.world) {
            return false;
        }

        // POS 1
        double x1 = getCorner1().getX();
        double z1 = getCorner1().getZ();
        double y1 = getCorner1().getY();

        // POS 2
        double x2 = getCorner2().getX();
        double z2 = getCorner2().getZ();
        double y2 = getCorner2().getY();

        // WITHIN
        boolean withinX = (x > Math.min(x1,x2) && (x < Math.max(x1,x2)));
        boolean withinY = (y > Math.min(y1,y2) && (y < Math.max(y1,y2)));
        boolean withinZ = (z > Math.min(z1,z2) && (z < Math.max(z1,z2)));

        return withinX && withinY && withinZ;
    }

}
