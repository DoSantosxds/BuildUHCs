package us.dosantos.buildffa.mangers;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import us.dosantos.buildffa.BuildFFA;

public class SpawnManager {
    private BuildFFA plugin;

    private Map<Player, TeleportTask> pendingTeleports;

    private int TeleportWarmup;

    public SpawnManager(BuildFFA plugin) {
        this.pendingTeleports = new HashMap<>();
        this.TeleportWarmup = 10;
        this.plugin = plugin;
    }

    public boolean useTeleportWarmup() {
        return (this.TeleportWarmup > 0);
    }

    public void cancelTeleport(Player player, boolean notify) {
        if (getPendingTeleports().containsKey(player)) {
            (getPendingTeleports().get(player)).cancel();
            getPendingTeleports().remove(player);
            if (notify)
                player.sendMessage(ChatColor.RED + "Teleport cancelled.");
        }
    }

    public TeleportTask getTeleport(Player player) {
        return getPendingTeleports().get(player);
    }

    public void removeTeleport(Player player) {
        getPendingTeleports().remove(player);
    }

    public void teleportToSpawnWithWarmup(Player player) {
        cancelTeleport(player, false);
        player.sendMessage(ChatColor.GREEN + "Teleporting in %seconds% seconds, please stand still and do not take damage.".replace("%seconds%", "" + this.TeleportWarmup));
        TeleportTask teleport = new TeleportTask(this.plugin, player, teleportToSpawn(), this.TeleportWarmup, ChatColor.GREEN + "Teleporting to spawn.");
        teleport.start();
        getPendingTeleports().put(player, teleport);
    }

    public Location teleportToSpawn() {
        World world = Bukkit.getWorld(this.plugin.getConfig().getString("SpawnLocation.World"));
        float x = (float)this.plugin.getConfig().getDouble("SpawnLocation.X");
        float y = (float)this.plugin.getConfig().getDouble("SpawnLocation.Y");
        float z = (float)this.plugin.getConfig().getDouble("SpawnLocation.Z");
        float pitch = (float)this.plugin.getConfig().getDouble("SpawnLocation.Pitch");
        float yaw = (float)this.plugin.getConfig().getDouble("SpawnLocation.Yaw");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Map<Player, TeleportTask> getPendingTeleports() {
        return this.pendingTeleports;
    }
}