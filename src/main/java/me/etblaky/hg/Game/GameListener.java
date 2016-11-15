package me.etblaky.hg.Game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 02/11/2016.
 */
public class GameListener implements Listener {

    @EventHandler
    public void onPlayerDie(final PlayerDeathEvent e){

        if(Game.playerGame(e.getEntity()) == null) return;

        final Player player = e.getEntity();

        for(Game g : Game.getGames()){
            if(g.getPlayers() != null)
            for(final Player p : g.getPlayers()){
                if(p.getUniqueId().equals(player.getUniqueId())){
                    g.removePlayer(p);
                    g.setSpectator(p);

                    if(g.getPlayers().size() < 1){
                        g.stop(null);
                    }

                }
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(final PlayerQuitEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getPlayers()){
                if(p.getUniqueId().equals(e.getPlayer().getUniqueId())){
                    g.removePlayer(p);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof  Player)) return;
        if(Game.playerGame((Player) e.getEntity()) == null) return;
        if(!(Game.playerGame((Player) e.getEntity()).getTimer().getTime() < 60)) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerUseSoup(final PlayerInteractEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(!e.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) return;

        e.setCancelled(true);

        if(e.getPlayer().getHealth() == e.getPlayer().getMaxHealth()) return;

        e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), new ItemStack(Material.BOWL));

        if(e.getPlayer().getHealth() + 6 > e.getPlayer().getMaxHealth()){ e.getPlayer().setHealth(e.getPlayer().getMaxHealth()); return;}

        e.getPlayer().setHealth(e.getPlayer().getHealth() + 6);

    }

    @EventHandler
    public void onPlayerUseComapass(final PlayerInteractEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!e.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) return;

        if(getClosest(e.getPlayer()) == null) { e.getPlayer().sendMessage("Nenhum player proximo encontrado."); return; }

        e.getPlayer().setCompassTarget(getClosest(e.getPlayer()).getLocation());
        e.getPlayer().sendMessage("Apontando para " + getClosest(e.getPlayer()).getName() + ".");

        e.getPlayer().updateInventory();

    }

    public Player getClosest(Player p){

        double closest = Double.MAX_VALUE;
        Player closestp = null;
        for(Player i : Bukkit.getOnlinePlayers()){
            double dist = i.getLocation().distance(p.getLocation());
            if (closest == Double.MAX_VALUE || dist < closest){
                if(!i.getUniqueId().equals(p.getUniqueId())){
                    closest = dist;
                    closestp = i;
                }
            }
        }

        return closestp;
    }

}