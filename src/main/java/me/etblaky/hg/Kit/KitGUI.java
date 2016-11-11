package me.etblaky.hg.Kit;

import me.etblaky.hg.Game.Game;
import me.etblaky.vip.VipSys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

    public ArrayList<Material> vipkits = new ArrayList<Material>();

    public Kit kit;

    public Inventory inv = Bukkit.createInventory(null, 18, "Escolha um kit.");

    public KitGUI(){
    }

    public KitGUI(Kit k) {
        kit = k;

        vipkits.add(Material.WOOD_SWORD); //Achillis
        vipkits.add(Material.ANVIL);//Anchor

        inv.setItem(0, getItem(Material.WOOD_SWORD, "Achillis", ChatColor.WHITE + "Espada de Diamante te dará dano de Espada de Madeira,", ChatColor.WHITE + "a de Ferro te dará dano de Pedra", ChatColor.WHITE + "e o mesmo ao contrario."));
        inv.setItem(1, getItem(Material.ANVIL, "Anchor", ChatColor.WHITE + "Você e seu adversario não receberão knockback"));
        inv.setItem(2, getItem(Material.BOW, "Archer", ChatColor.WHITE + "Comece com um arco encantado e algumas flechas."));
        inv.setItem(3, getItem(Material.IRON_SWORD, "Assasin", ChatColor.WHITE + "Carrega sua abilidade segurando shif", ChatColor.WHITE + "e ganha força na hora da batalha."));
        inv.setItem(4, getItem(Material.DIAMOND_SWORD, "Barbarian", ChatColor.WHITE + "Evulua sua espada a cada kill."));
        inv.setItem(5, getItem(Material.GRASS, "Basic", ChatColor.WHITE + "Test kit."));
        inv.setItem(6, getItem(Material.BONE, "BeastMaster", ChatColor.WHITE + "Spawne 3 lobos que vão te ajudar nas batalhas!"));
        inv.setItem(7, getItem(Material.WOOD_AXE, "Berserker", ChatColor.WHITE + "Ganhe força ao matar um animal ou player."));
        inv.setItem(8, getItem(Material.NETHER_STAR, "Blink", ChatColor.WHITE + "Se teleporte para onde estiver olhando", ChatColor.WHITE + "quando estiver segurado uma estrela do nether!"));
        inv.setItem(8, getItem(Material.STONE_SWORD, "Boxer", ChatColor.WHITE + "De dano de uma espada de pedra com a mão", ChatColor.WHITE + "e leve menos dano."));
        inv.setItem(9, getItem(Material.SAND, "Camel", ChatColor.WHITE + "Ande mais rápido no areia e", ChatColor.WHITE + "Crafte sopa com cacto e areia"));

        inv.setItem(10, getItem(Material.FIREWORK, "Kangaroo", ChatColor.WHITE + "Ganhe um impulso ", ChatColor.WHITE + "quando usar o firework", ChatColor.WHITE + "e não tome dano de queda!"));

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getInventory();


        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(player.getUniqueId())){
                    kit = g.getLobby().getKit();
                }
            }
        }

        if (inventory.getName().equals(inv.getName())) {

            //TODO: Verify if the player is VIP

            if (clicked.getType() == Material.WOOD_SWORD) {
                e.setCancelled(true);
                player.closeInventory();

                //VIP
                if(!VipSys.isVip((Player) e.getWhoClicked())){ e.getWhoClicked().sendMessage(ChatColor.RED + "Esse kit é apenas para VIPs! Adquira o seu em: Site.com"); return; }

                kit.playersKits.put(player, Kit.Kits.ACHILLES);

            }
            if (clicked.getType() == Material.ANVIL) {
                e.setCancelled(true);
                player.closeInventory();

                //VIP
                if(!VipSys.isVip((Player) e.getWhoClicked())){ e.getWhoClicked().sendMessage(ChatColor.RED + "Esse kit é apenas para VIPs! Adquira o seu em: Site.com"); e.setCancelled(true); return; }

                kit.playersKits.put(player, Kit.Kits.ANCHOR);
            }

            if (clicked.getType() == Material.BOW) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.ARCHER);
            }
            if (clicked.getType() == Material.IRON_SWORD) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.ASSASSIN);
            }
            if (clicked.getType() == Material.DIAMOND_SWORD) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.BARBARIAN);
            }
            if (clicked.getType() == Material.GRASS) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.BASIC);
            }
            if (clicked.getType() == Material.BONE) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.BEASTMASTER);
            }
            if (clicked.getType() == Material.WOOD_AXE) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.BERSERKER);
            }
            if (clicked.getType() == Material.NETHER_STAR) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.BLINK);
            }
            if (clicked.getType() == Material.STONE_SWORD) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.BOXER);
            }
            if (clicked.getType() == Material.SAND) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.CAMEL);
            }
            if (clicked.getType() == Material.FIREWORK) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.KANGAROO);
            }
        }
    }

    public Inventory getInv(){
        return inv;
    }

    public ItemStack getItem(Material m, String name, String... desc){
        ItemStack is = new ItemStack(m);
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<String>();

        for(String s : desc){
            lore.add(s);
        }

        if(vipkits.contains(m)){
            lore.add(ChatColor.GOLD + "VIP");
        }

        im.setLore(lore);

        is.setItemMeta(im);

        return is;
    }

}
