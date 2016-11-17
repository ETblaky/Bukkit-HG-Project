package me.etblaky.hg.Game;

import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 02/11/2016.
 */
public class GameListener implements Listener {

    @EventHandler
    public void onPlayerDie(final PlayerDeathEvent e){

        System.out.println("player died: " + e.getEntity().getName());

        final Game g = Game.playerGame(e.getEntity());

        if(g == null) return;

        g.removePlayer(e.getEntity());
        g.setSpectator(e.getEntity());

    }

    @EventHandler
    public void onPlayerLeave(final PlayerQuitEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;

        Game.playerGame(e.getPlayer()).removePlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof  Player)) return;
        if(Game.playerGame((Player) e.getEntity()) == null) return;
        if(!(Game.playerGame((Player) e.getEntity()).getTimer().getTime() < 60)) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerUseSoup(final FoodLevelChangeEvent e){
        if(Game.playerGame((Player) e.getEntity()) == null) return;
        if(!e.getEntity().getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) return;

        e.setCancelled(true);

    }

    @EventHandler
    public void onPlayerUseSoup(final PlayerInteractEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(!e.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) return;

        e.setCancelled(true);

        if(e.getPlayer().getHealth() == e.getPlayer().getMaxHealth()) return;

        e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), new ItemStack(Material.BOWL));

        if(e.getPlayer().getHealth() + 6 > e.getPlayer().getMaxHealth()){ e.getPlayer().setHealth(e.getPlayer().getMaxHealth()); return;}

        e.getPlayer().setHealth(e.getPlayer().getHealth() + 6);

    }

    @EventHandler
    public void onPlayerUseComapass(final PlayerInteractEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!e.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) return;

        if(getClosest(e.getPlayer()) == null) { e.getPlayer().sendMessage("Nenhum player proximo encontrado."); return; }

        e.getPlayer().setCompassTarget(getClosest(e.getPlayer()).getLocation());
        e.getPlayer().sendMessage("Apontando para " + getClosest(e.getPlayer()).getName() + ".");

        e.getPlayer().updateInventory();

    }

    public Player getClosest(Player p){

        double closest = Double.MAX_VALUE;
        Player closestp = null;
        for(Player i : Bukkit.getServer().getOnlinePlayers()){
            double dist = i.getLocation().distance(p.getLocation());
            if (closest == Double.MAX_VALUE || dist < closest){
                if(!i.getUniqueId().equals(p.getUniqueId())){
                    closest = dist;
                    closestp = i;
                }
            }
        }

        return closestp;
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!Game.playerGame(e.getPlayer()).spectators.contains(e.getPlayer())) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!Game.playerGame(e.getPlayer()).spectators.contains(e.getPlayer())) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!Game.playerGame(e.getPlayer()).spectators.contains(e.getPlayer())) return;



        e.setCancelled(true);
    }

    @EventHandler
    public void onPickUpItem(PlayerPickupItemEvent e){
        if(Game.playerGame(e.getPlayer()) == null) return;
        if(!Game.playerGame(e.getPlayer()).spectators.contains(e.getPlayer())) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e){
        if(Lobby.playerLobby(e.getPlayer()) == null) return;

        Player p = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack().clone();
        e.getItemDrop().remove();
        p.getInventory().setItem(p.getInventory().getHeldItemSlot(), item);

        p.updateInventory();
    }

}