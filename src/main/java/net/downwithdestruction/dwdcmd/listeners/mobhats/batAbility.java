package net.downwithdestruction.dwdcmd.listeners.mobhats;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Created by madmac on 10/5/15.
 */
public class batAbility implements Listener {
    private DwDCmd plugin;
    private String DwD = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "DwD" + ChatColor.DARK_RED + "] ";

    public batAbility(DwDCmd plugin) {
        this.plugin = plugin;
    }

    public void onMobHatBAT(Player player) {
        if (player.getPassenger() != null && player.getPassenger().getPassenger().getType() == EntityType.BAT) {
            Location location = player.getPlayer().getLocation();
            location = location.subtract(0, 1, 0);

            Block block = location.getBlock();

            if (!block.getType().isSolid()) return;

            player.setAllowFlight(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMobHatBATPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getPassenger() == null) return;

        if (player.getPassenger().getPassenger() == null) return;

        if (player.getPassenger().getPassenger().getType() == EntityType.BAT) {
            if (player.getAllowFlight()) return;
            this.onMobHatBAT(e.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMobHatBATPlayerDamage(EntityDamageEvent e) {
        if (e.getEntityType() != EntityType.PLAYER && e.getEntityType().getEntityClass() != Bat.class) return;
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMobHatBATPlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();

        if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;

        if (player.getPassenger() == null) return;

        if (player.getPassenger().getPassenger() == null) return;

        if (player.getPassenger().getType() == EntityType.PIG && player.getPassenger().getPassenger().getType() == EntityType.BAT) {
            e.setCancelled(true);
            player.setAllowFlight(false);
            player.setVelocity(player.getLocation().getDirection().multiply(1.6D).setY(1.0D));
        }
    }
}
