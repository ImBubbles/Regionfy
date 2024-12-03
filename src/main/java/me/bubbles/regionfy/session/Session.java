package me.bubbles.regionfy.session;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.regions.Region;
import me.bubbles.regionfy.regions.shape.WorldCuboid;
import me.bubbles.regionfy.regions.RegionManager;

import java.util.HashSet;

public abstract class Session implements ISession {

    private HashSet<Region> regions = new HashSet<>();
    private boolean bypassFlags = false;

    public void delete() {
        Regionfy.getInstance().getSessionManager().delete(this);
    }

    public HashSet<Region> updateRegions() {
        this.regions=RegionManager.getRegions(Regionfy.getInstance().getRegionManager().getRegions(), getWorld(), getVector3D());
        return regions;
    }

    public boolean setBypassFlags(boolean value) {
        bypassFlags=value;
        return value;
    }

    public boolean setBypassFlags() {
        bypassFlags=!bypassFlags;
        return bypassFlags;
    }

    public boolean affectedByFlags() {
        return !bypassFlags;
    }

    public HashSet<Region> getRegions() {
        return regions;
    }

    public boolean inRegion(Region region) {
        return updateRegions().contains(region);
    }

}