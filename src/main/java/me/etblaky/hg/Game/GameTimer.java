package me.etblaky.hg.Game;

import me.etblaky.hg.Main;
import org.bukkit.Bukkit;
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
                        case (600 - 14):
                            p.sendMessage("Faltam 15 segundos!");
                            break;
                        case (600 - 9):
                            p.sendMessage("Faltam 10 segundos!");
                            break;
                        case (600 - 4):
                            p.sendMessage("Faltam 5 segundos!");
                            break;
                        case (600 - 3):
                            p.sendMessage("Faltam 4 segundos!");
                            break;
                        case (600 - 2):
                            p.sendMessage("Faltam 3 segundos!");
                            break;
                        case (600 - 1):
                            p.sendMessage("Faltam 2 segundos!");
                            break;
                        case (600 - 0):
                            p.sendMessage("Falta 1 segundo!");
                            break;
                    }

                    if(time == 30){
                        p.sendMessage("O PvP está liberado!");
                    }

                }

                if(time == 600){
                    game.stop();
                }

            }
        }, 0, 20);
    }

    public void stop(){
        Bukkit.getScheduler().cancelTask(scheduler);
    }
}
