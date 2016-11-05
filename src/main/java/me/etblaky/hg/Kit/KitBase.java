package me.etblaky.hg.Kit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class KitBase implements Listener{

    public ItemStack enchant(ItemStack is, int level, Enchantment... enc){
        for(Enchantment e : enc){
            is.addEnchantment(e, level);
        }

        return is;
    }

    public ItemStack is(Material m, int q){
        return new ItemStack(m, q);
    }

}
