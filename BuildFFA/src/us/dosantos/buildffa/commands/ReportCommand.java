package us.dosantos.buildffa.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.dosantos.buildffa.BuildFFA;

public class ReportCommand implements CommandExecutor {
    private BuildFFA plugin;


    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        this.plugin.getConfig().getString(this.plugin + "Report.notOnline")));
                return true;
            }
            if (args.length >= 2) {
                Player target = sender.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("Player " + args[0] + " not found!");
                } else {
                    String reason = "";
                    int x = 0;
                    byte b;
                    int i;
                    String[] arrayOfString;
                    for (i = (arrayOfString = args).length, b = 0; b < i; ) {
                        String a = arrayOfString[b];
                        if (x == 0) {
                            x++;
                        } else {
                            reason = reason + " " + a;
                        }
                        b++;
                    }
                    reason = reason.trim();
                    sender.sendMessage(this.plugin + "Report sent!");
                    Player[] arrayOfPlayer;
                    for (i = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0])).length, b = 0; b < i; ) {
                        Player p = arrayOfPlayer[b];
                        if (p.hasPermission("build.reportes"))
                            p.sendMessage(sender.getName() + ChatColor.DARK_RED + "you send report" + target.getName() + ChatColor.GREEN +" for the reason:" +
                        reason);
                        b++;
                    }
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "/report <player> <reason>");
            }
        }
        return true;
    }
}
