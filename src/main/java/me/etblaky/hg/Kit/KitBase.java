package me.etblaky.hg.Kit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class KitBase implements Listener{
    
    public ItemStack enchant(ItemStack is, int level, Enchantment e){
        ItemMeta im = is.getItemMeta();
        im.addEnchant(e, level, true);
        is.setItemMeta(im);

        return is;
    }

    public ItemStack is(Material m, int q){
        return new ItemStack(m, q);
    }

    public ItemStack name(ItemStack is, String s){
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(s);

        is.setItemMeta(im);

        return is;
    }

}
