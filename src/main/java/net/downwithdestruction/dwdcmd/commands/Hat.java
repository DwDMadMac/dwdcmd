package net.downwithdestruction.dwdcmd.commands;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by madmac on 9/30/15.
 *
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
        return true;
    }
}
