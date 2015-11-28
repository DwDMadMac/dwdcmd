package net.downwithdestruction.dwdcmd.configuration;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by madmac on 11/28/15.
 */
public enum Lang {
    UNKNOWN_ERROR("An unknown error has happened!"),

    // INFO: Books Class
    NO_PERMISIONS_GIVE("&6You do not have permission to use /book give <BookName>"),
    NO_PERMISSIONS_DELETE("&6You do not have permission to use /book delete <BookName>");

    private DwDCmd plugin;
    private String def;

    private File configFile;
    private FileConfiguration config;

    Lang(String def) {
        this.plugin = DwDCmd.getPlugin(DwDCmd.class);
        this.def = def;
        configFile = new File(plugin.getDataFolder(), Config.LANGUAGE_FILE.getString());
        saveDefault();
        reload();
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    private void saveDefault() {
        if (!configFile.exists()) {
            plugin.saveResource(Config.LANGUAGE_FILE.getString(), false);
        }
    }

    private String getKey() {
        return name().toLowerCase().replace("_", "-");
    }

    @Override
    public String toString() {
        String value = config.getString(getKey(), def);
        if (value == null) {
            // TODO: Creaate Logger
            value = "[Missing lang daata]";
        }
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public String replace(String find, String replace) {
        return toString().replace(find, replace);
    }

}
