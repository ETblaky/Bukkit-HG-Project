package me.etblaky.hg.Kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Meu computador on 31/10/2016.
 */
public class Basic {

    public static ItemStack[] items = new ItemStack[] {Is(Material.WOOD_SWORD, 1), Is(Material.MUSHROOM_SOUP, 8)};
    public static String name = "Basic";

    public static ItemStack[] getItems(){
        return items;
    }

    public static String getName() {
        return name;
    }

    public static void setAbilities(Player p){
        p.setMaxHealth(30);
        p.setHealth(30);
    }

    public static void removeAbilities(Player p){
        p.setMaxHealth(20);
        p.setHealth(20);
    }


    public static ItemStack Is(Material m, int q){
        return new ItemStack(m, q);
    }
}
