package me.etblaky.hg.Game;

import me.etblaky.Main;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class Game {

    public String name;

    public GameScoreboard board;
    public GameTimer timer;

    public Kit kits;

    public Lobby lobby;

    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> spectators = new ArrayList<Player>();

    public Location loc = new Location(Bukkit.getWorld("world"), -6 , 4, 23);

    public static ArrayList<Game> games = new ArrayList<Game>();

    public Game(String n, Lobby l) {
        name = n;

        lobby = l;

        board = new GameScoreboard(this);
        timer = new GameTimer(this);

        kits = lobby.getKit();

        games.add(this);
    }

    public String getName(){
        return name;
    }

    public GameScoreboard getBoard() {
        return board;
    }

    public GameTimer getTimer() {
        return timer;
    }

    public Kit getKit(){
        return kits;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        for(int i = 0; i < players.size() - 1; i++){
            if(players.get(i).getUniqueId().equals(p.getUniqueId())){
                players.remove(i);
            }

            players.get(i).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

            kits.removeAbilities(p);

            p.teleport(Main.getSpawn());
        }

        for(int i = 0; i < spectators.size(); i++){
            if(spectators.get(i).getUniqueId().equals(p.getUniqueId())){
                spectators.remove(i);
            }

            spectators.get(i).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

            p.teleport(Main.getSpawn());
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

    public void setSpectator(Player p){
        p.setGameMode(GameMode.SPECTATOR);
        spectators.add(p);

        for(int i = 0; i < players.size()  - 1; i ++){
            if(players.get(i).getUniqueId().equals(p.getUniqueId())){
                players.remove(i);
            }
        }

        p.teleport(loc);
    }

    public static List<Game> getGames(){
        return games;
    }

    public void start(Kit k){
        this.kits = k;

        for(Player p : players) {
            p.getInventory().clear();
            for (ItemStack is : kits.getItems(p)) {
                if(is.getMaxStackSize() > 1){
                    p.getInventory().addItem(is);
                }
                else {
                    for(int i = 0; i < is.getAmount(); i ++){
                        p.getInventory().addItem(new ItemStack(is.getType()));
                    }
                }
            }

            kits.setAbilities(p);

            p.teleport(loc);

        }

        //TODO: Custom spawn location

        getTimer().start();
    }

    public void stop(){
        lobby.state = Lobby.MatchState.RELOADING;

        getTimer().stop();

        for(Player p : players) {
            p.getInventory().clear();
            p.setGameMode(GameMode.CREATIVE);
            p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            kits.removeAbilities(p);
            p.teleport(Main.getSpawn());
        }

        kits.playersKits.clear();
        lobby.reset();
        reset();

    }

    public void reset(){
        timer.time = 0;
        players.clear();
        spectators.clear();
    }

}
