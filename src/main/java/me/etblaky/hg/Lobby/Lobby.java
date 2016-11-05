package me.etblaky.hg.Lobby;

import me.etblaky.Main;
import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class Lobby {

    public enum MatchState {
        LOBBY,
        GAME,
        RELOADING;
    }

    public MatchState state;

    public LobbyScoreboard board;
    public LobbyTimer timer;

    public Kit kits;

    public Game game;

    public KitGUI gui;

    public ArrayList<Player> players = new ArrayList<Player>();
    public Location loc = new Location(Bukkit.getWorld("world"), 23 , 4, 23);

    public ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    public Lobby(String n) {
        state = MatchState.RELOADING;

        game = new Game(n, this);

        board = new LobbyScoreboard(this);
        timer = new LobbyTimer(this);

        kits = new Kit();

        gui = new KitGUI(kits);

        LobbyListener.setUp(this);

        ItemStack watch = new ItemStack(Material.WATCH);
        ItemMeta imWatch = watch.getItemMeta();

        imWatch.setDisplayName("Voltar");
        watch.setItemMeta(imWatch);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta imCompass = compass.getItemMeta();

        imCompass.setDisplayName("Kits");
        compass.setItemMeta(imCompass);

        items.add(watch);
        items.add(compass);

        start();
    }

    public LobbyScoreboard getBoard() {
        return board;
    }

    public LobbyTimer getTimer() {
        return timer;
    }

    public Kit getKit(){
        return kits;
    }

    public Game getGame() {
        return game;
    }

    public KitGUI getGUI() { return gui; }

    public List<Player> getPlayers(){
        return players;
    }

    public boolean addPlayer(Player p){
        if(players.contains(p)) {p.sendMessage("You already are in this match!"); return false;}
        if(!state.equals(MatchState.LOBBY)) {p.sendMessage("You cannot enter in this match!"); return false; }
        if(players.size() == 60) {p.sendMessage("This match is full!"); return false; }

        p.getInventory().clear();

        p.teleport(loc);
        players.add(p);

        kits.playersKits.put(p, Kit.Kits.BASIC);

        for(ItemStack is : items){

            if(is.getType().equals(Material.WATCH)){
                p.getInventory().setItem(8, is);
            }
            if(is.getType().equals(Material.COMPASS)) {
                p.getInventory().setItem(0, is);
            }
        }

        return true;
    }

    public void removePlayer(Player p){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getUniqueId().equals(p.getUniqueId())){
                players.get(i).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                players.get(i).getInventory().clear();
                players.remove(i);
            }
        }

        p.teleport(Main.getSpawn());
    }

    public void broadcast(String s){
        for(Player p : players){
            p.sendMessage(s);
        }
    }

    public void start(){
        getTimer().start();

        state = MatchState.LOBBY;

    }

    public void stop(){
        getTimer().stop();

        for(Player p : players) {
            game.addPlayer(p);
        }

        game.start(kits);

        state = MatchState.GAME;

    }

    public void reset(){

        state = MatchState.RELOADING;

        players.clear();
        timer.time = 0;

        start();
    }

}
