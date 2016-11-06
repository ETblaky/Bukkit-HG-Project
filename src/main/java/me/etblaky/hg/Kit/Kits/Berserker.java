package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class Berserker extends KitBase {

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Berserker";

    public Kit k;

    public Berserker(){ }

    public Berserker(Kit k){ this.k = k; }

    public ItemStack[] getItems(){ return items; }

    public String getName() { return name; }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(e.getEntity().getKiller().getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(e.getEntity().getKiller() == null) return;
        if(k.playersKits.get(e.getEntity().getKiller()) == null) return;
        if(!k.playersKits.get(e.getEntity().getKiller()).equals(Kit.Kits.BERSERKER)) return;

        e.getEntity().getKiller().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30 * 20, 1));

        //e.getEntity().setHealth(e.getEntity().getMaxHealth());

    }

}
