package it.Mandress101.Utilities;

import it.Mandress101.StaffPlugin;
import org.bukkit.ChatColor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.Logger;

public class PLUTILS {

	public static final Logger LOGGER = StaffPlugin.getInstance().getLogger();
	
	public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
	
	public static void log(String msg, int severity) {
		if (StaffPlugin.getConfigs() == null) {
	  		  severity = 0;
	        }
        switch (severity) {
        case 0:
            LOGGER.info(msg);
            msg = "[INFO] " + msg;
            break;
        case 1:
            LOGGER.warning(msg);
            msg = "[WARN] " + msg;
            break;
        case 2:
            LOGGER.severe(msg);
            msg = "[ERROR] " + msg;
            break;
        case 3:
            msg = "[DEBUG] " + msg;
            if (getBoolean(Configs.CONFIG, "settings.debug"))
                LOGGER.info(msg);
            msg = "[INFO] " + msg;
        } Configurations.writeLog(msg);
    }
	
	public static boolean getBoolean(Configs config, String path) {
		  return config.getC().getBoolean(path);
		}

	public static String getDate(String format) {
        return new SimpleDateFormat(format).format(new Date(Instant.now().toEpochMilli()));
  }
	
}
