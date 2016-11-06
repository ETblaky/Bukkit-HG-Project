package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.hg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class Assassin extends KitBase {

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Assassin";

    public Kit k;

    public Assassin(){ }

    public Assassin(Kit k){
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

    HashMap<Player, Integer> pTimes = new HashMap<Player, Integer>();
    int scheduler;

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(e.getPlayer().getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(k.playersKits.get(e.getPlayer()) == null) return;
        if(!k.playersKits.get(e.getPlayer()).equals(Kit.Kits.ASSASSIN)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        final PlayerToggleSneakEvent ev = e;
        if(pTimes.get(e.getPlayer()) == null){
            pTimes.put(e.getPlayer(), 0);
        }
        if(e.isSneaking()){
            scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                public void run() {
                    if(pTimes.get(ev.getPlayer()) == 10) return;

                    pTimes.put(ev.getPlayer(), pTimes.get(ev.getPlayer()) + 1);
                    ev.getPlayer().setLevel(pTimes.get(ev.getPlayer()));
                }
            }, 20, 30);
        }
        else {
            Bukkit.getScheduler().cancelTask(scheduler);
        }

    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){

        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(((Player) e.getDamager()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        //if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof  Player)) return;
        if(k.playersKits.get(e.getDamager()) == null) return;
        if(!k.playersKits.get(e.getDamager()).equals(Kit.Kits.ASSASSIN)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        Player p = (Player) e.getDamager();

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, pTimes.get(p) * 20, 2));
        pTimes.put(p, 0);
        p.setLevel(0);
        p.setExp(0);

    }

}