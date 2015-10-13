package net.downwithdestruction.dwdcmd;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.downwithdestruction.dwdcmd.commands.Fly;
import net.downwithdestruction.dwdcmd.commands.Hat;
import net.downwithdestruction.dwdcmd.listeners.PlayerListener;
import net.downwithdestruction.dwdcmd.listeners.mobhats.batAbility;
import net.downwithdestruction.dwdcmd.listeners.mobhats.mobHatSelector;
import net.downwithdestruction.dwdcmd.listeners.mobhats.packets.pigPackets;
import net.downwithdestruction.dwdcmd.listeners.mobhats.playerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by madmac on 9/20/15.
 * <p>
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
    private ProtocolManager protocolManager;
    private pigPackets pigpackets;

    public DwDCmd() {
        instance = this;
    }

    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();

        pigpackets = new pigPackets(getServer());
        pigpackets.onMobHatPigSound(protocolManager, this);


        // Global
        pm.registerEvents(new PlayerListener(this), this);
        // Mob Hats
        pm.registerEvents(new playerListener(this), this);
        pm.registerEvents(new mobHatSelector(this), this);
        pm.registerEvents(new batAbility(this), this);

        registerCommands();
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        pigpackets.onMobHatPigSoundRemoval(protocolManager, this);

        for (RegisteredListener listener : HandlerList.getRegisteredListeners(this)) {
            try {
                ((HandlerList) listener.getClass().getMethod("getHandlerList").invoke(null)).unregister(listener);
            } catch (Exception disabledException) {
                // Seeing if this fixes MobHats from not being removed on server shutdown
            }
        }
    }

    public void registerCommands() {
        this.getCommand("fly").setExecutor(new Fly(this));
        this.getCommand("flyspeed").setExecutor(new Fly(this));
        this.getCommand("helmet").setExecutor(new Hat(this));
        this.getCommand("mobhat").setExecutor(new Hat(this));
    }
}
