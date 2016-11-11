package me.etblaky.hg;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Game.GameListener;
import me.etblaky.hg.Kit.KitGUI;
import me.etblaky.hg.Kit.Kits.*;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.hg.Lobby.LobbyListener;
import me.etblaky.vip.VipSys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class Main extends JavaPlugin implements Listener{

    public void onEnable(){
        setUpEvents();
        setUpKits();

        createGames();

    }

    public void createGames(){
        if(this.getConfig().getConfigurationSection("Matches") == null) return;
        for(String key : this.getConfig().getConfigurationSection("Matches").getKeys(false)){
            Lobby l = new Lobby(key);

            World wL = Bukkit.getWorld(this.getConfig().getString("Matches." + key + ".Lobby.world"));
            double xL = this.getConfig().getDouble("Matches." + key + ".Lobby.X");
            double yL = this.getConfig().getDouble("Matches." + key + ".Lobby.Y");
            double zL = this.getConfig().getDouble("Matches." + key + ".Lobby.Z");
            float yawL = Float.valueOf(String.valueOf(this.getConfig().getDouble("Matches." + key + ".Lobby.Yaw")));
            float pitchL = Float.valueOf(String.valueOf(this.getConfig().getDouble("Matches." + key + ".Lobby.Picth")));

            l.loc = new Location(wL, xL, yL, zL, yawL, pitchL);

            World wG = Bukkit.getWorld(this.getConfig().getString("Matches." + key + ".Game.world"));
            double xG = this.getConfig().getDouble("Matches." + key + ".Game.X");
            double yG = this.getConfig().getDouble("Matches." + key + ".Game.Y");
            double zG = this.getConfig().getDouble("Matches." + key + ".Game.Z");
            float yawG = Float.valueOf(String.valueOf(this.getConfig().getDouble("Matches." + key + ".Game.Yaw")));
            float pitchG = Float.valueOf(String.valueOf(this.getConfig().getDouble("Matches." + key + ".Game.Picth")));

            l.game.loc = new Location(wG, xG, yG, zG, yawG, pitchG);

        }
    }

    public void saveGame(Lobby l){

        World wL = l.loc.getWorld();
        double xL = l.loc.getBlockX();
        double yL = l.loc.getBlockY();
        double zL = l.loc.getBlockZ();
        float yawL = l.loc.getYaw();
        float pitchL = l.loc.getPitch();


        World wG = l.loc.getWorld();
        double xG = l.loc.getBlockX();
        double yG = l.loc.getBlockY();
        double zG = l.loc.getBlockZ();
        float yawG = l.loc.getYaw();
        float pitchG = l.loc.getPitch();

        this.getConfig().set("Matches." + l.game.name + ".Lobby.world", wL.getName());
        this.getConfig().set("Matches." + l.game.name + ".Lobby.X", xL);
        this.getConfig().set("Matches." + l.game.name + ".Lobby.Y", yL);
        this.getConfig().set("Matches." + l.game.name + ".Lobby.Z", zL);
        this.getConfig().set("Matches." + l.game.name + ".Lobby.Yaw", yawL);
        this.getConfig().set("Matches." + l.game.name + ".Lobby.Pitch", pitchL);

        this.getConfig().set("Matches." + l.game.name + ".Game.world", wG.getName());
        this.getConfig().set("Matches." + l.game.name + ".Game.X", xG);
        this.getConfig().set("Matches." + l.game.name + ".Game.Y", yG);
        this.getConfig().set("Matches." + l.game.name + ".Game.Z", zG);
        this.getConfig().set("Matches." + l.game.name + ".Game.Yaw", yawG);
        this.getConfig().set("Matches." + l.game.name + ".Game.Pitch", pitchG);

        this.saveConfig();

    }

    public void setUpEvents(){
        Bukkit.getServer().getPluginManager().registerEvents(new GameListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LobbyListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitGUI(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MatchesGUI(), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    public void setUpKits(){
        Bukkit.getServer().getPluginManager().registerEvents(new Achilles(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Anchor(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Archer(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Assassin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Barbarian(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Basic(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BeastMaster(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Berserker(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Blink(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Boxer(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Camel(), this);
        
        Bukkit.getServer().getPluginManager().registerEvents(new Kangaroo(), this);
    }

    public static void giveItems(Player p){
        p.getInventory().clear();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta imCompass = compass.getItemMeta();

        imCompass.setDisplayName("Entrar numa partida");
        compass.setItemMeta(imCompass);

        p.getInventory().setItem(0, compass);
    }

    public static void verifyStatus(Player p){
        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {

        if(!(sender instanceof Player)) { sender.sendMessage("Apenas um jogador pode fazer isso!"); return true;}

        if(cmd.getName().equalsIgnoreCase("hg")){
            if(args.length < 1) { sender.sendMessage("You need to specify an action!"); return true;}

            if(args[0].equalsIgnoreCase("create")){
                if(args.length < 2) { sender.sendMessage("You need to specify a name!"); return true;}

                Lobby l = new Lobby(args[1]);
                //l.addPlayer((Player) sender);

                saveGame(l);

                sender.sendMessage("Match created!");

                return true;
            }

            if(args[0].equalsIgnoreCase("join")){
                if(args.length < 2) { sender.sendMessage("You need to specify a match!"); return true;}

                for(Game g : Game.getGames()){
                    if(g.getName().equalsIgnoreCase(args[1])){
                        g.getLobby().addPlayer((Player) sender, false);
                        return true;
                    }
                }

                return true;
            }

            if(args[0].equalsIgnoreCase("leave")){

                for(Game g : Game.getGames()){
                    if(g.getPlayers().contains((Player) sender)){
                        g.removePlayer((Player) sender);
                        return true;
                    }
                }

                for(Game g : Game.getGames()){
                    if(g.getLobby().getPlayers().contains((Player) sender)){
                        g.getLobby().removePlayer((Player) sender);
                        return true;
                    }
                }

                return true;
            }

            return true;
        }

        return true;
    }

    public static Plugin getInstance(){
        return Bukkit.getPluginManager().getPlugin("HG-Project");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        verifyStatus(e.getPlayer());
        giveItems(e.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        giveItems(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent e){
        e.getEntity().teleport(me.etblaky.Main.getSpawn());
        e.getEntity().setHealth(e.getEntity().getMaxHealth());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof  Player)) return;
        if(Game.playerGame((Player) e.getEntity()) != null) return;

        if(e.getEntity().getLocation().getY() <= 0) {
            if(Lobby.playerLobby((Player) e.getEntity()) != null){
                e.getEntity().teleport(Lobby.playerLobby((Player) e.getEntity()).loc);
                e.setCancelled(true);
                return;
            }
            e.getEntity().teleport(me.etblaky.Main.getSpawn());
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHungry(FoodLevelChangeEvent e){
        if(!(e.getEntity() instanceof  Player)) return;
        if(Game.playerGame((Player) e.getEntity()) != null) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void clickItem(PlayerInteractEvent e){

        if(e.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {
            if (!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("Entrar numa partida")) return;

            if(VipSys.isVip(e.getPlayer())){
                e.getPlayer().openInventory(MatchesGUI.getInv());
                return;
            }

            for(Game g : Game.getGames()){
                if(g.getLobby().addPlayer(e.getPlayer(), true)){
                    return;
                }
            }

            e.getPlayer().sendMessage("Não há nenhuma partida disponível!");

        }
    }

}

