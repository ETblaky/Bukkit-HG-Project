package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Bukkit;
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

        if(!(e.getDamager() instanceof  Player)) return;

        k = setKit(k, (Player) e.getDamager());
        if(k== null) return;

        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        if(k.isKit((Player) e.getDamager(), Kit.Kits.BOXER)){
            if(((Player) e.getDamager()).getItemInHand() != null){
                if(((Player) e.getDamager()).getItemInHand().getType() == Material.AIR)

                    try {

                        Object damage = null;

                        if(getVersion().equals("v1_8_R1")){
                            damage = e.getClass().getMethod("getFinalDamage").invoke(e);
                        }
                        else if(getVersion().equals("v1_10_R1")){
                            damage = e.getClass().getMethod("getDamage").invoke(e);
                        }

                        e.setDamage((Double) damage + 3);

                    }

                    catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    return;
            }
            e.setDamage(5);
        }

        if(k.isKit((Player) e.getEntity(), Kit.Kits.BOXER)){
            e.setDamage(e.getFinalDamage() - 3);
        }
    }

    public String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

}
