package me.etblaky.hg.Lobby;

import me.etblaky.hg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Meu computador on 31/10/2016.
 */
public class LobbyTimer {

    public int time = 0;

    public int scheduler;

    public Lobby lobby;

    public LobbyTimer(Lobby l){
        lobby = l;

    }

    public int getTime(){
        return time;
    }

    public void start(){
        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                time++;

                for(Player p : lobby.getPlayers()){

                    switch (time){
                        case (120 - 14):
                            p.sendMessage("Faltam 15 segundos!");
                            break;
                        case (120 - 9):
                            p.sendMessage("Faltam 10 segundos!");
                            break;
                        case (120 - 4):
                            p.sendMessage("Faltam 5 segundos!");
                            break;
                        case (120 - 3):
                            p.sendMessage("Faltam 4 segundos!");
                            break;
                        case (120 - 2):
                            p.sendMessage("Faltam 3 segundos!");
                            break;
                        case (120 - 1):
                            p.sendMessage("Faltam 2 segundos!");
                            break;
                        case (120 - 0):
                            p.sendMessage("Falta 1 segundo!");
                            break;
                    }

                    lobby.getBoard().setUpPlayer(p);

                }

                if(time == 120){
                    if(lobby.getPlayers().size() > 0){
                        lobby.stop();
                        return;
                    }

                    lobby.broadcast("Não tem jogadores suficientes para iniciar a partida!");
                    time = 0;

                }

            }
        }, 0, 20);
    }

    public void stop(){
        Bukkit.getScheduler().cancelTask(scheduler);
    }

}
