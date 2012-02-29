package com.precipicegames.utilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInventoryEvent;

public class ToolListener implements Listener {
	public static ToolNotifier plugin;

	public ToolListener(ToolNotifier instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (plugin.tools.containsKey(player.getItemInHand().getType()))
			plugin.onToolDamaged(player);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event instanceof EntityDamageByEntityEvent)) return;

		EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
		if (!(e.getDamager() instanceof Player)) return;

		Player player = (Player)e.getDamager();
		if (plugin.tools.containsKey(player.getItemInHand().getType())) plugin.onToolDamaged(player);
	}

	@EventHandler
	public void onInventoryChange(PlayerInventoryEvent event) {
		Player player = event.getPlayer();
		if (plugin.tools.containsKey(player.getItemInHand().getType())) plugin.onToolDamaged(player);
	}
}
