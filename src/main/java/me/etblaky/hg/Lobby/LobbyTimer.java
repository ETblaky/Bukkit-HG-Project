package me.etblaky.hg.Lobby;

import me.etblaky.hg.Main;
import me.etblaky.titles.TitleApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
                        case (120 - 15):
                            TitleApi.sendTitle(p, ChatColor.GREEN + "15 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                        case (120 - 10):
                            TitleApi.sendTitle(p, ChatColor.GREEN + "10 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                        case (120 - 5):
                            TitleApi.sendTitle(p, ChatColor.GREEN + "5 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                        case (120 - 4):
                            TitleApi.sendTitle(p, ChatColor.DARK_GREEN + "4 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                        case (120 - 3):
                            TitleApi.sendTitle(p, ChatColor.YELLOW + "3 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                        case (120 - 2):
                            TitleApi.sendTitle(p, ChatColor.RED + "2 segundos", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                        case (120 - 1):
                            TitleApi.sendTitle(p, ChatColor.DARK_RED + "1 segundo", 1, 1, 0);
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            break;
                    }

                    lobby.getBoard().setUpPlayer(p);

                }

                if(time == 120){
                    if(lobby.getPlayers().size() > 0){
                        lobby.stop();
                        return;
                    }
                    for(Player p : lobby.getPlayers()){
                        TitleApi.sendTitle(p, "Não tem jogadores suficientes para iniciar a partida!", 1, 2, 1 );
                    }

                    time = 0;

                }

            }
        }, 0, 20);
    }

    public void stop(){
        Bukkit.getScheduler().cancelTask(scheduler);
    }

    public String getTimeStr(){

        int s = time;
        int m = 0;

        for(int i = 0; i < (s/60); i++){
            m++;
        }
        s = s -(m * 60);

        return m + "m " + s + "s";
    }

}
