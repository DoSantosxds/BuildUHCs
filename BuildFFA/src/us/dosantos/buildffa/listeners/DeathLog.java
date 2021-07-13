package us.dosantos.buildffa.listeners;

import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.StatisticList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import us.dosantos.buildffa.BuildFFA;
import us.dosantos.buildffa.Util.Utils;

public class DeathLog implements Listener {
    private BuildFFA plugin;


    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        StatisticList onlinePlayers = null;
        Player player = event.getEntity();
        if (player.getLastDamageCause() != null && player.getLastDamageCause().getEntity() != null) {
            CraftEntity craftEntity = getKiller(event);
            if (craftEntity instanceof Player) {
                Player killer = (Player)craftEntity;
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.WHITE + " fue asesinado por " + ChatColor.GREEN + killer.getName());
            } else {
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.WHITE + " fue asesinado por " + ChatColor.GREEN + craftEntity.getType().getName());
            }
        }
    }

    private CraftEntity getKiller(PlayerDeathEvent event) {
        EntityLiving lastAttacker = ((CraftPlayer)event.getEntity()).getHandle().aX();
        return (lastAttacker == null) ? null : lastAttacker.getBukkitEntity();
    }

    public void onPlayerDeath(PlayerDeathEvent e) {
            e.setDroppedExp(0);
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.GOLDEN_APPLE, 2));
        }

    @EventHandler
    public void itemdrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
}




