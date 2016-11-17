package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by ETblaky on 07/11/2016.
 */
public class Camel extends KitBase {

    public ItemStack[] items = new ItemStack[] {};
    public String name = "Camel";

    public Kit k;

    public Camel(){

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);

        ShapedRecipe soupR = new ShapedRecipe(soup);
        soupR.shape(
                "#@#",
                " # ");
        soupR.setIngredient('@', Material.CACTUS);
        soupR.setIngredient('#', Material.SAND);
        Bukkit.getServer().addRecipe(soupR);

    }

    public Camel(Kit k){ this.k = k; }

    public ItemStack[] getItems(){ return items; }

    public String getName() { return name; }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){
        p.removePotionEffect(PotionEffectType.SPEED);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals((e.getPlayer()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(k== null) return;
        if(!k.isKit(e.getPlayer(), Kit.Kits.CAMEL)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        Block b = e.getPlayer().getLocation().getWorld().getBlockAt(e.getPlayer().getLocation().getBlockX(), e.getPlayer().getLocation().getBlockY() - 1, e.getPlayer().getLocation().getBlockZ());
        if(!b.getType().equals(Material.SAND)) return;

        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 2));

    }

    @EventHandler
    public void onCraftItem(CraftItemEvent e){
        if(!k.isKit((Player) e.getWhoClicked(), Kit.Kits.CAMEL)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        if(!e.getRecipe().getResult().getType().equals(Material.MUSHROOM_SOUP)) return;
        if(!(e.getRecipe() instanceof ShapedRecipe)) return;

        e.setCurrentItem(new ItemStack(Material.AIR));
        e.setCancelled(true);

    }

}
