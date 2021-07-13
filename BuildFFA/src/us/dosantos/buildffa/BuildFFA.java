package us.dosantos.buildffa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import us.dosantos.buildffa.Scoreboard.ScoreboardHandler;
import us.dosantos.buildffa.commands.ReportCommand;
import us.dosantos.buildffa.commands.SpawnCommand;
import us.dosantos.buildffa.commands.StatsCommand;
import us.dosantos.buildffa.listeners.*;
import us.dosantos.buildffa.mangers.KitManager;
import us.dosantos.buildffa.mangers.SpawnManager;


public class BuildFFA extends JavaPlugin {
    private static BuildFFA plugin;
    private KitManager kitManager;
    private SpawnManager spawnManager;
    private ScoreboardHandler scoreboardHandler;


   public void onEnable() {
    (plugin = this).saveDefaultConfig();
    (this.scoreboardHandler = new ScoreboardHandler(this)).setupScoreboard();
    this.kitManager = new KitManager();
    this.spawnManager = new SpawnManager(this);
    RegisterCommands();
    RegisterEvents();
}

    public void onDisable() {
        (plugin = this).saveDefaultConfig();
        RegisterCommands();
        RegisterEvents();
    }



   public void RegisterCommands() {
    getCommand("stats").setExecutor(new StatsCommand());
    getCommand("setspawn").setExecutor(new SpawnCommand(this));
    getCommand("spawn").setExecutor(new SpawnCommand(this));
    getCommand("report").setExecutor(new ReportCommand());


   }
   public void RegisterEvents() {
    Bukkit.getPluginManager().registerEvents(new DeathLog(), this);
    Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
    Bukkit.getPluginManager().registerEvents(new FallEvent(), this);
    Bukkit.getPluginManager().registerEvents(new BlockBuckets(), this);
    Bukkit.getPluginManager().registerEvents(new BlockRemove(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
}
    public ScoreboardHandler ScoreboardHandler() {
       return this.scoreboardHandler;
    }

    public KitManager getKitManager() {

       return this.kitManager;
    }

    public SpawnManager getSpawnManager() {

       return this.spawnManager;
    }

}

