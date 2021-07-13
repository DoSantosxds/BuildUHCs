package us.dosantos.buildffa.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.dosantos.buildffa.BuildFFA;

public class StatsCommand implements CommandExecutor {
    private BuildFFA plugin;



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.STRIKETHROUGH + "--------------------");
                player.sendMessage(ChatColor.GOLD + player.getName() + ChatColor.YELLOW + "'s Stats:");
                player.sendMessage(ChatColor.YELLOW + "Kills: " + ChatColor.GRAY + player.getStatistic(Statistic.PLAYER_KILLS));
                player.sendMessage(ChatColor.YELLOW + "Deaths: " + ChatColor.GRAY + player.getStatistic(Statistic.DEATHS));
                player.sendMessage(ChatColor.STRIKETHROUGH + "--------------------");
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                player.sendMessage(ChatColor.STRIKETHROUGH + "--------------------");
                player.sendMessage(ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s Stats:");
                player.sendMessage(ChatColor.YELLOW + "Kills: " + ChatColor.GRAY + target.getStatistic(Statistic.PLAYER_KILLS));
                player.sendMessage(ChatColor.YELLOW + "Deaths: " + ChatColor.GRAY + target.getStatistic(Statistic.DEATHS));
                player.sendMessage(ChatColor.STRIKETHROUGH + "--------------------");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        }
        return true;
    }
}