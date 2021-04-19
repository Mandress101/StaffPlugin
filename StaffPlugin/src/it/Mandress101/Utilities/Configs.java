package it.Mandress101.Utilities;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import it.Mandress101.StaffPlugin;

public enum Configs {

    CONFIG(StaffPlugin.CONFIGS.getConfig(), StaffPlugin.CONFIGS.getConfigFile()),
    MESSAGES(StaffPlugin.CONFIGS.getMessages(), StaffPlugin.CONFIGS.getMessagesFile());
    
    private File f;
    private FileConfiguration c;
    
    private Configs(FileConfiguration c, File f) {
        this.c = c;
        this.f = f;
    }
    
    public FileConfiguration getC() {
        return c;
    }
    
    public File getF() {
        return f;
    }
}