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

        inv.addItem(getItem(Material.WOOD_SWORD, "Achillis", "Espada de Diamante te dará dano de Espada de Madeira,", "a de Ferro te dará dano de Pedra", "e o mesmo ao contrario."));
        inv.addItem(getItem(Material.ANVIL, "Anchor", "Você e seu adversario não receberão knockback"));
        inv.addItem(getItem(Material.BOW, "Archer", "Comece com um arco encantado e algumas flechas."));
        inv.addItem(getItem(Material.IRON_SWORD, "Assassin", "Carrega sua abilidade segurando shift", "e ganha força na hora da batalha."));
        inv.addItem(getItem(Material.DIAMOND_SWORD, "Barbarian", "Evolua sua espada a cada kill."));
        //inv.addItem(getItem(Material.GRASS, "Basic", "Test kit."));
        inv.addItem(getItem(Material.BONE, "BeastMaster", "Spawne 3 lobos que vão te ajudar nas batalhas!"));
        inv.addItem(getItem(Material.WOOD_AXE, "Berserker", "Ganhe força ao matar um animal ou player."));
        inv.addItem(getItem(Material.NETHER_STAR, "Blink", "Se teleporte para onde estiver olhando", "quando estiver segurado uma estrela do nether!"));
        inv.addItem(getItem(Material.STONE_SWORD, "Boxer", "De dano de uma espada de pedra com a mão", "e leve menos dano."));
        inv.addItem(getItem(Material.SAND, "Camel", "Ande mais rápido no areia e", "Crafte sopa com cacto e areia"));

        inv.addItem(getItem(Material.ENDER_PORTAL_FRAME, "Endermage", "Puxe os jogadores que ", "estiverem abaixo de você."));

        inv.addItem(getItem(Material.FIREWORK, "Kangaroo", "Ganhe um impulso ", "quando usar o firework", "e não tome dano de queda!"));

        inv.addItem(getItem(Material.DIAMOND_BOOTS, "Stomper", "Transfira seu dano de queda ", "para players a 5 blocos ", "de onde você cair "));

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
                if(!VipSys.isVip((Player) e.getWhoClicked())){ ((Player) e.getWhoClicked()).sendMessage(ChatColor.RED + "Esse kit é apenas para VIPs! Adquira o seu em: kangarooKits.com.br/vip"); return; }

                kit.playersKits.put(player, Kit.Kits.ACHILLES);

            }
            if (clicked.getType() == Material.ANVIL) {
                e.setCancelled(true);
                player.closeInventory();

                //VIP
                if(!VipSys.isVip((Player) e.getWhoClicked())){ ((Player) e.getWhoClicked()).sendMessage(ChatColor.RED + "Esse kit é apenas para VIPs! Adquira o seu em: kangarooKits.com.br/vip");  return; }

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
            if (clicked.getType() == Material.ENDER_PORTAL_FRAME) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.ENDERMAGE);
            }
            if (clicked.getType() == Material.FIREWORK) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.KANGAROO);
            }
            if (clicked.getType() == Material.DIAMOND_BOOTS) {
                e.setCancelled(true);
                player.closeInventory();
                kit.playersKits.put(player, Kit.Kits.STOMPER);
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
            lore.add(ChatColor.WHITE + s);
        }

        if(vipkits.contains(m)){
            lore.add(ChatColor.GOLD + "VIP");
        }

        im.setLore(lore);

        is.setItemMeta(im);

        return is;
    }

}
