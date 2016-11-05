package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class Archer extends KitBase{

    public ItemStack[] items = new ItemStack[] {enchant(is(Material.BOW, 1), 2, Enchantment.ARROW_FIRE), is(Material.ARROW, 32)};
    public String name = "Archer";

    public Kit k;

    public Archer(){ }

    public Archer(Kit k){
        this.k = k;
    }

    public ItemStack[] getItems(){
        return items;
    }

    public String getName() {
        return name;
    }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

}
