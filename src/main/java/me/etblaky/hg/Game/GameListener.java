package me.etblaky.hg.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by ETblaky on 02/11/2016.
 */
public class GameListener implements Listener {

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent e){

        final Player player = e.getEntity();

        for(Game g : Game.getGames()){
            for(Player p : g.getPlayers()){
                if(p.getUniqueId().equals(player.getUniqueId())){
                    g.setSpectator(p);

                    if(g.getPlayers().size() <= 1){
                        //g.stop();
                    }

                }
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getPlayers()){
                if(p.getUniqueId().equals(e.getPlayer().getUniqueId())){
                    g.removePlayer(p);
                }
            }
        }
    }

}