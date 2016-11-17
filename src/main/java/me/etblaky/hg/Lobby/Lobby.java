package me.etblaky.hg.Lobby;

import me.etblaky.Main;
import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitGUI;
import me.etblaky.vip.VipSys;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

    public static ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    public Lobby(String n) {
        state = MatchState.RELOADING;

        game = new Game(n, this);

        board = new LobbyScoreboard(this);
        timer = new LobbyTimer(this);

        kits = new Kit(this);

        gui = new KitGUI(kits);

        LobbyListener.setUp(this);

        ItemStack watch = new ItemStack(Material.WATCH);
        ItemMeta imWatch = watch.getItemMeta();

        imWatch.setDisplayName("Voltar");
        watch.setItemMeta(imWatch);

        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta imChest = chest.getItemMeta();

        imChest.setDisplayName("Kit");
        chest.setItemMeta(imChest);

        ItemStack chestVip = new ItemStack(Material.CHEST);
        ItemMeta imChestVip = chest.getItemMeta();

        imChestVip.setDisplayName("Segundo Kit");
        chestVip.setItemMeta(imChestVip);

        ItemStack star = new ItemStack(Material.NETHER_STAR);
        ItemMeta imStar = star.getItemMeta();

        imStar.setDisplayName("Iniciar a partida");
        star.setItemMeta(imStar);

        items.add(watch);
        items.add(chest);
        items.add(chestVip);
        items.add(star);

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

    public static Lobby playerLobby(Player p){

        for(Game g : Game.getGames()){
            for(Player p1 : g.getLobby().getPlayers()){
                if (p1.getUniqueId().equals(p.getUniqueId())) {
                    return g.getLobby();
                }
            }
        }

        return null;
    }

    public boolean addPlayer(Player p, boolean isCompass){
        if(players.contains(p)) {p.sendMessage("You already are in this match!"); return false;}
        if(!state.equals(MatchState.LOBBY) && !isCompass) {p.sendMessage("You cannot enter in this match!"); return false; }
        if(players.size() == 60 && !isCompass) {p.sendMessage("This match is full!"); return false; }

        p.getInventory().clear();

        p.teleport(loc);
        p.setGameMode(GameMode.ADVENTURE);
        players.add(p);

        kits.playersKits.put(p, Kit.Kits.BASIC);
        if(VipSys.isVip(p)) { kits.vipsSecondKits.put(p, Kit.Kits.BASIC); }

        for(ItemStack is : items){

            if(is.getType().equals(Material.WATCH)){
                p.getInventory().setItem(8, is);
            }
            if(is.getType().equals(Material.CHEST)) {
                if(is.getItemMeta().getDisplayName().equals("Kit")){
                    p.getInventory().setItem(0, is);
                }
                if(is.getItemMeta().getDisplayName().equals("Segundo Kit")){
                    if(VipSys.isVip(p))
                        p.getInventory().setItem(1, is);
                }
            }
            if(is.getType().equals(Material.NETHER_STAR)) {
                if(p.isOp()) {
                    p.getInventory().setItem(4, is);
                }
            }
        }

        p.updateInventory();

        return true;
    }

    public void removePlayer(Player p){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getUniqueId().equals(p.getUniqueId())){
                players.get(i).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                players.get(i).getInventory().clear();
                me.etblaky.hg.Main.giveItems(players.get(i));
                players.get(i).updateInventory();
                players.remove(i);
            }
        }

        p.teleport(Main.getSpawn());
        p.setGameMode(GameMode.ADVENTURE);
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
