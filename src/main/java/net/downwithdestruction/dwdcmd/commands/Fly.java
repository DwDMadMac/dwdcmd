package net.downwithdestruction.dwdcmd.commands;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by madmac on 9/20/15.
 */
public class Fly implements CommandExecutor {
    private DwDCmd plugin;
    private String DwD = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "DwD" + ChatColor.DARK_RED + "] ";

    public Fly(DwDCmd plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("fly")) {
            if (strings.length == 0 && !(commandSender instanceof Player)) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "Only players can use fly!");
                return true;
            }

            if (!commandSender.hasPermission("dwdcmd.command.fly")) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to enable /fly.");
                return true;
            }

            Player target;

            if (strings.length == 0) {
                target = (Player) commandSender;
            } else {
                target = Bukkit.getServer().getPlayer(strings[0]);

                if (target == null) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "Cannot find player " + strings[0] + ".");
                    return true;
                }

                if (target.hasPermission("dwdcmd.command.fly.exempt")) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "You can not toggle a players fly that is exempted!");
                    return true;
                }
            }

            if (commandSender != target && !(commandSender.hasPermission("dwdcmd.command.fly.others"))) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to enable /fly for others.");
                return true;
            }

            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
            } else {
                target.setAllowFlight(true);
            }

            String flyStatus = (target.getAllowFlight()) ? ChatColor.GREEN + "ON" : ChatColor.DARK_RED + "OFF";
            if (commandSender == target) {
                target.sendMessage(DwD + ChatColor.GOLD + "Fly was toggled " + ChatColor.DARK_RED + flyStatus + ChatColor.GOLD + ".");
            } else {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You toggled " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.GOLD + "'s fly " + ChatColor.RED + flyStatus + ChatColor.GOLD + ".");
                target.sendMessage(DwD + ChatColor.GOLD + "Your fly was toggled " + ChatColor.DARK_RED + flyStatus + ChatColor.GOLD + " by " + ChatColor.DARK_RED + commandSender.getName() + ChatColor.GOLD + ".");
            }

            return true;
        }

        if (command.getName().equalsIgnoreCase("flyspeed")) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "Only players can use flyspeed!");
                return true;
            }

            if (!commandSender.hasPermission("dwdcmd.command.flyspeed")) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to use /flyspeed.");
                return true;
            }

            if (strings.length < 1) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "Please enter a speed value of 1 - 10.");
                return true;
            }

            int flySpeed;

            try {
                flySpeed = Integer.valueOf(strings[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return true;
            }

            if (flySpeed < 0 || flySpeed > 10) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "Please enter a speed value of 1 - 10.");
                return true;
            }

            Player target;

            if (strings.length < 2) {
                target = (Player) commandSender;
            } else {
                target = Bukkit.getServer().getPlayer(strings[1]);

                if (target == null) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "Cannot find player " + strings[1] + ".");
                    return true;
                }

                if (target.hasPermission("dwdcmd.command.flyspeed.exempt")) {
                    commandSender.sendMessage(DwD + ChatColor.GOLD + "You can not change a players flyspeed that is exempted!");
                    return true;
                }
            }

            if (commandSender != target && !(commandSender.hasPermission("dwdcmd.command.fly.others"))) {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to change flyspeed for others.");
                return true;
            }

            target.setFlySpeed(flySpeed / 10F);
            if (commandSender == target) {
                target.sendMessage(DwD + ChatColor.GOLD + "Your flyspeed was changed to " + ChatColor.DARK_RED + Integer.toString(flySpeed) + ChatColor.GOLD + ".");
            } else {
                commandSender.sendMessage(DwD + ChatColor.GOLD + "You changed " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.GOLD + "'s flyspeed to " + ChatColor.RED + Integer.toString(flySpeed) + ChatColor.GOLD + ".");
                target.sendMessage(DwD + ChatColor.GOLD + "Your flyspeed was changed to " + ChatColor.DARK_RED + Integer.toString(flySpeed) + ChatColor.GOLD + " by " + ChatColor.DARK_RED + commandSender.getName() + ChatColor.GOLD + ".");
            }

            return true;
        }

        return true;
    }
}
