package net.downwithdestruction.dwdcmd.commands;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by madmac on 9/30/15.
 */
public class Hat implements CommandExecutor {
    private DwDCmd plugin;
    private String DwD = ChatColor.DARK_RED + "[" + ChatColor.DARK_GRAY + "DwD" + ChatColor.DARK_RED + "] ";

    public Hat(DwDCmd plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("helmet")) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "Only players can use helmets!");
                return true;
            }

            if (!commandSender.hasPermission("dwdcmd.command.helmet")) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to use /helmet.");
                return true;
            }

            Player target;
            ItemStack helmet;

            if (strings.length < 2) {
                target = (Player) commandSender;
                helmet = target.getItemInHand();
            } else {
                target = Bukkit.getServer().getPlayer(strings[1]);

                if (target == null) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "Cannot find player " + strings[1] + ".");
                    return true;
                }

                if (target.hasPermission("dwdcmd.command.helmet.exempt")) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "You can not put a players helmet that is exempted!");
                    return true;
                }

                helmet = ((Player) commandSender).getItemInHand();
            }

            if (commandSender != target && !(commandSender.hasPermission("dwdcmd.command.helmet.others"))) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to modify other players helmet!");
                return true;
            }

            ItemStack oldHelmet = target.getInventory().getHelmet();

            if (strings.length > 0 && (strings[0].contains("remove") || strings[0].contains("off") || strings[0].equalsIgnoreCase("0"))) {
                if (oldHelmet != null && !oldHelmet.getType().equals(Material.AIR)) {
                    if (target.getInventory().firstEmpty() == -1) {
                        target.sendMessage(DwD + ChatColor.GOLD + "Your inventory is full, can not remove helmet!");
                        return true;
                    }
                    target.getInventory().addItem(oldHelmet);
                }
                target.getInventory().setHelmet(helmet);

                commandSender.sendMessage(DwD + ChatColor.GOLD + "Your helmet was removed!");

                return true;
            } else {
                if (helmet == null || helmet.getType().equals(Material.AIR)) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "Nothing in your hand to place as a helmet!");
                    return true;
                }

                if (helmet.getAmount() == 1) {
                    target.getInventory().remove(helmet);

                    if (oldHelmet != null && !oldHelmet.getType().equals(Material.AIR)) {
                        target.getInventory().addItem(oldHelmet);
                    }

                    target.getInventory().setHelmet(helmet);
                } else {
                    if (oldHelmet != null && !oldHelmet.getType().equals(Material.AIR)) {
                        if (target.getInventory().firstEmpty() == -1) {
                            target.sendMessage(DwD + ChatColor.GOLD + "Your inventory is full, can not remove helmet!");
                            return true;
                        }
                        target.getInventory().addItem(oldHelmet);
                    }
                    helmet.setAmount(helmet.getAmount() - 1);
                    target.setItemInHand(helmet);
                    helmet.setAmount(1);
                    target.getInventory().setHelmet(helmet);
                }

                commandSender.sendMessage(DwD + ChatColor.GOLD + "You set a custom helmet!");
            }
            return true;
        }

        if (command.getName().equalsIgnoreCase("mobhat")) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "Only players can use hats!");
                return true;
            }

            if (!commandSender.hasPermission("dwdcmd.command.mobhat")) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to use /mobhat.");
                return true;
            }

            Player target = (Player) commandSender;

            ChatColor G = ChatColor.GREEN;
            ChatColor DR = ChatColor.DARK_RED;

            Inventory inv = Bukkit.createInventory(null, 54, DwD + ChatColor.DARK_GREEN + "Mob Hats:");

            ArrayList<String> noPerms = new ArrayList<String>();
            noPerms.add(ChatColor.RED + "Sorry " + ChatColor.GOLD + target.getDisplayName() + ChatColor.RED + ", you do");
            noPerms.add(ChatColor.RED + "not have permissions to use");
            noPerms.add(ChatColor.RED + "this MobHat!");

            ItemStack cowEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.COW.getTypeId());
            ItemMeta cowMeta = cowEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.cow") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                cowMeta.setDisplayName(G + "Cow");
            } else {
                cowMeta.setDisplayName(DR + "Cow");
                cowMeta.setLore(noPerms);
            }
            cowEgg.setItemMeta(cowMeta);
            inv.setItem(19, cowEgg);

            ItemStack chickenEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CHICKEN.getTypeId());
            ItemMeta chickenMeta = chickenEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.chicken") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                chickenMeta.setDisplayName(G + "Chicken");
            } else {
                chickenMeta.setDisplayName(DR + "Chicken");
                chickenMeta.setLore(noPerms);
            }
            chickenEgg.setItemMeta(chickenMeta);
            inv.setItem(21, chickenEgg);

            ItemStack squidEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SQUID.getTypeId());
            ItemMeta squidMeta = squidEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.squid") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                squidMeta.setDisplayName(G + "Squid");
            } else {
                squidMeta.setDisplayName(DR + "Squid");
                squidMeta.setLore(noPerms);
            }
            squidEgg.setItemMeta(squidMeta);
            inv.setItem(23, squidEgg);

            ItemStack mooshroomEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.MUSHROOM_COW.getTypeId());
            ItemMeta mooshroomMeta = mooshroomEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.mooshroom") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                mooshroomMeta.setDisplayName(G + "Mooshroom Cow");
            } else {
                mooshroomMeta.setDisplayName(DR + "Mooshroom Cow");
                mooshroomMeta.setLore(noPerms);
            }
            mooshroomEgg.setItemMeta(mooshroomMeta);
            inv.setItem(25, mooshroomEgg);

            ItemStack sheepEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SHEEP.getTypeId());
            ItemMeta sheepMeta = sheepEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.sheep") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                sheepMeta.setDisplayName(G + "Sheep");
            } else {
                sheepMeta.setDisplayName(DR + "Sheep");
                sheepMeta.setLore(noPerms);
            }
            sheepEgg.setItemMeta(sheepMeta);
            inv.setItem(27, sheepEgg);

            ItemStack pigEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.PIG.getTypeId());
            ItemMeta pigMeta = pigEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.pig") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                pigMeta.setDisplayName(G + "Pig");
            } else {
                pigMeta.setDisplayName(DR + "Pig");
                pigMeta.setLore(noPerms);
            }
            pigEgg.setItemMeta(pigMeta);
            inv.setItem(29, pigEgg);

            ItemStack pigmanEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.PIG_ZOMBIE.getTypeId());
            ItemMeta pigmanMeta = pigmanEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.pigman") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                pigmanMeta.setDisplayName(G + "Pigman");
            } else {
                pigmanMeta.setDisplayName(DR + "Pigman");
                pigmanMeta.setLore(noPerms);
            }
            pigmanEgg.setItemMeta(pigmanMeta);
            inv.setItem(31, pigmanEgg);

            ItemStack slimeEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SLIME.getTypeId());
            ItemMeta slimeMeta = slimeEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.slime") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                slimeMeta.setDisplayName(G + "Slime");
            } else {
                slimeMeta.setDisplayName(DR + "Slime");
                slimeMeta.setLore(noPerms);
            }
            slimeEgg.setItemMeta(slimeMeta);
            inv.setItem(33, slimeEgg);

            ItemStack ocelotEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.OCELOT.getTypeId());
            ItemMeta ocelotMeta = ocelotEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.ocelot") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                ocelotMeta.setDisplayName(G + "Ocelot");
            } else {
                ocelotMeta.setDisplayName(DR + "Ocelot");
                ocelotMeta.setLore(noPerms);
            }
            ocelotEgg.setItemMeta(ocelotMeta);
            inv.setItem(35, ocelotEgg);

            ItemStack caveSpiderEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CAVE_SPIDER.getTypeId());
            ItemMeta caveSpiderMeta = caveSpiderEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.cavespider") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                caveSpiderMeta.setDisplayName(G + "Cave Spider");
            } else {
                caveSpiderMeta.setDisplayName(DR + "Cave Spider");
                caveSpiderMeta.setLore(noPerms);
            }
            caveSpiderEgg.setItemMeta(caveSpiderMeta);
            inv.setItem(37, caveSpiderEgg);

            ItemStack silverFishEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SILVERFISH.getTypeId());
            ItemMeta silverFishMeta = silverFishEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.silverfish") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                silverFishMeta.setDisplayName(G + "Silver Fish");
            } else {
                silverFishMeta.setDisplayName(DR + "Silver Fish");
                silverFishMeta.setLore(noPerms);
            }
            silverFishEgg.setItemMeta(silverFishMeta);
            inv.setItem(39, silverFishEgg);

            ItemStack skeletonEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SKELETON.getTypeId());
            ItemMeta skeletonMeta = skeletonEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.silverfish") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                skeletonMeta.setDisplayName(G + "Skeleton");
            } else {
                skeletonMeta.setDisplayName(DR + "Skeleton");
                skeletonMeta.setLore(noPerms);
            }
            skeletonEgg.setItemMeta(skeletonMeta);
            inv.setItem(41, skeletonEgg);

            ItemStack ironGolemEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.IRON_GOLEM.getTypeId());
            ItemMeta ironGolemMeta = ironGolemEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.irongolem") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                ironGolemMeta.setDisplayName(G + "Iron Golem");
            } else {
                ironGolemMeta.setDisplayName(DR + "Iron Golem");
                ironGolemMeta.setLore(noPerms);
            }
            ironGolemEgg.setItemMeta(ironGolemMeta);
            inv.setItem(47, ironGolemEgg);

            ItemStack zombieEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ZOMBIE.getTypeId());
            ItemMeta zombieMeta = zombieEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.zombie") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                zombieMeta.setDisplayName(G + "Zombie");
            } else {
                zombieMeta.setDisplayName(DR + "Zombie");
                zombieMeta.setLore(noPerms);
            }
            zombieEgg.setItemMeta(zombieMeta);
            inv.setItem(43, zombieEgg);

            ItemStack spiderEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SPIDER.getTypeId());
            ItemMeta spiderMeta = spiderEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.spider") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                spiderMeta.setDisplayName(G + "Spider");
            } else {
                spiderMeta.setDisplayName(DR + "Spider");
                spiderMeta.setLore(noPerms);
            }
            spiderEgg.setItemMeta(spiderMeta);
            inv.setItem(45, spiderEgg);

            ItemStack snowmanEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SNOWMAN.getTypeId());
            ItemMeta snowmanMeta = snowmanEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.snowman") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                snowmanMeta.setDisplayName(G + "Snowman");
            } else {
                snowmanMeta.setDisplayName(DR + "Snowman");
                snowmanMeta.setLore(noPerms);
            }
            snowmanEgg.setItemMeta(snowmanMeta);
            inv.setItem(49, snowmanEgg);

            ItemStack batEgg = new ItemStack(Material.MONSTER_EGG, 1, EntityType.BAT.getTypeId());
            ItemMeta batMeta = batEgg.getItemMeta();
            if (target.hasPermission("dwdcmd.command.mobhat.bat") || target.hasPermission("dwdcmd.command.mobhat.all")) {
                batMeta.setDisplayName(G + "Bat");
                ArrayList<String> batLore = new ArrayList<String>();
                batLore.add(ChatColor.GOLD + "Mob Hat Abilities");
                batLore.add(ChatColor.GOLD + "  - Double Jump");
                batLore.add(ChatColor.BLACK + "-*");
                if (target.hasPermission("dwdcmd.command.fly")) {
                    batLore.add(ChatColor.RED + "/fly will be disabled when");
                    batLore.add(ChatColor.RED + "you use this Mob Hat!");
                }
                batMeta.setLore(batLore);
            } else {
                batMeta.setDisplayName(DR + "Bat");
                batMeta.setLore(noPerms);
            }
            batEgg.setItemMeta(batMeta);
            inv.setItem(51, batEgg);

            ItemStack tntExit = new ItemStack(Material.TNT, 1);
            ItemMeta tntExitMeta = tntExit.getItemMeta();
            tntExitMeta.setDisplayName(DR + "Remove Your Mob Hat");
            ArrayList<String> tntExitLore = new ArrayList<String>();
            tntExitLore.add(ChatColor.GOLD + "Clicking this" + ChatColor.RED + " TNT " + ChatColor.GOLD + "item");
            tntExitLore.add(ChatColor.GOLD + "will remove your Mob Hat.");
            tntExitMeta.setLore(tntExitLore);
            tntExit.setItemMeta(tntExitMeta);
            inv.setItem(0, tntExit);

            ItemStack redstoneBlockCloseInv = new ItemStack(Material.REDSTONE_BLOCK, 1);
            ItemMeta redstoneBlockCloseInvMeta = redstoneBlockCloseInv.getItemMeta();
            redstoneBlockCloseInvMeta.setDisplayName(DR + "Close Mob Hat Menu");
            ArrayList<String> redstoneBlockCloseInvLore = new ArrayList<String>();
            redstoneBlockCloseInvLore.add(ChatColor.GOLD + "Quick way of removing");
            redstoneBlockCloseInvLore.add(ChatColor.GOLD + "your Mob Hat menu.");
            redstoneBlockCloseInvMeta.setLore(redstoneBlockCloseInvLore);
            redstoneBlockCloseInv.setItemMeta(redstoneBlockCloseInvMeta);
            inv.setItem(8, redstoneBlockCloseInv);

            /*
            ItemStack showALL = new ItemStack(Material.TORCH, 1);
            ItemMeta showALLMeta = showALL.getItemMeta();
            showALLMeta.setDisplayName(G + "Show all");
            ArrayList showALLLore = new ArrayList();
            showALLLore.add(ChatColor.GOLD + "This will show all the Mob Hats");
            showALLLore.add(ChatColor.GOLD + "weather you have permission to");
            showALLLore.add(ChatColor.GOLD + "them or not!");
            showALLMeta.setLore(showALLLore);
            showALL.setItemMeta(showALLMeta);
            inv.setItem(3, showALL);

            ItemStack showPermedOnly = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
            ItemMeta showPermedOnlyMeta = showALL.getItemMeta();
            showPermedOnlyMeta.setDisplayName(G + "Show Permitted");
            ArrayList showPermedOnlyMetaLore = new ArrayList();
            showPermedOnlyMetaLore.add(ChatColor.GOLD + "This will show only the Mob Hats");
            showPermedOnlyMetaLore.add(ChatColor.GOLD + "you have permission to!");
            showPermedOnlyMeta.setLore(showPermedOnlyMetaLore);
            showPermedOnly.setItemMeta(showPermedOnlyMeta);
            inv.setItem(5, showPermedOnly);
            */

            target.openInventory(inv);

            return true;
        }
        return true;
    }
}
