package net.downwithdestruction.dwdcmd.configuration;

import net.downwithdestruction.dwdcmd.DwDCmd;

import java.util.List;

/**
 * Created by madmac on 11/25/15.
 * <p>
 * Default Config
 */
public enum Config {
    COLOR_LOGS(true),
    DEBUG_MODE(false),
    LANGUAGE_FILE("lang-en.yml");

    private DwDCmd plugin;
    private Object def;

    Config(Object def) {
        this.plugin = DwDCmd.getPlugin(DwDCmd.class);
        this.def = def;
    }

    private String getKey() {
        return name().toLowerCase().replace("_", "-");
    }

    public void set(Object value) {
        plugin.getConfig().set(getKey(), value);
    }

    public int getInt() {
        return plugin.getConfig().getInt(getKey(), (Integer) def);
    }

    public double getDouble() {
        return plugin.getConfig().getDouble(getKey(), (Double) def);
    }

    public String getString() {
        return plugin.getConfig().getString(getKey(), (String) def);
    }

    public List<String> getStringList() {
        return plugin.getConfig().getStringList(getKey());
    }

    public boolean getBoolean() {
        return plugin.getConfig().getBoolean(getKey(), (Boolean) def);
    }


}
