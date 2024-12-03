package me.bubbles.regionfy.regions;

import me.bubbles.regionfy.regions.shape.Vector3D;
import org.bukkit.World;
import org.bukkit.event.Event;

import java.util.HashSet;
import java.util.stream.Collectors;

public class RegionManager {

    private static final HashSet<Region> regions = new HashSet<>();

    public HashSet<Region> getRegions() {
        return regions;
    }

    public Region registerRegion(World world, Vector3D corner1, Vector3D corner2) {
        return registerRegion(new Region(world, corner1, corner2));
    }

    public Region registerRegion(Region region) {
        if(regions.contains(region)) {
            return region;
        }
        regions.add(region);
        /*for(Flag flag : region.getFlags()) {
            Regionfy.getInstance().getEventManager().addEvent(flag);
        }*/
        return region;
    }

    public boolean deleteRegion(Region region) {
        if(region==null) {
            return false;
        }
        if(!regions.contains(region)) {
            return false;
        }
        regions.remove(region);
        return true;
    }

    public static HashSet<Region> getRegions(HashSet<Region> regions, World world, Vector3D vector3D) {
        return regions.stream().filter(region -> region.isInside(world, vector3D)).collect(Collectors.toCollection(HashSet::new));
    }

    public void onEvent(Event event) {
        regions.forEach(region -> region.onEvent(event));
    }

}
