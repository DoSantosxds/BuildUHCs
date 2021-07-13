package us.dosantos.buildffa.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.dosantos.buildffa.BuildFFA;
import us.dosantos.buildffa.Util.Utils;

public class SpawnCommand implements CommandExecutor {
    private BuildFFA plugin;

    public SpawnCommand(BuildFFA plugin) {
        this.plugin = plugin;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("setspawn")) {
                if (player.hasPermission("iserycombo.setspawn")) {
                    this.plugin.getConfig().set("SpawnLocation.World", player.getLocation().getWorld().getName());
                    this.plugin.getConfig().set("SpawnLocation.X", Double.valueOf(player.getLocation().getX()));
                    this.plugin.getConfig().set("SpawnLocation.Y", Double.valueOf(player.getLocation().getY()));
                    this.plugin.getConfig().set("SpawnLocation.Z", Double.valueOf(player.getLocation().getZ()));
                    this.plugin.getConfig().set("SpawnLocation.Pitch", Float.valueOf(player.getLocation().getPitch()));
                    this.plugin.getConfig().set("SpawnLocation.Yaw", Float.valueOf(player.getLocation().getYaw()));
                    this.plugin.saveConfig();
                    player.sendMessage(ChatColor.GREEN + "Spawn location set!");
                } else {
                    player.sendMessage(ChatColor.RED + "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
                }
            } else if (cmd.getName().equalsIgnoreCase("spawn")) {
                if (player.hasPermission("iserybuild.spawn")) {
                    if (!this.plugin.getSpawnManager().useTeleportWarmup() || player.hasPermission("isery.spawn.bypass") || !Utils.isRegionWithPvP(player.getLocation()).booleanValue()) {
                        player.sendMessage(ChatColor.GREEN + "Teleporting to spawn.");
                        player.teleport(this.plugin.getSpawnManager().teleportToSpawn());
                    } else {
                        this.plugin.getSpawnManager().teleportToSpawnWithWarmup(player);
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
                }
            }

        return true;
    }
}