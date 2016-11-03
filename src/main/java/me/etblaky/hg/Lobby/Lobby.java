package me.etblaky.hg.Lobby;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meu computador on 31/10/2016.
 */
public class Lobby {

    public LobbyScoreboard board;
    public LobbyTimer timer;

    public Kit kits;

    public Game game;

    public ArrayList<Player> players = new ArrayList<Player>();
    public Location loc = new Location(Bukkit.getWorld("world"), 23 , 4, 23);

    public ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    public Lobby(String n) {
        game = new Game(n, this);

        board = new LobbyScoreboard(this);
        timer = new LobbyTimer(this);

        kits = new Kit();

        LobbyListener.setUp(this);

        ItemStack watch = new ItemStack(Material.WATCH);
        ItemMeta imWatch = watch.getItemMeta();

        imWatch.setDisplayName("Voltar");
        watch.setItemMeta(imWatch);

        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta imStick = stick.getItemMeta();

        imStick.setDisplayName("Kits");
        stick.setItemMeta(imStick);

        items.add(watch);
        items.add(stick);

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

    public List<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player p){
        p.teleport(loc);
        players.add(p);

        for(ItemStack is : items){

            if(is.getType().equals(Material.WATCH)){
                p.getInventory().setItem(8, is);
            }
            if(is.getType().equals(Material.STICK)) {
                p.getInventory().setItem(0, is);
            }
        }

    }

    public void removePlayer(Player p){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getUniqueId().equals(p.getUniqueId())){
                players.get(i).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                players.get(i).getInventory().clear();
                players.remove(i);
            }
        }

        //TODO: Teleport to spawn
    }

    public void broadcast(String s){
        for(Player p : players){
            p.sendMessage(s);
        }
    }

    public void start(){
        getTimer().start();

    }

    public void stop(){
        getTimer().stop();

        for(Player p : players) {
            game.addPlayer(p);
        }

        game.start();

    }

}
