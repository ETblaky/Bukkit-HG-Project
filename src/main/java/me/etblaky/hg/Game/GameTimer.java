package me.etblaky.hg.Game;

import me.etblaky.hg.Main;
import me.etblaky.titles.TitleApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class GameTimer {

    public int time = 0;

    public int scheduler;

    public Game game;

    public GameTimer(Game g){
        game = g;

    }

    public int getTime(){
        return time;
    }

    public void start(){
        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                time++;

                for(Player p : game.getPlayers()){
                    game.getBoard().setUpPlayer(p);

                    switch (time){
                        case (600 - 15):
                            TitleApi.sendTitle(p, ChatColor.GREEN + "15 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                        case (600 - 10):
                            TitleApi.sendTitle(p, ChatColor.GREEN + "10 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                        case (600 - 5):
                            TitleApi.sendTitle(p, ChatColor.GREEN + "5 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                        case (600 - 4):
                            TitleApi.sendTitle(p, ChatColor.DARK_GREEN + "4 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                        case (600 - 3):
                            TitleApi.sendTitle(p, ChatColor.YELLOW + "3 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                        case (600 - 2):
                            TitleApi.sendTitle(p, ChatColor.RED + "2 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                        case (600 - 1):
                            TitleApi.sendTitle(p, ChatColor.DARK_RED + "1 segundo", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            break;
                    }

                    if(time == 60){
                        TitleApi.sendTitle(p, ChatColor.RED + "O PvP está liberado!", 1, 1, 1);
                    }

                }

                for(Player p : game.spectators) {
                    game.getBoard().setUpPlayer(p);
                }

                if(time == 600){
                    game.stop(null);
                }

            }
        }, 0, 20);
    }

    public void stop(){
        Bukkit.getScheduler().cancelTask(scheduler);
    }
}
