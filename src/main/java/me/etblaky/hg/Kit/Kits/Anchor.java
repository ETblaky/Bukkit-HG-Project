package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by ETblaky on 04/11/2016.
 */
public class Anchor {

    public static ItemStack[] items = new ItemStack[] {};
    public static String name = "Anchor";

    public Kit k;

    public Anchor(Kit k){
        this.k = k;
    }

    public static ItemStack[] getItems(){
        return items;
    }

    public static String getName() {
        return name;
    }

    public static void setAbilities(Player p){ }

    public static void removeAbilities(Player p){  }

    @EventHandler
    public void disableKnockBack(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof  Player)) return;
        if(k.playersKits.get((Player) e.getEntity()) == null) return;
        if(!k.playersKits.get((Player) e.getEntity()).equals(Kit.Kits.ANCHOR) || !k.playersKits.get((Player) e.getDamager()).equals(Kit.Kits.ANCHOR)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        e.getEntity().setVelocity(new Vector());

    }

    public static ItemStack Is(Material m, int q){
        return new ItemStack(m, q);
    }


}
