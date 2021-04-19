package it.Mandress101.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.Mandress101.Utilities.Configurations;

public class GmsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		label.equalsIgnoreCase("gms");
		Player p = (Player) sender;
		if(sender instanceof Player) {
			if(p.hasPermission("staffplugin.gms")) {
				if(args.length == 0) {
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(Configurations.getMessage("messages.setgms.self"));
				} else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if(target != null) {
						target.setGameMode(GameMode.SURVIVAL);
						target.sendRawMessage(Configurations.getMessage("messages.setgms.other"));
						p.sendMessage(Configurations.getMessage("messages.setgms.response"));
					} else {
						p.sendMessage(Configurations.getMessage("messages.player-offline"));
					}
				}
			} else {
				p.sendMessage(Configurations.getMessage("messages.no-permission"));
			}
		} else {
			sender.sendMessage(Configurations.getMessage("messages.only-player"));
		}
		return false;
	}

}

