package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 15/11/2016.
 */
public class Stomper extends KitBase {

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Stomper";

    public Kit k;

    public Stomper(){ }

    public Stomper(Kit k){ this.k = k; }

    public ItemStack[] getItems(){ return items; }

    public String getName() { return name; }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

    @EventHandler
    public void playerDamage(EntityDamageEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals((e.getEntity()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(k== null) return;
        if(k.playersKits == null) return;
        if(!(e.getEntity() instanceof Player)) return;
        if(k.playersKits.get(e.getEntity()) == null) return;
        if(!k.playersKits.get(e.getEntity()).equals(Kit.Kits.STOMPER)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        if(e.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        for(Entity en : e.getEntity().getNearbyEntities(5, 3, 5)){
            if(!(en instanceof Player)) return;
            if(en.equals(e.getEntity())) return;

            ((Player) en).damage(e.getFinalDamage());
        }

        e.setDamage(e.getFinalDamage() * 0.05);

    }
}
