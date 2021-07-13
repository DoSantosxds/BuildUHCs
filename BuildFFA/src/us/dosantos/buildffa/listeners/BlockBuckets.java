package us.dosantos.buildffa.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.dosantos.buildffa.BuildFFA;

public class BlockBuckets implements Listener {
    private BuildFFA plugin;


    @EventHandler
    public void onPlayerBucketEmptyEvent(final PlayerBucketEmptyEvent e) {
        if (e.getBucket() != null && e.getBucket() == Material.LAVA_BUCKET)
            (new BukkitRunnable() {
                public void run() {
                    e.getBlockClicked().getRelative(e.getBlockFace()).setType(Material.AIR);
                }
            }).runTaskLater(this.plugin, 100L);
        if (e.getBucket() != null && e.getBucket() == Material.WATER_BUCKET)
            (new BukkitRunnable() {
                public void run() {
                    e.getBlockClicked().getRelative(e.getBlockFace()).setType(Material.AIR);
                }
            }).runTaskLater(this.plugin, 100L);
    }
}
