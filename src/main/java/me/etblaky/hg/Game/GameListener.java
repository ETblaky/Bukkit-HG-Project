package me.etblaky.hg.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by ETblaky on 02/11/2016.
 */
public class GameListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDie(final PlayerDeathEvent e){

        if(Game.playerGame(e.getEntity()) == null) return;

        final Player player = e.getEntity();

        for(Game g : Game.getGames()){
            if(g.getPlayers() != null)
            for(final Player p : g.getPlayers()){
                if(p.getUniqueId().equals(player.getUniqueId())){
                    g.setSpectator(p);

                    if(g.getPlayers().size() <= 1){
                        g.stop();
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
        if(!(Game.playerGame((Player) e.getEntity()).getTimer().getTime() < 30)) return;

        e.setCancelled(true);
    }

}