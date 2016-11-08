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
 * Created by ETblaky on 06/11/2016.
 */
public class Boxer extends KitBase {

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Boxer";

    public Kit k;

    public Boxer(){ }

    public Boxer(Kit k){ this.k = k; }

    public ItemStack[] getItems(){ return items; }

    public String getName() { return name; }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(e.getDamager().getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }


        if(!(e.getDamager() instanceof  Player)) return;
        if(k== null) return;
        if(k.playersKits == null) return;
        if(k.playersKits.get(e.getDamager()) == null) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        if(k.playersKits.get(e.getDamager()).equals(Kit.Kits.BOXER)){
            if(((Player) e.getDamager()).getItemInHand() != null){
                if(((Player) e.getDamager()).getItemInHand().getType() == Material.AIR)
                e.setDamage(e.getDamage() + 3);
                return;
            }
            e.setDamage(5);
        }

        if(k.playersKits.get(e.getEntity()).equals(Kit.Kits.BOXER)){
            e.setDamage(e.getDamage() - 3);
        }
    }

}
