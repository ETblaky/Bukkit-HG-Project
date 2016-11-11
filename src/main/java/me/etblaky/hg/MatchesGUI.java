package me.etblaky.hg;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by ETblaky on 11/11/2016.
 */
public class MatchesGUI implements Listener{

    public static Inventory inv = Bukkit.createInventory(null, 27, "Escolha em uma partida.");

    public static void setUp(){
        for(Game g : Game.getGames()){
            ItemStack is = new ItemStack(Material.GOLD_BLOCK);
            ItemMeta im = is.getItemMeta();

            im.setDisplayName(g.getName());

            ArrayList<String> lore = new ArrayList<String>();
            lore.add("State: " + g.getLobby().state.toString().toLowerCase());
            im.setLore(lore);

            is.setItemMeta(im);

            inv.addItem(is);
        }
    }

    public static Inventory getInv(){
        setUp();
        return inv;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getInventory();

        if(!inventory.getName().equals(inv.getName())) return;

        for(Game g : Game.getGames()){
            if(g.getName().equalsIgnoreCase(clicked.getItemMeta().getDisplayName())){
                if(g.getLobby().state == Lobby.MatchState.GAME){
                    g.setSpectator(player);
                }
                if(g.getLobby().state == Lobby.MatchState.LOBBY){
                    g.getLobby().addPlayer(player, false);
                }
            }
        }

    }

}
