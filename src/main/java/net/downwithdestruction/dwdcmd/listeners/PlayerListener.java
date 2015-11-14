package net.downwithdestruction.dwdcmd.listeners;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * Created by madmac on 9/30/15.
 *
 * TODO: Add check for mobhat Entity Drops
 * TODO: Add check for mobhat player Entity Damage
 * TODO: Fix player not being able to teleport when Mob Hat is on
 *
 * ** Special Abilities **
 *
 * TODO: Ocelot, Fall damage immunity
 */
public class PlayerListener implements Listener {
    private DwDCmd plugin;
    private String DwD = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "DwD" + ChatColor.DARK_RED + "] ";


    public PlayerListener(DwDCmd plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerFlyChangedWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        p.setFlySpeed(0.1F); // Set players flyspeed back to normal
    }

/*
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerHatTeleport(PlayerTeleportEvent e){
        Player player = e.getPlayer();

        // Remove players Mob Hat
        if( player.getPassenger() != null && player.getPassenger().getPassenger() != null ){
            player.getPassenger().getPassenger().remove();
            player.getPassenger().remove();
            player.getPassenger().getPassenger().eject();
            player.getPassenger().eject();
            player.teleport(player.getLocation());
            return;
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerHatChangeWorld(PlayerChangedWorldEvent e){
        Player player = e.getPlayer();

        protocolManager.addPacketListener(playerListener());

        // Remove players Mob Hat
        if( player.getPassenger() != null && player.getPassenger().getPassenger() != null ){
            e.getPlayer().getPassenger().getPassenger().remove();
            e.getPlayer().getPassenger().remove();
            player.getPassenger().getPassenger().eject();
            player.getPassenger().eject();
            player.teleport(player.getLocation());
            return;
        }
    }
*/


/*
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerHatTeleport(PlayerTeleportEvent e){
        Player player = (Player) e.getPlayer();
        //LivingEntity mobHATT = (LivingEntity) player.getPassenger();

        System.out.println(player.getLocation().toString() + " Numer one");

        if(player.getPassenger() == null || player.getPassenger().getPassenger() == null){ return; }
        player.sendMessage(ChatColor.AQUA + "player vehcile workeds!");

        * This works but Mob Hat runs away! :(
        player.setPassenger(player.getPassenger().getPassenger());
        player.setPassenger(player.getPassenger());
        player.setPassenger(player.getPassenger());
        player.setPassenger(player.getPassenger().getPassenger());
        *

        player.getPassenger().getPassenger().remove();
        player.getPassenger().remove();

        System.out.println(player.getLocation().toString() + " Number Two");

        player.sendMessage(ChatColor.GREEN + "player vehcile workeds!");



        *
        mobHATT.getPassenger().eject();
        mobHATT.eject();
        player.sendMessage(ChatColor.BLUE + "player vehcile ejected!");
        mobHATT.getPassenger().remove();
        mobHATT.remove();
        player.sendMessage(ChatColor.DARK_RED + "player vehcile removed!");
        *

        *
        if(mobHATT == null){ return; }

        if(mobHATT.isInsideVehicle()){
            player.sendMessage(ChatColor.RED + "player vehcile workeds!");
                player.sendMessage(ChatColor.BLUE + "player vehcile workeds!");
                if( (e.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND)
                        || (e.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL)
                        || (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL)
                        || (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL)
                        || (e.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN)
                        || (e.getCause() == PlayerTeleportEvent.TeleportCause.UNKNOWN)){
                    player.sendMessage(ChatColor.GREEN + "player vehcile workeds!");
                    mobHATT.teleport(e.getTo());
                    player.teleport(mobHATT);
                    player.setPassenger(mobHATT);
                }
            return;
        }

        if(mobHATT instanceof Entity){
            if( mobHATT != null || mobHATT.getPassenger() != null){
                player.sendMessage(ChatColor.GREEN + "onPlayerHatTeleport mobHATT has been enabled!");
                e.setCancelled(false);
                e.getTo().getWorld().spawn(player.getLocation(), mobHATT.getClass());
                mobHATT.getLocation(player.getLocation());
                mobHATT.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
                player.sendMessage(ChatColor.GREEN + "onPlayerHatTeleport mobHATT has been enabled! TWO");
            }
            return;
        }
        *


        *
        if(player.getPassenger().getPassenger() != null || player.getPassenger() != null){
            Entity mobHat = (Entity) player.getPassenger().getPassenger();
            new EntityTeleportEvent(mobHat, player.getLocation(), player.getLocation());

            player.sendMessage(ChatColor.GREEN + "onPlayerHatTeleport has been enabled!");
            //player.getPassenger().getPassenger().remove();
            //player.getPassenger().remove();
            return;
        }
        *
    }
*/

/*
    @EventHandler
    public void onDamageByHat(EntityDamageByEntityEvent e){
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
        if(attacker instanceof Projectile){
            Projectile projectile = (Projectile) attacker;
            Entity shooter = (Entity) projectile.getShooter();

            // check if player shoots his own hat
            if (shooter.getPassenger() != null && shooter.getPassenger().getPassenger() == damaged) {
                e.setCancelled(true);
                return; // player shot passenger (hat)
            }
        }
    }

    @EventHandler
    public void onHatDamage(EntityDamageEvent e){
        if(e.getEntity().getVehicle() instanceof Player){
            e.setCancelled(true);
            return;
        }

        if( (e instanceof EntityDamageByEntityEvent) ){
            EntityDamageByEntityEvent EDBEE = (EntityDamageByEntityEvent) e;
            Entity entityAttacker = EDBEE.getDamager();
            Entity attackedPlayer = EDBEE.getEntity();

            if(entityAttacker instanceof Player){
                Player playerAttacker = (Player) entityAttacker;

                if(playerAttacker.getPassenger() != null || playerAttacker.getPassenger().getPassenger() != null){
                    e.setCancelled(true);
                    return;
                }
            }

            if(entityAttacker instanceof Projectile){
                Projectile thrownObject = (Projectile) EDBEE.getDamager();
                Entity entityThrower = (Entity) thrownObject.getShooter();

                if(entityThrower == attackedPlayer){
                    e.setCancelled(true);
                    return;
                }

                if(entityThrower.getPassenger() != null && entityThrower.getPassenger().getPassenger() == attackedPlayer){
                    e.setCancelled(true);
                    return;
                }

            }
        }
    }

*/
}
