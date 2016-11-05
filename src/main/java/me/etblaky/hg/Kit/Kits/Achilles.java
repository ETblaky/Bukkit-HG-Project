package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 04/11/2016.
 */
public class Achilles implements Listener{

    public static ItemStack[] items = new ItemStack[] {};
    public static String name = "Achilles";

    public Kit k;

    public Achilles(Kit k){
        this.k = k;
    }

    public static ItemStack[] getItems(){
        return items;
    }

    public static String getName() {
        return name;
    }

    public static void setAbilities(Player p){ }

    public static void removeAbilities(Player p){ }

    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof  Player)) return;
        if(k.playersKits.get((Player) e.getEntity()) == null) return;
        if(!k.playersKits.get((Player) e.getEntity()).equals(Kit.Kits.ACHILLES)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        if(((Player) e.getDamager()).getItemInHand().getType().equals(Material.WOOD_SWORD)){
            e.setDamage(7); //Diamond Damage
        }
        if(((Player) e.getDamager()).getItemInHand().getType().equals(Material.STONE_SWORD)){
            e.setDamage(6); //Iron Damage
        }
        if(((Player) e.getDamager()).getItemInHand().getType().equals(Material.IRON_SWORD)){
            e.setDamage(5); //Stone Damage
        }
        if(((Player) e.getDamager()).getItemInHand().getType().equals(Material.DIAMOND_SWORD)){
            e.setDamage(4); //Wood Damage
        }

    }

    public static ItemStack Is(Material m, int q){
        return new ItemStack(m, q);
    }

}
