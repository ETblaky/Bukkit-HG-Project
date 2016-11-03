package me.etblaky.hg.Kit;

import me.etblaky.hg.Game.Game;
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
 * Created by ETblaky on 02/11/2016.
 */
public class KitGUI implements Listener{

    public Kit kit;

    public static Inventory inv = Bukkit.createInventory(null, 9, "Choose a kit");

    public KitGUI(){
    }

    public KitGUI(Kit k) {
        kit = k;

        inv.setItem(0, getItem(Material.WOOD_SWORD, "Basic"));

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getInventory();

        if (inventory.getName().equals(inv.getName())) {

            if (clicked.getType() == Material.WOOD_SWORD) {
                e.setCancelled(true);
                player.closeInventory();
                setKit(player, Kit.Kits.BASIC);
            }

        }
    }

    public static Inventory getInv(){
        return inv;
    }

    public void setKit(Player p, Kit.Kits k){
        for(Game g : Game.getGames()){
            for(Player p1 : g.getPlayers()){
                if(p1.getUniqueId().equals(p.getUniqueId())){
                    g.getKit().setKit(p1, k);
                }
            }
        }
    }

    public ItemStack getItem(Material m, String name){
        ItemStack is = new ItemStack(m);
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Gives you more 5 hearts, a sword and 8 soups.");
        im.setLore(lore);

        is.setItemMeta(im);

        return is;
    }

}
