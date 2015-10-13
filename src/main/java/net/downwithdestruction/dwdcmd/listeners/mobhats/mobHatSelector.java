package net.downwithdestruction.dwdcmd.listeners.mobhats;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by madmac on 10/7/15.
 */
public class mobHatSelector implements Listener {
    private String DwD = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "DwD" + ChatColor.DARK_RED + "] ";
    private DwDCmd plugin;

    public mobHatSelector(DwDCmd plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHatInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        PacketPlayOutTitle title = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE, // Title or Sub-Title
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"Sorry\",\"color\":\"dark_red\"}"),// Text
                10, // Fade-in (ticks)
                10, // Display Time (ticks)
                10  // Fade-out (ticks)
        );
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE, // Title or Sub-Title
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"This Mob Hat is restricted, \",\"extra\":[{\"text\":\"§btry again!\"}],\"color\":\"red\"}"),// Text
                40, // Fade-in (ticks)
                40, // Display Time (ticks)
                40  // Fade-out (ticks)
        );

        if ((e.getClickedInventory() == null) && (e.getInventory().getName().contains("Mob Hats:"))) {
            return;
        }

        if ((e.getInventory().getHolder() == null) && (e.getCurrentItem().hasItemMeta()) && (e.getInventory().getName().contains("Mob Hats:"))) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                player.closeInventory();
                return;
            }

            if (player.getPassenger() == null) {
                if (e.getCurrentItem().getDurability() == 92) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.cow")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Cow cow = (Cow) player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
                        pig.setPassenger(cow);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 93) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.chicken")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Chicken chicken = (Chicken) player.getWorld().spawnEntity(player.getLocation(), EntityType.CHICKEN);
                        pig.setPassenger(chicken);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 94) {
                    // TODO: Add check for breath, Squid dies becuse of breath
                    if ((player.hasPermission("dwdcmd.command.mobhat.squid")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Squid squid = (Squid) player.getWorld().spawnEntity(player.getLocation(), EntityType.SQUID);
                        pig.setPassenger(squid);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 96) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.mooshroom")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        MushroomCow mushroomCow = (MushroomCow) player.getWorld().spawnEntity(player.getLocation(), EntityType.MUSHROOM_COW);
                        pig.setPassenger(mushroomCow);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 90) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.pig")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Pig pigMob = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.setPassenger(pigMob);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 91) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.sheep")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Sheep sheep = (Sheep) player.getWorld().spawnEntity(player.getLocation(), EntityType.SHEEP);
                        pig.setPassenger(sheep);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 57) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.pigman")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        PigZombie pigZombie = (PigZombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG_ZOMBIE);
                        pig.setPassenger(pigZombie);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 98) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.ocelot")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Ocelot ocelot = (Ocelot) player.getWorld().spawnEntity(player.getLocation(), EntityType.OCELOT);
                        pig.setPassenger(ocelot);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 51) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.skeleton")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Skeleton skeleton = (Skeleton) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                        pig.setPassenger(skeleton);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 59) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.cavespider")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        CaveSpider caveSpider = (CaveSpider) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
                        pig.setPassenger(caveSpider);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 60) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.silverfish")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Silverfish silverFish = (Silverfish) player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);
                        pig.setPassenger(silverFish);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 55) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.slime")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Slime slime = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                        pig.setPassenger(slime);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 54) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.zombie")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        pig.setPassenger(zombie);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 52) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.spider")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Spider spider = (Spider) player.getWorld().spawnEntity(player.getLocation(), EntityType.SPIDER);
                        pig.setPassenger(spider);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 99) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.irongolem")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        IronGolem ironGolem = (IronGolem) player.getWorld().spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
                        pig.setPassenger(ironGolem);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 97) {
                    // TODO: Add a check to remove the snow underneath feet
                    if ((player.hasPermission("dwdcmd.command.mobhat.snowman")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Snowman snowman = (Snowman) player.getWorld().spawnEntity(player.getLocation(), EntityType.SNOWMAN);
                        pig.setPassenger(snowman);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                } else if (e.getCurrentItem().getDurability() == 65) {
                    if ((player.hasPermission("dwdcmd.command.mobhat.bat")) || (player.hasPermission("dwdcmd.command.mobhat.all"))) {
                        Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                        pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000, 1));
                        Bat bat = (Bat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
                        pig.setPassenger(bat);
                        player.setPassenger(pig);
                        player.closeInventory();
                    } else {
                        player.closeInventory();
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(subtitle);
                        player.sendMessage(DwD + ChatColor.GOLD + "sorry, you are not allowed to choose this mob as a hat!");
                    }
                }
            } else if (e.getCurrentItem().getType() == Material.TNT) {
                if (player.getPassenger().getType() == EntityType.PIG && player.getPassenger().getPassenger() != null) {
                    player.getPassenger().getPassenger().remove();
                    player.getPassenger().remove();
                }
                if (player.getPassenger() != null) {
                    player.getPassenger().remove();
                }
                player.sendMessage(DwD + ChatColor.GOLD + "You removed your Mob Hat!");
            }
        }
    }
}
