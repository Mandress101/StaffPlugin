package it.Mandress101.Commands;

import it.Mandress101.Utilities.Configurations;
import it.Mandress101.Utilities.PLUTILS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfo implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		label.equalsIgnoreCase("playerinfo");
		if(sender.hasPermission("staffplugin.playerinfo")) {
			Player p = (Player) sender;
			if(args.length == 0) {
				sender.sendMessage(Configurations.getMessage("playerinfo.usage"));
			} else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if(target != null) {
					p.sendMessage(PLUTILS.translate("&7&l&m------------------------------ "));
					p.sendMessage(PLUTILS.translate("&b&lInfo su » &3&l" + target.getName()));
						if(target.hasPermission("staffplugin.staff")){
							p.sendMessage(PLUTILS.translate("&3&l> Staff &f| &a&lTrue"));
						}else{
							p.sendMessage(PLUTILS.translate("&3&l> Staff &f| &c&lFalse"));
						}
					p.sendMessage(PLUTILS.translate("&3&l> GameMode &f| &e" + target.getGameMode()));
					p.sendMessage(PLUTILS.translate("&3&l> Vita &f| &e" + target.getHealth() ));
						if(p.isBanned()) {
							p.sendMessage(PLUTILS.translate("&3&l> Bannato &f| &a&lTrue"));
						}else{
							p.sendMessage(PLUTILS.translate("&3&l> Bannato &f| &c&lFalse"));
						}
					p.sendMessage(PLUTILS.translate("&3&l> IP &f| &e" + p.getAddress().getAddress().getHostAddress()));
					p.sendMessage(PLUTILS.translate("&7&l&m------------------------------"));
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
