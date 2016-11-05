package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by ETblaky on 04/11/2016.
 */
public class Anchor extends KitBase{

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Anchor";

    public Kit k;

    public Anchor(){ }

    public Anchor(Kit k){
        this.k = k;
    }

    public ItemStack[] getItems(){
        return items;
    }

    public String getName() {
        return name;
    }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){  }

    @EventHandler
    public void disableKnockBack(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof  Player)) return;
        if(k.playersKits.get((Player) e.getEntity()) == null) return;
        if(!k.playersKits.get((Player) e.getEntity()).equals(Kit.Kits.ANCHOR) || !k.playersKits.get((Player) e.getDamager()).equals(Kit.Kits.ANCHOR)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        e.getEntity().setVelocity(new Vector());

    }

}
