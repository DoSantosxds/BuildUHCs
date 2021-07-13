package us.dosantos.buildffa.listeners;


import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.dosantos.buildffa.BuildFFA;

public class PlayerListener implements Listener {
    private BuildFFA plugin;

    public PlayerListener(BuildFFA plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.plugin.getKitManager().GiveKit(player);
        if (!player.hasPlayedBefore()) {
            player.teleport(this.plugin.getSpawnManager().teleportToSpawn());
        }
        player.setMaximumNoDamageTicks(1);
    }

    public void onPlayerRespawn(PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        event.setRespawnLocation(this.plugin.getSpawnManager().teleportToSpawn());
        (new BukkitRunnable() {
            public void run() {
                PlayerListener.this.plugin.getKitManager().GiveKit(player);
            }
        }).runTask(this.plugin);
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        final Player player = event.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        event.setDeathMessage(null);
        event.getDrops().clear();
        if (player.getInventory().getHelmet() != null)
            player.getWorld().dropItem(player.getLocation().clone().add(0.0D, 2.5D, 0.0D), player.getInventory().getHelmet());
        if (player.getInventory().getChestplate() != null)
            player.getWorld().dropItem(player.getLocation().clone().add(0.0D, 2.5D, 0.0D), player.getInventory().getChestplate());
        if (player.getInventory().getLeggings() != null)
            player.getWorld().dropItem(player.getLocation().clone().add(0.0D, 2.5D, 0.0D), player.getInventory().getLeggings());
        if (player.getInventory().getBoots() != null)
            player.getWorld().dropItem(player.getLocation().clone().add(0.0D, 2.5D, 0.0D), player.getInventory().getBoots());
        if (player.getInventory().contains(Material.DIAMOND_SWORD)) {
            HashMap<Integer, ItemStack> slots = (HashMap<Integer, ItemStack>) player.getInventory().all(Material.DIAMOND_SWORD);
            for (int num = slots.size(), i = 0; i < num; i++)
                player.getWorld().dropItem(player.getLocation().clone().add(0.0D, 2.5D, 0.0D), player.getInventory().getItem(i));
        }
        if (event.getEntity().getKiller() != null) {
            (new BukkitRunnable() {
                public void run() {
                    PlayerListener.this.plugin.getKitManager().Refill(event.getEntity().getKiller());
                }
            }).runTask(this.plugin);
            event.getEntity().getKiller().sendMessage(ChatColor.GREEN + "Your inventory was refilled!");
        }
        (new BukkitRunnable() {
            public void run() {
                player.spigot().respawn();
            }
        }).runTaskLater(this.plugin, 60L);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);

    }

}
