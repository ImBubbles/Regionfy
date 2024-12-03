package me.bubbles.regionfy.utility.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class UtilLocation {

    public static Location toLocation(String value) {
        String[] values = value.split(",");
        Location location;
        if(values.length==4) {
            location=new Location(
                    Bukkit.getWorld(values[0]),
                    Double.parseDouble(values[1]),
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3])
            );
        } else if (values.length==6) {
            location=new Location(
                    Bukkit.getWorld(values[0]),
                    Double.parseDouble(values[1]),
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3]),
                    Float.parseFloat(values[4]),
                    Float.parseFloat(values[5])
            );
        } else {
            location=null;
        }
        return location;
    }

    public static String asLocationString(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getWorld().getName());
        stringBuilder.append(",");
        stringBuilder.append(location.getX());
        stringBuilder.append(",");
        stringBuilder.append(location.getY());
        stringBuilder.append(",");
        stringBuilder.append(location.getZ());
        stringBuilder.append(",");
        stringBuilder.append(location.getYaw());
        stringBuilder.append(",");
        stringBuilder.append(location.getPitch());
        return stringBuilder.toString();
    }

}
