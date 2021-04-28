package it.Mandress101.Commands;

import it.Mandress101.Utilities.Configurations;
import it.Mandress101.Utilities.PLUTILS;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		label.equalsIgnoreCase("gm");
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("staffplugin.gm")){
				p.sendMessage(Configurations.getMessage("messages.no-permission"));
				return true;
			} else {
				if(args.length == 0) {
					p.sendMessage(PLUTILS.translate("&7&l&m------------------------------"));
					p.sendMessage(PLUTILS.translate("&b&lSTAFF > &9&lGAMEMODE"));
					p.sendMessage(PLUTILS.translate("&3&l> &b/gm 0 &f| &eGamemode survival"));
					p.sendMessage(PLUTILS.translate("&3&l> &b/gm 1 &f| &eGamemode creative"));
					p.sendMessage(PLUTILS.translate("&3&l> &b/gm 2 &f| &eGamemode adventure"));
					p.sendMessage(PLUTILS.translate("&3&l> &b/gm 3 &f| &eGamemode spcetator"));
					p.sendMessage(PLUTILS.translate("&7&l&m------------------------------"));
				} else {
					switch (args[0].toLowerCase()) {
					case "0":
						if(p.hasPermission("staffplugin.gms")) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(Configurations.getMessage("messages.setgms.self"));
						} else {
							p.sendMessage(Configurations.getMessage("messages.no-permission"));
						}
						break;
						
					case "1":
						if(p.hasPermission("staffplugin.gmc")) {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(Configurations.getMessage("messages.setgmc.self"));
						} else {
							p.sendMessage(Configurations.getMessage("messages.no-permission"));
						}
						break;
						
					case "2":
						if(p.hasPermission("staffplugin.gma")) {
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage(Configurations.getMessage("messages.setgma.self"));
						} else {
							p.sendMessage(Configurations.getMessage("messages.no-permission"));
						}
						break;
					
					case "3":
						if(p.hasPermission("staffplugin.gmsp")) {
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(Configurations.getMessage("messages.setgmsp.self"));
						} else {
							p.sendMessage(Configurations.getMessage("messages.no-permission"));
						}
						break;
						
					default:
						sender.sendMessage(Configurations.getMessage("messages.cmd-not-found"));
						break;
					}
				}
			}
		} else {
			System.out.println(Configurations.getMessage("messages.only-player"));
		}
		return false;
	}
	

}
