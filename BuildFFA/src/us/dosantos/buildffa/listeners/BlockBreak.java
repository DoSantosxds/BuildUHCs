package us.dosantos.buildffa.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import us.dosantos.buildffa.BuildFFA;

public class BlockBreak implements Listener {
    private BuildFFA plugin;

    @EventHandler
    public void BlockBreaks(BlockBreakEvent e) {
            e.setCancelled(true);

    }

}