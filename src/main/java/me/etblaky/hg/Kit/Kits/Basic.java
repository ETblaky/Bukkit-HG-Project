package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class Basic extends KitBase{

    public ItemStack[] items = new ItemStack[] {is(Material.WOOD_SWORD, 1), is(Material.MUSHROOM_SOUP, 7)};
    public String name = "Basic";

    public Kit k;

    public Basic(){ }

    public Basic(Kit ks){
        k = ks;
    }

    public ItemStack[] getItems(){
        return items;
    }

    public String getName() {
        return name;
    }

    public void setAbilities(Player p){
        p.setMaxHealth(30);
        p.setHealth(30);
    }

    public void removeAbilities(Player p){
        p.setMaxHealth(20);
        p.setHealth(20);
    }

}
