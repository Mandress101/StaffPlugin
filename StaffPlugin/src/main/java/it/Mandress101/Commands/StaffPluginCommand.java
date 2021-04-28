package it.Mandress101.Commands;

import it.Mandress101.StaffPlugin;
import it.Mandress101.Utilities.Configurations;
import it.Mandress101.Utilities.PLUTILS;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StaffPluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        label.equalsIgnoreCase("staffplugin");
        if (sender.hasPermission("staffplugin.main")) {
            if (args.length == 0) {
                sender.sendMessage(PLUTILS.translate("&e&lRunning &b&lStaffPlugin &e&lVersion: &b&l" + StaffPlugin.Version + " &e&lby &b&l" + StaffPlugin.Author));
                sender.sendMessage(PLUTILS.translate("&cCommands:"));
                sender.sendMessage(PLUTILS.translate("&b/staffplugin reload | &eReload the plugin"));
                sender.sendMessage(PLUTILS.translate("&b/staffplugin help | &ePlugin help"));
                sender.sendMessage(PLUTILS.translate("&b/staffplugin info | &ePlugin info"));
                sender.sendMessage(PLUTILS.translate("&b/staffplugin support | &eSupport for plugin"));
                if (StaffPlugin.isPrivate == true) {
                    sender.sendMessage(PLUTILS.translate("&b/staffplugin update | &eSupport for plugin &c&l(ONLY PRIVATE)"));
                }

            } else {
                switch (args[0].toLowerCase()) {
                    case "reload":
                        if (sender.hasPermission("staffplugin.reload")) {
                            StaffPlugin.getConfigs().reload();
                            sender.sendMessage(Configurations.getMessage("messages.reload"));
                            PLUTILS.log("Config reloaded", 1);
                        } else {
                            sender.sendMessage(Configurations.getMessage("messages.no-permission"));
                        }
                        break;

                    case "info":
                        sender.sendMessage(PLUTILS.translate(
                                "&7&l&m------------------------------" + "\n" +
                                        "&b&lStaffPlugin &e" + "Version: " + StaffPlugin.Version + "\n" +
                                        "&7Questo plugin è stato siluppato da &c" + StaffPlugin.Author + "\n" +
                                        "&7come plugin per lo staff di SiteGames network" + "\n" +
                                        "&7&l&m------------------------------"
                        ));
                        break;

                    case "support":
                        sender.sendMessage(PLUTILS.translate(
                                "" + "\n" +
                                        "&6Support contact: &5Mandress#5855" + "\n" +
                                        "&6Discord: &ahttps://discord.gg/SbwWSTwHYu"
                        ));
                        break;

                    case "help":
                        sender.sendMessage(PLUTILS.translate(
                                "&7&l&m------------------------------" + "\n" +
                                        "&b&lSTAFFPLUGIN &e&lCOMMANDS LIST" + "\n" +
                                        "&b&lVersion: &e" + StaffPlugin.Version + " by: &b" + StaffPlugin.Author + "\n" +
                                        "" + "\n" +
                                        "&b/staffplugin &f| &eMain command" + "\n" +
                                        "&b/gm (0,1,2,3) &f| &eCambia gamemode" + "\n" +
                                        "&b/gmc /gms /gmsp /gma &f| &eCambia gamemode" + "\n" +
                                        "&b/staffvanish &f| &eEntra in vanish &c(Coming SOON)" + "\n" +
                                        "&b/freeze &f| &eFrizza qualcuno &c(Coming SOON)" + "\n" +
                                        "&b/staffly &f| &eAttiva il volo" + "\n" +
                                        "&7&l&m------------------------------" + "\n"
                        ));
                        break;

                    case "update":
                        if (StaffPlugin.isPrivate == true) {
                            sender.sendMessage(PLUTILS.translate(
                                    "&6Plugin update link: &cConig SOON"
                            ));
                        } else {
                            sender.sendMessage(Configurations.getMessage("messages.cmd-not-found"));
                        }
                        break;

                    default:
                        sender.sendMessage(Configurations.getMessage("messages.cmd-not-found"));
                        break;
                }
            }

        } else {
            if (StaffPlugin.isPrivate == true) {
                sender.sendMessage(PLUTILS.translate("&e&lRunning &b&lStaffPlugin &e&lVersion: &b&l" + StaffPlugin.Version + " &e&lby &b&l" + StaffPlugin.Author));
            } else {
                sender.sendMessage(PLUTILS.translate("&e&lRunning &b&lStaffPlugin &e&lVersion: &b&l" + StaffPlugin.Version + " &e&lby &b&l" + StaffPlugin.Author));
            }
        }
        return true;
    }

}
