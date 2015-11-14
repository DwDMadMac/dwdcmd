package net.downwithdestruction.dwdcmd;

import net.downwithdestruction.dwdcmd.commands.Fly;
import net.downwithdestruction.dwdcmd.commands.Hat;
import net.downwithdestruction.dwdcmd.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by madmac on 9/20/15.
 *
 * TODO Commands: hat
 * TODO Commands: inventory see command
 * TODO Commands: kit
 * TODO Commands: MAYBE, list command
 * TODO Commands: nickname
 * TODO Commands: repair command with economy
 * TODO Commands: seen
 * TODO Commands: suicide
 * TODO Commands: backpack
 * TODO Commands: ride
 * TODO Commands: walkspeed
 */
public class DwDCmd extends JavaPlugin {
    public static DwDCmd instance;
    private final PluginManager pm = Bukkit.getPluginManager();

    public DwDCmd() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Global
        pm.registerEvents(new PlayerListener(this), this);

        registerCommands();
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }

    public void registerCommands() {
        this.getCommand("fly").setExecutor(new Fly(this));
        this.getCommand("flyspeed").setExecutor(new Fly(this));
        this.getCommand("helmet").setExecutor(new Hat(this));
    }
}
