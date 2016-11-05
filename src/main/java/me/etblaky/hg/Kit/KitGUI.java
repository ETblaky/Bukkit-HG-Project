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

    public Inventory inv = Bukkit.createInventory(null, 9, "Choose a kit");

    public KitGUI(){
    }

    public KitGUI(Kit k) {
        kit = k;

        inv.setItem(0, getItem(Material.GRASS, "Basic", "Gives you more 5 hearts, a sword and 8 soups."));
        inv.setItem(1, getItem(Material.WOOD_SWORD, "Achillis", "Espada de Diamante te dará dano de Espada de Madeira, a de Ferro te dará dano de Pedra, o mesmo ao contrario."));
        inv.setItem(2, getItem(Material.ANVIL, "Anchor", "Você e seu adversario não receberão knockback"));

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getInventory();

        if (inventory.getName().equals(inv.getName())) {

            if (clicked.getType() == Material.GRASS) {
                e.setCancelled(true);
                player.closeInventory();

                for(Game g : Game.getGames()){
                    if(g.getLobby().getPlayers().contains(player)){
                        g.getLobby().getKit().playersKits.put(player, Kit.Kits.BASIC);

                        System.out.println(g.getLobby().getKit().playersKits.values());
                    }
                }
            }

            if (clicked.getType() == Material.WOOD_SWORD) {
                e.setCancelled(true);
                player.closeInventory();

                for(Game g : Game.getGames()){
                    if(g.getLobby().getPlayers().contains(player)){
                        g.getLobby().getKit().playersKits.put(player, Kit.Kits.ACHILLES);

                        System.out.println(g.getLobby().getKit().playersKits.values());
                    }
                }

            }

            if (clicked.getType() == Material.ANVIL) {
                e.setCancelled(true);
                player.closeInventory();

                for(Game g : Game.getGames()){
                    if(g.getLobby().getPlayers().contains(player)){
                        g.getLobby().getKit().playersKits.put(player, Kit.Kits.ANCHOR);

                        System.out.println(g.getLobby().getKit().playersKits.values());
                    }
                }
            }

        }
    }

    public Inventory getInv(){
        return inv;
    }

    public ItemStack getItem(Material m, String name, String desc){
        ItemStack is = new ItemStack(m);
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<String>();

        String[] descArr = desc.split(",");

        for(String s : descArr){
            lore.add(s);
        }

        im.setLore(lore);

        is.setItemMeta(im);

        return is;
    }

}
