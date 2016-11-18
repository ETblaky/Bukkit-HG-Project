package me.etblaky.hg.Game;

import me.etblaky.Main;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.titles.TitleApi;
import me.etblaky.vip.VipSys;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    public Location loc; //= new Location(Bukkit.getWorld("world"), -6 , 4, 23);

    public Location cakeLoc; //= new Location(Bukkit.getWorld("world"), -6 , 4, 23);

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

    public static Game playerGame(Player p){

        for(Game g : Game.getGames()){
            for(Player p1 : g.getPlayers()){
                if (p1.getUniqueId().equals(p.getUniqueId())) {
                    return g;
                }
            }
        }

        for(Game g : Game.getGames()){
            for(Player p1 : g.spectators){
                if (p1.getUniqueId().equals(p.getUniqueId())) {
                    return g;
                }
            }
        }

        return null;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){

        for(int i = 0; i < players.size(); i++){

            if(players.get(i).getUniqueId().equals(p.getUniqueId())){
                System.out.println("Removing " + players.get(i).getName());
                players.remove(i);
            }

            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

            kits.removeAbilities(p, false);
            if(VipSys.isVip(p)) { kits.removeAbilities(p, true); }

            p.getInventory().clear();
            me.etblaky.hg.Main.giveItems(p);

            p.teleport(Main.getSpawn());
            p.setGameMode(GameMode.ADVENTURE);

            p.updateInventory();
        }

        if(players.size() == 1) { stop(); }

    }

    public void removeSpectator(Player p){
        for(int i = 0; i < spectators.size() - 1; i++){
            if(spectators.get(i).getUniqueId().equals(p.getUniqueId())){
                spectators.remove(i);
            }

            spectators.get(i).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

            kits.removeAbilities(p, false);
            if(VipSys.isVip(p)){ kits.removeAbilities(p, true); }

            players.get(i).getInventory().clear();
            me.etblaky.hg.Main.giveItems(players.get(i));

            p.teleport(Main.getSpawn());
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

    public void setSpectator(Player p){
        System.out.println("Setting spectator: " + p.getName());

        p.setGameMode(GameMode.CREATIVE);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 10));
        spectators.add(p);

        p.teleport(loc);
    }

    public static List<Game> getGames(){
        return games;
    }

    public void start(Kit k){
        this.kits = k;

        for(Player p : players) {

            p.getInventory().clear();

            p.getInventory().addItem(new ItemStack(Material.COMPASS));

            if(kits.getItems(p, false) != null) {

                for (ItemStack is : kits.getItems(p, false)) {

                    if (is.getMaxStackSize() > 1) {
                        if (is.getItemMeta().getLore() != null) {
                            ItemMeta im = is.getItemMeta();
                            im.setLore(is.getItemMeta().getLore());
                            is.setItemMeta(im);
                        }
                        p.getInventory().addItem(is);
                    } else {
                        for (int i = 0; i < is.getAmount(); i++) {
                            ItemStack is1 = new ItemStack(is.getType());
                            if (is.getItemMeta().getEnchants().size() > 0) {
                                for (Enchantment e : is.getItemMeta().getEnchants().keySet()) {
                                    ItemMeta im = is1.getItemMeta();
                                    im.addEnchant(e, is.getItemMeta().getEnchants().get(e), true);
                                    is1.setItemMeta(im);
                                }
                            }
                            if (is.getItemMeta().getLore() != null) {
                                ItemMeta im = is1.getItemMeta();
                                im.setLore(is.getItemMeta().getLore());
                                is1.setItemMeta(im);
                            }
                            p.getInventory().addItem(is1);
                        }
                    }

                }
            }

            if(VipSys.isVip(p) && kits.getItems(p, true) != null){
                for (ItemStack is : kits.getItems(p, true)) {

                    if(is.getMaxStackSize() > 1){
                        if(is.getItemMeta().getLore() != null){
                            ItemMeta im = is.getItemMeta();
                            im.setLore(is.getItemMeta().getLore());
                            is.setItemMeta(im);
                        }
                        p.getInventory().addItem(is);
                    }

                    else {
                        for(int i = 0; i < is.getAmount(); i ++){
                            ItemStack is1 = new ItemStack(is.getType());
                            if(is.getItemMeta().getEnchants().size() > 0){
                                for(Enchantment e : is.getItemMeta().getEnchants().keySet()){
                                    ItemMeta im = is1.getItemMeta();
                                    im.addEnchant(e, is.getItemMeta().getEnchants().get(e), true);
                                    is1.setItemMeta(im);
                                }
                            }
                            if(is.getItemMeta().getLore() != null){
                                ItemMeta im = is1.getItemMeta();
                                im.setLore(is.getItemMeta().getLore());
                                is1.setItemMeta(im);
                            }
                            p.getInventory().addItem(is1);
                        }
                    }

                }
            }

            kits.setAbilities(p, false);
            if(VipSys.isVip(p)){ kits.setAbilities(p, true); }

            p.teleport(loc);
            p.setGameMode(GameMode.SURVIVAL);
            p.setExp(0);
            p.setLevel(0);
            p.updateInventory();

            TitleApi.sendTitle(p, ChatColor.GREEN + "Começou!", 1, 1, 1);

        }

        getTimer().start();
    }

    public void stop(){

        System.out.println("Stopping");

        lobby.state = Lobby.MatchState.RELOADING;

        Player lastOne = null;

        if(players.size() == 1) { lastOne = players.get(0); }

        if(lastOne != null){
            //TODO: Telepot p1 to cake area

            lastOne.teleport(cakeLoc);
            lastOne.setGameMode(GameMode.ADVENTURE);

            for(Player p : spectators) {
                //TODO: Telepot spectators to cake area
                p.teleport(cakeLoc);
            }
        }

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(me.etblaky.hg.Main.getInstance(), new Runnable() {
            public void run() {
                getTimer().stop();

                for(Player p : players) {
                    p.getInventory().clear();

                    p.setGameMode(GameMode.ADVENTURE);

                    p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

                    kits.removeAbilities(p, false);
                    if(VipSys.isVip(p)) { kits.removeAbilities(p, true); }

                    p.teleport(Main.getSpawn());

                    me.etblaky.hg.Main.verifyStatus(p);
                    me.etblaky.hg.Main.giveItems(p);

                    p.updateInventory();
                }

                for(Player p : spectators) {
                    p.getInventory().clear();

                    p.setGameMode(GameMode.ADVENTURE);
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);

                    p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

                    kits.removeAbilities(p, false);
                    if(VipSys.isVip(p)) { kits.removeAbilities(p, true); }

                    p.teleport(Main.getSpawn());

                    me.etblaky.hg.Main.verifyStatus(p);
                    me.etblaky.hg.Main.giveItems(p);

                    p.updateInventory();
                }

                kits.playersKits.clear();
                lobby.reset();
                reset();
            }
        }, 30 * 20);

    }

    public void reset(){
        timer.time = 0;
        players.clear();
        spectators.clear();
    }

}
