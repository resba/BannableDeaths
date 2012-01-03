package com.resbah.DeathBan;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class DeathBanEntityListener extends EntityListener {

    public static DeathBan plugin;
    
    public DeathBanEntityListener(DeathBan instance) {
            plugin = instance;
    }
    
	public void onEntityDeath(EntityDeathEvent event) {
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			if(player.hasPermission("BannableDeaths.bypass")){
			plugin.getServer().broadcastMessage(player.getName() + " has died, but is immortal, therefore shall not be banned!");
			}else{
			plugin.getServer().broadcastMessage(ChatColor.RED + "Whoops! Looks like " + player.getName() + " died by " + player.getLastDamageCause().getCause());
			plugin.getConfig().set("banned."+player.getName(), player.getName());
			plugin.saveConfig();
			player.setBanned(true);
			String Pl = player.getName();
			player.kickPlayer(Pl);
			}
		}

	}
	
}
