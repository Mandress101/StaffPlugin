package it.Mandress101.Commands;

import it.Mandress101.Utilities.Configurations;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		label.equalsIgnoreCase("heal");
		if(sender instanceof Player) {
			if(p.hasPermission("staffplugin.heal")) {
				if(args.length == 0) {
					p.setHealth(20.0);
					p.sendMessage(Configurations.getMessage("messages.heal.self"));
				} else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if(target != null) {
						target.setHealth(20.0);
						target.sendMessage(Configurations.getMessage("messages.heal.other"));
						p.sendMessage(Configurations.getMessage("messages.heal.response"));
					} else {
						p.sendMessage(Configurations.getMessage("messages.player-offline"));
					}
				}
			} else {
				p.sendMessage(Configurations.getMessage("messages.no-permission"));
			}
		} else {
			System.out.println(Configurations.getMessage("messages.only-player"));
		}
		return false;
	}
	
}
