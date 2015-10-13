package net.downwithdestruction.dwdcmd.listeners.mobhats.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by madmac on 10/7/15.
 */
public class pigPackets {

    private Server server;

    public pigPackets(Server server) {
        this.server = server;
    }

    public void onMobHatPigSound(ProtocolManager protocolManager, JavaPlugin plugin) {
        protocolManager.addPacketListener(
                new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        Player player = event.getPlayer();

                        if (player.getPassenger() == null) return;

                        if (player.getPassenger().getType() != EntityType.PIG) return;

                        try {
                            if (event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                                if (event.getPacket().getStrings().read(0).equalsIgnoreCase("mob.pig.say")) {
                                    event.setCancelled(true);
                                }
                            }
                        } catch (FieldAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public Server getServer() {
        return server;
    }

    public void onMobHatPigSoundRemoval(ProtocolManager protocolManager, JavaPlugin plugin) {
        protocolManager.removePacketListeners(plugin);
    }
}
