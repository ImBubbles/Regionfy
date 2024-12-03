package me.bubbles.regionfy.regions.shape;

public class Cuboid {

    private final Vector3D corner1;
    private final Vector3D corner2;

    public Cuboid(Vector3D corner1, Vector3D corner2) {
        this.corner1=corner1;
        this.corner2=corner2;
    }

    public Vector3D getCorner1() {
        return corner1;
    }

    public Vector3D getCorner2() {
        return corner2;
    }

}
