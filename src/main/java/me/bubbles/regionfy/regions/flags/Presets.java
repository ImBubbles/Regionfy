package me.bubbles.regionfy.regions.flags;

import java.util.Arrays;
import java.util.HashSet;

public enum Presets {

    STAGE(new HashSet<>(Arrays.asList(
            Flags.PREVENT_BUILDING,
            Flags.PREVENT_INTERACT,
            Flags.PREVENT_PVP,
            Flags.PREVENT_FALL_DAMAGE,
            Flags.PREVENT_OPENABLE
    )));

    private final HashSet<Flags> flags;
    Presets(HashSet<Flags> flags) {
        this.flags=flags;
    }

    public HashSet<Flags> getFlags() {
        return flags;
    }

}
