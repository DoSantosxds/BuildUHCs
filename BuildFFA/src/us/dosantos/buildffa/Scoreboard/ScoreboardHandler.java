package us.dosantos.buildffa.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import us.dosantos.buildffa.BuildFFA;
import us.dosantos.buildffa.Util.Utils;

public class ScoreboardHandler implements Listener {
    private BuildFFA plugin;

    private Map helpers;

    public ScoreboardHandler(BuildFFA pl) {
        this.plugin = pl;
        this.helpers = new HashMap<>();
        for (Player onlinePlayers : Bukkit.getOnlinePlayers())
            loadData(onlinePlayers);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public PlayerScoreboard getScoreboardFor(Player player) {
        return (PlayerScoreboard)this.helpers.get(player.getUniqueId());
    }

    public final void loadData(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
        this.helpers.put(player.getUniqueId(), new PlayerScoreboard(player, scoreboard, Utils.color(this.plugin.getConfig().getString("Scoreboard.Title"))));
    }

    public void disable() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            for (String players : onlinePlayers.getScoreboard().getEntries())
                onlinePlayers.getScoreboard().resetScores(players);
        }
        this.helpers.clear();
    }

    public void reload() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            loadData(onlinePlayers);
            if (this.helpers.containsKey(onlinePlayers.getUniqueId()))
                onlinePlayers.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        loadData(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for (String players : player.getScoreboard().getEntries())
            player.getScoreboard().resetScores(players);
        this.helpers.remove(player.getUniqueId());
    }

    public void setupScoreboard() {
        (new BukkitRunnable() {
            public void run() {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    int playerKills = players.getStatistic(Statistic.PLAYER_KILLS);
                    int playerDeaths = players.getStatistic(Statistic.DEATHS);
                    PlayerScoreboard sb = ScoreboardHandler.this.getScoreboardFor(players);
                    sb.clear();
                    sb.addLine("&7&m-------", "&1&7&m--------", "&7&m-------");
                    for (String s : ScoreboardHandler.this.plugin.getConfig().getStringList("ScoreboardText"))
                        sb.add(s.replace("%kills%", "" + playerKills).replace("%deaths%", "" + playerDeaths), "");
                    sb.addLine("&7&m-------", "&7&m--------", "&7&m-------");
                    sb.update(players);
                }
            }
        }).runTaskTimerAsynchronously((Plugin)this.plugin, 40L, 2L);
    }
}
