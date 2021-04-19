package it.Mandress101.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import it.Mandress101.StaffPlugin;

public class Configurations {

	private static File logFile;
	private File folder, configFile, messagesFile;
	private FileConfiguration config, messages;
	@SuppressWarnings("unused")
	private String defaultWorld;
	
	public Configurations() {
		defaultWorld = Bukkit.getWorlds().get(0).getName();
		folder = StaffPlugin.getInstance().getDataFolder();
		logFile = new File(folder, "pluginname.log");
		configFile = new File(folder, "config.yml");
		config = new YamlConfiguration();
		messagesFile = new File(folder, "messages.yml");
		messages = new YamlConfiguration();
		
		if (folder.exists() && !logFile.exists())
			createLog();
		
		if (folder.mkdir()) {
			if (!logFile.exists())
				createLog();
			
			PLUTILS.log("Fresh installation? Welcome! Setting up configuration's default values...", 0);
			PLUTILS.log("The \"StaffPlugin\" folder has been created.", 0);
		}
		
        if (!configFile.exists())
			createConfig();
		if (!messagesFile.exists())
			createMessages();
		reload();
		
		if (justUpdated()) {
			createConfig();
			createMessages();
			
			reload();
		}
	}
	
	public void createConfig() {
		if (!create(configFile))
			return;
		
		//defaults
		config.options().header("PluginName's config - DON'T edit the version line, or the plugin will not work.\nVisit the Spigot page for documentation.");
		config.set("version", StaffPlugin.Version); //metti la variabile della versione
		config.set("Author", StaffPlugin.Author);
		
		if(StaffPlugin.isPrivate == true) {
			config.set("Plugin_type", "PRIVATE");
		} else {
			config.set("Plugin_type", "PUBLIC");
		}
		
		//SETTINGS
		config.addDefault("settings.debug", false);
		
		config.options().copyDefaults(true);
		save(config, configFile);
	}
	
	public void createMessages() {
		if (!create(messagesFile))
			return;
		
		//defaults
		messages.options().header("PluginName's messages - here you can set all the in-game messages of the plugin.");
		
		//MISC
		messages.addDefault("messages.prefix", "&bStaff »");
		messages.addDefault("messages.no-permission", "%prefix% &eNon hai il permesso!");
		messages.addDefault("messages.player-offline", "%prefix% &eIl giocatore non è online!");
		
		messages.addDefault("messages.setgmc.self", "%prefix% &eSei entrato in modalità creativa!");
		messages.addDefault("messages.setgms.self", "%prefix% &eSei entrato in modalità sopravvivenza!");
		messages.addDefault("messages.setgma.self", "%prefix% &eSei entrato in modalità avventura!");
		messages.addDefault("messages.setgmsp.self", "%prefix% &eSei entrato in modalità spettatore!");
		
		messages.addDefault("messages.fly.self.on", "%prefix% &eHai attivato la fly");
		messages.addDefault("messages.fly.self.off", "%prefix% &eHai disattivato la fly");
		messages.addDefault("messages.fly.other.on", "%prefix% &eTi è stata attivata la fly!");
		messages.addDefault("messages.fly.other.off", "%prefix% &eTi è stata disattivata la fly!");
		messages.addDefault("messages.fly.response.on", "%prefix% &eHai attivato la fly al giocatore");
		messages.addDefault("messages.fly.response.off", "%prefix% &eHai disattivato la fly al giocatore");
		
		messages.addDefault("messages.setgmc.other", "%prefix% &eSei stato messo in creativa dallo staff!");
		messages.addDefault("messages.setgms.other", "%prefix% &eSei stato messo in survival dallo staff!");
		messages.addDefault("messages.setgma.other", "%prefix% &eSei stato messo in adventure dallo staff!");
		messages.addDefault("messages.setgmsp.other", "%prefix% &eSei stato messo in spectator dallo staff!");
		
		messages.addDefault("messages.setgmc.response", "%prefix% &eIl giocatore è stato messo in cretiva!");
		messages.addDefault("messages.setgms.response", "%prefix% &eIl giocatore è stato messo in survival!");
		messages.addDefault("messages.setgma.response", "%prefix% &eIl giocatore è stato messo in adventure!");
		messages.addDefault("messages.setgmsp.response", "%prefix% &eIl giocatore è stato messo in spectator!");
		
		messages.addDefault("messages.only-player", "%prefix% &eSolo i player posono eseguire questo comando!");
		messages.addDefault("messages.heal.other", "%prefix% &eSei stato curato dallo staff!");
		messages.addDefault("messages.heal.self", "%prefix% &eTi sei curato!");
		messages.addDefault("messages.heal.response", "%prefix% &eIl giocatore è stato curato!");
		messages.addDefault("messages.reload", "%prefix% &eConfig ricaricati!");
		messages.addDefault("messages.cmd-not-found", "%prefix% &eComando non trovato!");
		
		messages.addDefault("playerinfo.usage", "%prefix% &cUtilizzo: &e/playerinfo <giocatore>");
		messages.addDefault("playerinfo.message", "&b&lINFO GIOCATORE\n&eStaff: &b%staff%\n&ePing: &b%ping%");
		
		messages.options().copyDefaults(true);
		save(messages, messagesFile);
	}
    
	public void createLog() {
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			System.err.println("[PluginName] Unable to create \"pluginname.log\".");
			e.printStackTrace();
		}
	}
	
	public static void writeLog(String string) {
		try {
			FileWriter writer = new FileWriter(logFile, true);
			
			writer.write(PLUTILS.getDate("[MM-dd hh:mm:ss]: ") + string + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create(File file) {
		String name = file.getName();
		try {
			if (file.createNewFile())
				PLUTILS.log("The \"" + name + "\" file has been created.", 0);
		} catch (IOException e) {
			PLUTILS.log("Unable to set defaults or create \"" + name + "\".", 2);
			e.printStackTrace();
			return false;
		} return true;
	}

	public void load(FileConfiguration cfg, File file) {
		String name = file.getName();
		try {
			cfg.load(file);
		} catch (InvalidConfigurationException | IOException e) {
			PLUTILS.log("The \"" + name + "\" configuration is invalid and it cannot be loaded.", 2);
			e.printStackTrace();
		}
	}
	
	public void saveAll() {
		long ms = System.currentTimeMillis();
        
		save(config, configFile);
		save(messages, messagesFile);
		PLUTILS.log("Config files saved in " + (System.currentTimeMillis() - ms) + "ms.", 3);
	}
	
	private void save(FileConfiguration cfg, File file) {
		String n = file.getName();
		try {
			cfg.save(file);
		} catch (IOException e) {
			PLUTILS.log("Unable to load \"" + n + "\" file.", 2);
			e.printStackTrace();
		}
	}
	
	public static void save(Configs cfg) {
		String n = cfg.getF().getName();
		try {
			cfg.getC().save(cfg.getF());
		} catch (IOException e) {
			PLUTILS.log("Unable to load \"" + n + "\" file.", 2);
			e.printStackTrace();
		}
	}
	
	public void reload() {
		long ms = System.currentTimeMillis();

		load(config, configFile);
		load(messages, messagesFile);
		PLUTILS.log("Config files loaded in " + (System.currentTimeMillis() - ms) + "ms.", 3);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public File getConfigFile() {
		return configFile;
	}
	
	public FileConfiguration getMessages() {
		return messages;
	}
	
	public File getMessagesFile() {
		return messagesFile;
	}
	
	public boolean justUpdated() {
		return !config.getString("version").equalsIgnoreCase(StaffPlugin.Version); 
	}
	
	public static String getMessage(String message) {
		return PLUTILS.translate(StaffPlugin.getConfigs().getMessages().getString(message).replace("%prefix%", StaffPlugin.getConfigs().getMessages().getString("messages.prefix")));
	}
}
