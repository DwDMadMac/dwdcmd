package net.downwithdestruction.dwdcmd.listeners.mobhats;

import net.downwithdestruction.dwdcmd.DwDCmd;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by madmac on 10/7/15.
 * <p>
 * TODO: Add cehck for mobhats being damaged inside a block
 */
public class playerListener implements Listener {
    private DwDCmd plugin;

    public playerListener(DwDCmd plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamageByHat(EntityDamageByEntityEvent e) {
        Entity attacker = e.getDamager();
        Entity damaged = e.getEntity();

        // check if other entity attacks OR shoots a player's hat
        if (damaged.getVehicle() != null && damaged.getVehicle().getType().equals(EntityType.PIG)) {
            e.setCancelled(true);
            return; // other entity attacked/shot passenger (hat)
        }

        // check if player attacks own hat
        if (attacker.getPassenger() != null && attacker.getPassenger().getPassenger() == damaged) {
            e.setCancelled(true);
            return; // player attacked passenger (hat)
        }

        // check for the shootings (goldmember)
        if (attacker instanceof Projectile) {
            Projectile projectile = (Projectile) attacker;
            Entity shooter = (Entity) projectile.getShooter();

            // check if player shoots his own hat
            if (shooter.getPassenger() != null && shooter.getPassenger().getPassenger() == damaged) {
                e.setCancelled(true); // player shot passenger (hat), cancled
            }
        }
    }

    @EventHandler
    public void onPlayerHatTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Entity ent1 = player.getPassenger();

        if (ent1 == null) {
            return; // no entity found on head (pig)
        }

        Entity ent2 = ent1.getPassenger();
        if (ent2 == null) {
            return; // no entity found on entity found on head (hat)
        }

        System.out.println("Hat ejecting");

        ent2.eject();
        ent1.eject();
        player.eject();

        new ReattachHat(player, ent1, ent2).runTaskLater(plugin, 5); // wait 5 ticks (1/4 second) to reattach the hat
    }

    @EventHandler
    public void onHatPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (player.getPassenger() != null && player.getPassenger().getPassenger() != null) {
            player.getPassenger().getPassenger().remove();
            player.getPassenger().remove();
        }

        if (player.getPassenger() != null) {
            player.getPassenger().remove();
        }
    }

    @EventHandler
    public void onMobHatPlayereKick(PlayerKickEvent e) {
        Player player = e.getPlayer();

        if (player.getPassenger() != null && player.getPassenger().getPassenger() != null) {
            player.getPassenger().getPassenger().remove();
            player.getPassenger().remove();
        }

        if (player.getPassenger() != null) {
            player.getPassenger().remove();
        }
    }

    @EventHandler
    public void onPlayerHatDeath(PlayerDeathEvent e) {
        if ((e.getEntity().getPassenger() != null) && (!(e.getEntity().getPassenger() instanceof Player))) {
            e.getEntity().getPassenger().getPassenger().remove();
            e.getEntity().getPassenger().remove();
            e.getDrops().clear();
        }
    }

    public class ReattachHat extends BukkitRunnable {
        private Player player;
        private Entity ent1;
        private Entity ent2;


        public ReattachHat(Player player, Entity ent1, Entity ent2) {
            this.player = player;
            this.ent1 = ent1;
            this.ent2 = ent2;
        }

        public void run() {
            player.setPassenger(ent1);
            ent1.setPassenger(ent2);

            System.out.println("Hat re-attached");
        }

    }
}
