package it.Mandress101.Commands;

import it.Mandress101.Utilities.Configurations;
import it.Mandress101.Utilities.PLUTILS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Staffly implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		label.equalsIgnoreCase("staffly");
		Player p = (Player) sender;
		
		if(sender instanceof Player) {
			if(p.hasPermission("staffplugin.fly")) {
				if(args.length == 0) {
					p.sendMessage(PLUTILS.translate(
							"&7&l&m------------------------------" + "\n" +
							"&b&lFLY &e&lCOMMANDS" + "\n" +
							"&3&l> &b/staffly on &f| &eAttiva la fly" + "\n" +
							"&3&l> &b/staffly off &f| &eDisattiva la fly" + "\n" +
							"&3&l> &b/staffly on <giocatore> &f| &eAttiva la fly ad un player" + "\n" +
							"&3&l> &b/staffly off <giocatore> &f| &eDisattiva la fly" + "\n" +
							"&7&l&m------------------------------"
							));
				} else {
					switch (args[0].toLowerCase()) {
					case "on":
						p.sendMessage(Configurations.getMessage("messages.fly.self.on"));
						p.setAllowFlight(true);
						break;
						
					case "off":
						p.sendMessage(Configurations.getMessage("messages.fly.self.off"));
						p.setAllowFlight(false);
						break;
						
					default:
						p.sendMessage(Configurations.getMessage("messages.cmd-not-found"));
						break;
					}
				}
				if(args.length == 2) {
					Player target = Bukkit.getPlayerExact(args[1]);
					if(target != null) {
						switch (args[0].toLowerCase()) {
						case "on":
							p.sendMessage(Configurations.getMessage("messages.fly.response.on"));
							target.sendMessage(Configurations.getMessage("messages.fly.other.on"));
							target.setAllowFlight(true);
							break;
							
						case "off":
							p.sendMessage(Configurations.getMessage("messages.fly.response.off"));
							target.sendMessage(Configurations.getMessage("messages.fly.other.off"));
							target.setAllowFlight(false);
							break;

						default:
							p.sendMessage(Configurations.getMessage("messages.cmd-not-found"));
							break;
						}
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
