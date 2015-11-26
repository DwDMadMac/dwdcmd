package net.downwithdestruction.dwdcmd;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

/**
 * Created by madmac on 11/23/15.
 */
public class Utils {
    public static void Book(ItemStack bookItem) {
        //net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = ((CraftItemStack)bookItem).get


    }

    /*
     * Adds a Glowing effect to a ItemStack (Enchantment Glow)
     *
     * Usage INFO: player.getInventory().setItemInHand(addGlow(player.getItemInHand()));
     */
    public static org.bukkit.inventory.ItemStack addGlow(org.bukkit.inventory.ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        NBTTagCompound tag = null;

        if (!(nmsStack.hasTag())) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }

        if (tag == null) {
            tag = nmsStack.getTag();
        }

        NBTTagList ench = new NBTTagList();

        tag.set("ench", ench);

        nmsStack.setTag(tag);

        return CraftItemStack.asCraftMirror(nmsStack);
    }
}
