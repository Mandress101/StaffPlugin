package it.Mandress101;

import org.bukkit.plugin.java.JavaPlugin;

import it.Mandress101.Commands.GamemodeCommands;
import it.Mandress101.Commands.GmaCommand;
import it.Mandress101.Commands.GmcCommand;
import it.Mandress101.Commands.GmsCommand;
import it.Mandress101.Commands.GmspCommand;
import it.Mandress101.Commands.HealCommand;
import it.Mandress101.Commands.StaffPluginCommand;
import it.Mandress101.Commands.Staffly;
import it.Mandress101.Utilities.Configurations;


public class StaffPlugin extends JavaPlugin{
	
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
