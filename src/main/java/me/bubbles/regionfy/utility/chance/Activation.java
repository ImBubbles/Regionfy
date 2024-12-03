package me.bubbles.regionfy.utility.chance;

public class Activation extends Chance<Boolean> {

    public Activation(double min, double max, double below) {
        super(true, false, min, max, below);
    }

}
