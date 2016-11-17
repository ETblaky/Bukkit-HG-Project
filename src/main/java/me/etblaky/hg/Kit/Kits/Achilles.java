package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 04/11/2016.
 */
public class Achilles extends KitBase{

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Achilles";

    public Kit k;

    public Achilles(){ }

    public Achilles(Kit k){
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

    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(((Player) e.getDamager()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof  Player)) return;
        if(!k.isKit((Player) e.getEntity(), Kit.Kits.ACHILLES)) return;
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

}
