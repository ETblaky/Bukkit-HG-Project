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
import java.util.HashMap;

/**
 * Created by ETblaky on 02/11/2016.
 */
public class KitGUI implements Listener{

    public ArrayList<Material> vipkits = new ArrayList<Material>();
    public HashMap<String, Object[]> kits = new HashMap<String, Object[]>();

    public Kit kit;

    public Inventory inv = Bukkit.createInventory(null, 18, "Escolha um kit.");
    public Inventory vipInv = Bukkit.createInventory(null, 18, "Escolha um segundo kit.");

    public KitGUI(){
        setUp();
    }

    public KitGUI(Kit k) {
        kit = k;
        setUp();
    }

    public void setUp(){
        vipkits.clear();
        kits.clear();
        inv.clear();
        vipInv.clear();

        vipkits.add(Material.WOOD_SWORD); //Achillis
        vipkits.add(Material.ANVIL);//Anchor

        kits.put("Achilles", new Object[] {Material.WOOD_SWORD, new String[] {"Espada de Diamante te dará dano de Espada de Madeira,", "a de Ferro te dará dano de Pedra", "e o mesmo ao contrario."} });
        kits.put("Anchor", new Object[] {Material.ANVIL, new String[] {"Você e seu adversario não receberão knockback"} });
        kits.put("Archer", new Object[] {Material.BOW, new String[] {"Comece com um arco encantado e algumas flechas."} });
        kits.put("Assassin", new Object[] {Material.IRON_SWORD, new String[] {"Carrega sua abilidade segurando shift", "e ganha força na hora da batalha."} });
        kits.put("Barbarian", new Object[] {Material.DIAMOND_SWORD, new String[] {"Evolua sua espada a cada kill."} });
        kits.put("Basic", new Object[] {Material.GRASS, new String[] {"Test kit."} });
        kits.put("BeastMaster", new Object[] {Material.BONE, new String[] {"Spawne 3 lobos que vão te ajudar nas batalhas!"} });
        kits.put("Berserker", new Object[] {Material.WOOD_AXE, new String[] {"Ganhe força ao matar um animal ou player."} });
        kits.put("Blink", new Object[] {Material.NETHER_STAR, new String[] {"Se teleporte para onde estiver olhando", "quando estiver segurado uma estrela do nether!"} });
        kits.put("Boxer", new Object[] {Material.STONE_SWORD, new String[] {"De dano de uma espada de pedra com a mão", "e leve menos dano."} });
        kits.put("Camel", new Object[] {Material.SAND, new String[] {"Ande mais rápido no areia e", "crafte sopa com cacto e areia"} });
        kits.put("Endermage", new Object[] {Material.ENDER_PORTAL_FRAME, new String[] {"Puxe os jogadores que ", "estiverem abaixo de você."} });
        kits.put("Kangaroo", new Object[] {Material.FIREWORK, new String[] {"Ganhe um impulso ", "quando usar o firework", "e não tome dano de queda!"} });
        kits.put("Stomper", new Object[] {Material.DIAMOND_BOOTS, new String[] {"Transfira seu dano de queda ", "para players a 5 blocos ", "de onde você cair "} });

        for(String s : kits.keySet()){
            vipInv.addItem(getItem((Material) kits.get(s)[0], s, (String[]) kits.get(s)[1]));
            inv.addItem(getItem((Material) kits.get(s)[0], s, (String[]) kits.get(s)[1]));
        }

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

        setUp();

        if (inventory.getName().equals(inv.getName())) {

            for(String s : kits.keySet()){
                e.setCancelled(true);
                player.closeInventory();

                if(kits.get(s)[0] == clicked.getType() ){

                    if(vipkits.contains(kits.get(s)[0])) {
                        if(!VipSys.isVip((Player) e.getWhoClicked())){
                            ((Player) e.getWhoClicked()).sendMessage(ChatColor.RED + "Esse kit é apenas para VIPs! Adquira o seu em: kangarooKits.com.br/vip");
                            return;
                        }
                    }

                    kit.playersKits.put(player, Kit.Kits.valueOf(s.toUpperCase()));
                }
            }
        }
    }

    @EventHandler
    public void onVipInventoryClick(InventoryClickEvent e) {

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

        setUp();

        if (inventory.getName().equals(vipInv.getName())) {

            for(String s : kits.keySet()){
                e.setCancelled(true);
                player.closeInventory();

                if(kits.get(s)[0] == clicked.getType() ){

                    if(vipkits.contains(kits.get(s)[0])) {
                        if(!VipSys.isVip((Player) e.getWhoClicked())){
                            ((Player) e.getWhoClicked()).sendMessage(ChatColor.RED + "Esse kit é apenas para VIPs! Adquira o seu em: kangarooKits.com.br/vip");
                            return;
                        }
                    }

                    kit.vipsSecondKits.put(player, Kit.Kits.valueOf(s.toUpperCase()));
                }
            }
        }
    }

    public Inventory getInv(){
        return inv;
    }

    public Inventory getVipInv(){
        return vipInv;
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
