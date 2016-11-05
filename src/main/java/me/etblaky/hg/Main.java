package me.etblaky.hg;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Game.GameListener;
import me.etblaky.hg.Kit.KitGUI;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.hg.Lobby.LobbyListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class Main extends JavaPlugin implements Listener{

    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(new GameListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LobbyListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitGUI(), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {

        if(!(sender instanceof Player)) { sender.sendMessage("Apenas um jogador pode fazer isso!"); return true;}

        if(cmd.getName().equalsIgnoreCase("hg")){
            if(args.length < 1) { sender.sendMessage("You need to specify an action!"); return true;}

            if(args[0].equalsIgnoreCase("create")){
                if(args.length < 2) { sender.sendMessage("You need to specify a name!"); return true;}

                new Lobby(args[1]);
                //l.addPlayer((Player) sender);

                sender.sendMessage("Match created!");

                return true;
            }

            if(args[0].equalsIgnoreCase("join")){
                if(args.length < 2) { sender.sendMessage("You need to specify a match!"); return true;}

                for(Game g : Game.getGames()){
                    if(g.getName().equalsIgnoreCase(args[1])){
                        g.getLobby().addPlayer((Player) sender);
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
        e.getPlayer().getInventory().clear();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta imCompass = compass.getItemMeta();

        imCompass.setDisplayName("Entrar numa partida");
        compass.setItemMeta(imCompass);

        e.getPlayer().getInventory().setItem(0, compass);

    }

    @EventHandler
    public void clickItem(PlayerInteractEvent e){

        if(e.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {
            if (!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("Entrar numa partida")) return;

            //TODO: Vips can choose the match.

            for(Game g : Game.getGames()){
                if(g.getLobby().addPlayer(e.getPlayer())){
                    return;
                }
            }

            e.getPlayer().sendMessage("Não há nenhuma partida disponível!");

        }
    }

}

