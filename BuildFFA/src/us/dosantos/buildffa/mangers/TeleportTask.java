package us.dosantos.buildffa.mangers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.dosantos.buildffa.BuildFFA;

public class TeleportTask extends BukkitRunnable {
    private BuildFFA plugin;

    private Player player;

    private Location destination;

    private int warmup;

    private String message;

    public TeleportTask(BuildFFA plugin, Player player, Location destination, int warmup, String message) {
        this.plugin = plugin;
        this.player = player;
        this.destination = destination;
        this.warmup = warmup;
        this.message = message;
    }

    public void start() {
        runTaskLater(this.plugin, (this.warmup * 20));
    }

    public void cancel() {

        super.cancel();
    }

    public void run() {
        if (this.message != null)
            this.player.sendMessage(this.message);
        this.plugin.getSpawnManager().removeTeleport(this.player);
        this.player.teleport(this.destination);
    }
}
