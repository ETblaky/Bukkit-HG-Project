package me.etblaky.hg.Kit;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
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

    public Kit setKit(Kit k, Player p){

        if(Lobby.playerLobby(p) != null) { k = Lobby.playerLobby(p).getKit(); return k; }
        if(Game.playerGame(p) != null) { k = Game.playerGame(p).getLobby().getKit(); return k; }

        return k;
    }

}
