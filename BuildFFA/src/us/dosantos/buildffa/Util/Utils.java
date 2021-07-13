package us.dosantos.buildffa.Util;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Utils {
    public static WorldGuardPlugin getWorldGuard() {
        Plugin pl = Bukkit.getPluginManager().getPlugin("WorldGuard");
        return (pl != null) ? WorldGuardPlugin.inst() : null;
    }

    public static Boolean isOnRegion(Player player, String name) {
        ProtectedRegion region = WGBukkit.getRegionManager(player.getWorld()).getRegion(name);
        if (region == null)
            return Boolean.valueOf(false);
        return Boolean.valueOf(region.contains(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()));
    }

    public static Boolean isRegionWithPvP(Player p) {
        return Boolean.valueOf(!isOnRegion(p, "spawn").booleanValue());
    }

    public static Boolean isRegionWithPvP(Location l) {
        if (getWorldGuard() != null)
            return Boolean.valueOf(getWorldGuard().getGlobalRegionManager().allows(DefaultFlag.PVP, l));
        return Boolean.valueOf(true);
    }

    public static String Miliseconds(Long l) {
        float f = ((float)l.longValue() + 0.0F) / 1000.0F;
        String s = String.format("%1$.1f", new Object[] { Float.valueOf(f) });
        return s.replace(",", ".");
    }

    public static String color(String str) {

        return str.replaceAll("&", "§");
    }
}

