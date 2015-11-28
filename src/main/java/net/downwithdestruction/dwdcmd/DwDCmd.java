package net.downwithdestruction.dwdcmd;

import net.downwithdestruction.dwdcmd.commands.Books;
import net.downwithdestruction.dwdcmd.commands.Fly;
import net.downwithdestruction.dwdcmd.commands.Hat;
import net.downwithdestruction.dwdcmd.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by madmac on 9/20/15.
 *
 * TODO Command: hat
 * TODO Command: inventory see command
 * TODO Command: kit
 * TODO Command: MAYBE, list command
 * TODO Command: nickname
 * TODO Command: repair command with economy
 * TODO Command: seen
 * TODO Command: suicide
 * TODO Command: backpack
 * TODO Command: ride
 * TODO Command: walkspeed
 * TODO Coomand: Book
 */
public class DwDCmd extends JavaPlugin {
    private final PluginManager pm = Bukkit.getPluginManager();
    public DwDCmd instance;

    public DwDCmd() {
        instance = this;
    }

    @Override
    public void onEnable() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!(configFile.exists())) {
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
        }

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
        this.getCommand("book").setExecutor(new Books(this));
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
