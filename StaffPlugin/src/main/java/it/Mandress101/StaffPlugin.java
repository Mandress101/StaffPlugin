package it.Mandress101;

import it.Mandress101.Commands.*;
import it.Mandress101.Utilities.Configurations;
import org.bukkit.plugin.java.JavaPlugin;


public class StaffPlugin extends JavaPlugin {

    public static StaffPlugin instance;

    public static String Version = "0.7.1";

    public static String Author = "Mandress101";

    public static boolean isPrivate = true;

    public static Configurations CONFIGS;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println((
                "&bStaffPlugin > &aEnabled" + "\n" +
                        "&eBy &bMandress101 &eVersion: " + Version
        ).replace("&", "\247"));
        this.getCommand("staffplugin").setExecutor(new StaffPluginCommand());
        this.getCommand("gm").setExecutor(new GamemodeCommands());
        this.getCommand("gms").setExecutor(new GmsCommand());
        this.getCommand("gmc").setExecutor(new GmcCommand());
        this.getCommand("gma").setExecutor(new GmaCommand());
        this.getCommand("gmsp").setExecutor(new GmspCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("staffly").setExecutor(new Staffly());
        this.getCommand("playerinfo").setExecutor(new PlayerInfo());
        CONFIGS = new Configurations();
    }

    @Override
    public void onDisable() {
        System.out.println((
                "&bStaffPlugin > &cDisabled"
        ).replace("&", "\247"));
        CONFIGS.saveAll();
    }

    public static StaffPlugin getInstance() {
        return instance;
    }

    public static Configurations getConfigs() {
        return CONFIGS;
    }

}
