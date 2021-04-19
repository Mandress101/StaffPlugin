package it.Mandress101.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.Mandress101.Utilities.Configurations;

public class PlayerInfo implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		label.equalsIgnoreCase("playerinfo");
		if(sender.hasPermission("staffplugin.playerinfo")) {
			if(args.length == 0) {
				sender.sendMessage(Configurations.getMessage("playerinfo.usage"));
			} else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if(target != null) {
					
				} else {
					sender.sendMessage(Configurations.getMessage("playerinfo.usage"));
				}
			}
		} else {
			sender.sendMessage(Configurations.getMessage("messages.no-permission"));
		}
		return false;
	}

}
