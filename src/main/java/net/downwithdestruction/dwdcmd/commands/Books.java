package net.downwithdestruction.dwdcmd.commands;

import net.downwithdestruction.dwdcmd.DwDCmd;
import net.downwithdestruction.dwdcmd.configuration.BooksConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by madmac on 11/23/15.
 */
public class Books implements CommandExecutor {
    private DwDCmd plugin;
    private String DwD = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "DwD" + ChatColor.DARK_RED + "] ";

    public Books(DwDCmd plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(DwD + ChatColor.GOLD + "Only players can use this command!");
            return true;
        }

        if (command.getName().equalsIgnoreCase("book")) {
            Player player = (Player) commandSender;

            if (!(player.hasPermission("dwdmcd.command.books"))) {
                player.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to use /book.");
                return true;
            }

            if ((strings.length == 0)) {
                player.sendMessage(DwD + ChatColor.GOLD + "Invalid Command!");
                player.sendMessage(DwD + ChatColor.GOLD + "/book create <BookName> " + ChatColor.AQUA + "To create/save an book.");
                player.sendMessage(DwD + ChatColor.GOLD + "/book give <BookName> " + ChatColor.AQUA + "To get a book");
                return true;
            }

            if (strings[0].equalsIgnoreCase("create")) {
                if (!(player.hasPermission("dwdcmd.command.books.create"))) {
                    player.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to use /book create <BookName>");
                    return true;
                }

                if (strings.length == 1) {
                    player.sendMessage(DwD + ChatColor.GOLD + "Invalid Command.");
                    player.sendMessage(DwD + ChatColor.GOLD + "/book create <BookName> " + ChatColor.AQUA + "To create/save an book.");
                    return true;
                }

                createBook(player, strings[1]);
                return true;
            }

            if (strings[0].equalsIgnoreCase("give")) {
                if (!(player.hasPermission("dwdcmd.command.books.give"))) {
                    player.sendMessage(DwD + ChatColor.GOLD + "You do not have permission to use /book give <BookName>");
                    return true;
                }

                File given = new File(plugin.getDataFolder(), strings[1]);

                if (!(given == null) && !(given.exists())) {

                    if (!(plugin.getConfig().get(strings[1]) == null)) {
                        ItemStack book = (ItemStack) plugin.getConfig().get(strings[1].toString());

                        if (!(book == null)) {
                            giveBook(book, player, player.getInventory().firstEmpty());
                        }
                    }
                }
                // TODO: Create giveBook() method
            }
        }

        return true;
    }

    /*
     * Creates a custom book that is being held in a players hand
     */
    public void createBook(Player player, String bookString) {
        if (!(bookString == null)) {
            ItemStack book = player.getInventory().getItemInHand();

            if (!(book == null) && (book.getType() == Material.WRITTEN_BOOK)) {
                BookMeta bookMeta = (BookMeta) book.getItemMeta();

                if (bookMeta.hasAuthor()) {
                    //BooksConfig.valueOf(BooksConfig.BOOK_WRITTEN_BY.getString(bookMeta.getAuthor()));
                    //plugin.getConfig().set(bookString + ".Book written by", bookMeta.getAuthor());
                    // Testing method
                    BooksConfig.BOOK_WRITTEN_BY.set(bookString, bookMeta.getAuthor());
                } else {
                    //BooksConfig.valueOf(BooksConfig.BOOK_WRITTEN_BY.getString("Unknown"));
                    //plugin.getConfig().set(bookString + ".Book written by", "Unknown");
                    // Testing method
                    BooksConfig.BOOK_WRITTEN_BY.set(bookString, "Unknown");
                }

                if (bookMeta.hasTitle()) {
                    //BooksConfig.valueOf(BooksConfig.TITLE.getString(bookMeta.getTitle()));
                    //plugin.getConfig().set(bookString + ".Title", bookMeta.getTitle());
                    // Testing method
                    BooksConfig.TITLE.set(bookString, bookMeta.getTitle());
                } else {
                    //BooksConfig.valueOf(BooksConfig.TITLE.getString("None"));
                    //plugin.getConfig().set(bookString + ".Title", "None");
                    // Testing method
                    BooksConfig.TITLE.set(bookString, "None");
                }

                if (bookMeta.hasPages()) {
                    List pages = new ArrayList();

                    for (String page : bookMeta.getPages()) {
                        //page = page.replaceAll("\n", "%new"); // Not needed as Java Strings will parse on their own ~ Billy ^_^
                        page = page.replaceAll("§", "&");
                        pages.add(page);
                    }

                    //plugin.getConfig().set(bookString + ".content", pages);
                    // Testing method
                    BooksConfig.CONTENT.set(bookString, pages);
                } else {
                    //plugin.getConfig().set(bookString + ".content", Arrays.asList(bookMeta.getPages().get(bookMeta.getPageCount())));
                    // Testing method
                    BooksConfig.BOOK_WRITTEN_BY.set(bookString, Arrays.asList(bookMeta.getPages().get(bookMeta.getPageCount())));
                }

                /*
                try {
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    // testing method
                    player.sendMessage(DwD + ChatColor.GOLD + "New book " + ChatColor.AQUA + bookString + ChatColor.GOLD + " has been registered!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    player.sendMessage(DwD + ChatColor.GOLD + "Failed to save " + ChatColor.AQUA + bookString + ChatColor.GOLD + " please check console error!");
                }
                */
            } else {
                player.sendMessage(DwD + ChatColor.GOLD + "Please retry with a book in your hand!");
            }
        }
    }

    private void giveBook(ItemStack bookString, Player player, int slot) {
        if (slot > 0) {
            ItemStack item = player.getInventory().getItem(slot);

            if (item == null) {
                player.getInventory().setItem(slot, bookString);
            } else {
                player.getInventory().addItem(bookString);
            }
        } else {
            player.getInventory().addItem(bookString);
        }

        player.updateInventory();
    }

    /*
     * First attempt, to complicated, trying something simple
     */
    /*
    public void giveBook(Player player, String bookString){
        ItemStack bookInfo = new ItemStack(Material.WRITTEN_BOOK);

        BookMeta bookMeta = (BookMeta) bookInfo.getItemMeta();

        bookMeta.setTitle(plugin.getConfig().getString("Title").replaceAll("&", "§"));
        bookMeta.setAuthor(plugin.getConfig().getString("Book written by"));

        for (String page : plugin.getConfig().getStringList("content")){
            bookMeta.addPage(new String[] { page.replaceAll("&", "§").replaceFirst("<PLAYER>", player.getName()) });
        }

        bookInfo.setItemMeta(bookMeta);

        player.getInventory().setItem(plugin.getConfig().getInt("Item Slot", bookInfo), bookString);
    }
    */
}
