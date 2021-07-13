package us.dosantos.buildffa.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.dosantos.buildffa.BuildFFA;

public class BlockRemove implements Listener {
    private static BuildFFA plugin;


    public void onPlaceFlint(final BlockPlaceEvent e) {
        if (e.getItemInHand().getType().equals(Material.COBBLESTONE))
            (new BukkitRunnable() {
                public void run() {
                    if (e.getBlock().getType().equals(Material.COBBLESTONE))
                        e.getBlock().setType(Material.AIR);
                }
            }).runTaskLater(plugin, 100L);
    }

    @EventHandler
    public void onPlaceLava(BlockPlaceEvent e) {

        if (e.getBlock().getType() == Material.OBSIDIAN) {
            new BukkitRunnable() {
                public void run() {
                    e.getBlock().setType(Material.AIR);
                }
            }.runTaskLater(plugin, 100L);

        }
    }

    @EventHandler
    public void onPlaceArrow(BlockPlaceEvent e) {

        if (e.getBlock().getType() == Material.ARROW) {
            new BukkitRunnable() {
                public void run() {
                    e.getBlock().setType(Material.AIR);
                }
            }.runTaskLater(plugin, 100L);

        }
    }
}
