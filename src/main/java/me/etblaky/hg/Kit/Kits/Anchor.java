package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

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
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(((Player) e.getDamager()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof  Player)) return;
        if(!k.isKit((Player) e.getEntity(), Kit.Kits.ANCHOR) && !k.isKit((Player) e.getEntity(), Kit.Kits.ANCHOR)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        e.setCancelled(true);

        try {

            Object damage = null;

            if(getVersion().equals("v1_8_R1")){
                damage = e.getClass().getMethod("getFinalDamage").invoke(e);
            }
            else if(getVersion().equals("v1_10_R1")){
                damage = e.getClass().getMethod("getDamage").invoke(e);
            }

            ((Player) e.getEntity()).damage((Double) damage);

        }

        catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }


}
