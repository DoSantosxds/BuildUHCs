package us.dosantos.buildffa.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import us.dosantos.buildffa.BuildFFA;

public class FallEvent implements Listener {
    private BuildFFA plugin;


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL)
            event.setCancelled(true);
    }
}