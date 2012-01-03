package com.resbah.DeathBan;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathBan extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	public void onEnable(){
		log.info("DeathBan by resba has been Enabled");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
		this.getConfig().getDefaults();
	}
	 
	public void onDisable(){ 
		log.info("DeathBan has been Disabled");
		this.saveConfig();
		this.reloadConfig();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("dbunban")){
			if(args.length == 1){
				if(sender.hasPermission("DeathBan.unban")){
				Bukkit.getOfflinePlayer(args[0]).setBanned(false);
				sender.sendMessage("Player Unbanned.");
				return true;
				}else{
					sender.sendMessage("Sorry, you do not have permissions to use this command.");
				}
			}
		}
		return false;
	}
	
	private final DeathBanEntityListener entityListener = new DeathBanEntityListener(this);

}