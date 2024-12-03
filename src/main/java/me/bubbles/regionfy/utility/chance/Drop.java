package me.bubbles.regionfy.utility.chance;

import org.bukkit.inventory.ItemStack;

public class Drop extends Chance<ItemStack> {

    public Drop(ItemStack itemStack, double min, double max, double below) {
        super(itemStack, null, min, max, below);
    }

}
