package net.downwithdestruction.dwdcmd.configuration;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by madmac on 11/25/15.
 * <p>
 * books Config
 */
public enum BooksConfig {
    BOOK_WRITTEN_BY(null),
    TITLE(null),
    CONTENT(null);

    private DwDCmd plugin;
    private Object def;

    BooksConfig(Object def) {
        this.plugin = DwDCmd.getPlugin(DwDCmd.class);
        this.def = def;
    }

    public static void deleteFile(String bookName) {
        try {
            File dir = new File(DwDCmd.getPlugin(DwDCmd.class).getDataFolder(), "books");

            if (!dir.exists()) {

                if (!dir.mkdir()) {
                    // TODO: Create Logger
                    System.out.println(ChatColor.RED + "Unable to create book data directory: " + ChatColor.GOLD + dir.getAbsolutePath());
                    return;
                }
            }
            File file = new File(dir, bookName + ".yml");
            if (!file.exists()) {
                return;
            }

            if (!file.delete()) {
                // TODO: Create Logger
                System.out.println(ChatColor.RED + "Unable to delete book data file: " + ChatColor.GOLD + ".yml");
            }
        } catch (Exception e) {
            // TODO: Create Logger
            System.out.println(ChatColor.RED + "Unable to delete city data file: " + bookName + ".yml");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private String getKey() {
        return name().toLowerCase().replace("_", "-");
    }

    public void set(String bookName, Object value) {
        saveConfig(bookName, value);
    }

    public String getString(String bookName) {
        return getConfig(bookName).getString(getKey(), (String) def);
    }

    public List<String> getStringList(String bookName) {
        return getConfig(bookName).getStringList(getKey());
    }

    public List<Map<?, ?>> getMapList(String bookName) {
        return getConfig(bookName).getMapList(getKey());
    }

    public Map<String, ?> getMap(String bookName) {
        ConfigurationSection configurationSection = getConfig(bookName).getConfigurationSection(getKey());

        if (configurationSection == null) {
            return null;
        }

        return configurationSection.getValues(false);
    }

    public boolean getBoolean(String bookName) {
        return getConfig(bookName).getBoolean(getKey(), (Boolean) def);
    }

    public int getInteger(String bookName) {
        return getConfig(bookName).getInt(getKey(), (Integer) def);
    }

    public double getDouble(String bookName) {
        return getConfig(bookName).getDouble(getKey(), (Double) def);
    }

    private YamlConfiguration getConfig(String bookName) {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        try {
            yamlConfiguration.load(new File(plugin.getDataFolder(), "books" + File.separator + bookName + ".yml"));
        } catch (Exception e) {
            // TODO: Create Logger
            System.out.println(ChatColor.RED + "Error loading city config file!");
            e.printStackTrace();
        }

        return yamlConfiguration;
    }

    private void saveConfig(String bookName, Object value) {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        try {
            File dir = new File(plugin.getDataFolder(), "books");

            if (!dir.exists()) {

                if (!dir.mkdir()) {
                    // TODO: Create Logger
                    System.out.println(ChatColor.RED + "Unable to create book data directory: " + ChatColor.GOLD + dir.getAbsolutePath());
                    return;
                }
            }
            File file = new File(dir, bookName + ".yml");

            if (!file.exists()) {

                if (!file.createNewFile()) {
                    // TODO: Create Logger
                    System.out.println(ChatColor.RED + "Unable to create city data file: " + ChatColor.GOLD + file.getAbsolutePath());
                    return;
                }
            }
            yamlConfiguration.load(file);
            yamlConfiguration.set(getKey(), value);
            yamlConfiguration.save(file);
        } catch (Exception e) {
            // TODO: Create Logger
            System.out.println(ChatColor.RED + "Unable to save book data: " + ChatColor.GOLD + bookName + ": " + getKey() + ": " + value);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
