package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.hg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;

/**
 * Created by ETblaky on 08/11/2016.
 */
public class Kangaroo extends KitBase {

    public ItemStack[] items = new ItemStack[] {is(Material.FIREWORK, 1)};
    public String name = "Kangaroo";

    public Kit k;

    public Kangaroo(){ }

    public Kangaroo(Kit k){ this.k = k; }

    public ItemStack[] getItems(){ return items; }

    public String getName() { return name; }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

    HashMap<Player, Double> cooldown = new HashMap<Player, Double>();
    HashMap<Player, Integer> task = new HashMap<Player, Integer>();

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals((e.getPlayer()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(k== null) return;
        if(k.playersKits == null) return;
        if(k.playersKits.get(e.getPlayer()) == null) return;
        if(!k.playersKits.get(e.getPlayer()).equals(Kit.Kits.KANGAROO)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        if(e.getPlayer().getItemInHand().getType() != Material.FIREWORK) return;

        e.setCancelled(true);

        if(cooldown.get(e.getPlayer()) == null) { cooldown.put(e.getPlayer(), 0.0); }
        if(cooldown.get(e.getPlayer()) > 0) { return; }

        if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) return;

        if(e.getPlayer().isSneaking()){
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
        }
        else {
            e.getPlayer().setVelocity(new Vector(0, 1, 0));
        }

        final PlayerInteractEvent ev = e;
        cooldown.put(ev.getPlayer(), 1.0);
        task.put(e.getPlayer(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                if(cooldown.get(ev.getPlayer()) == null) { return; }
                cooldown.put(ev.getPlayer(),cooldown.get(ev.getPlayer()) - 1);

                if (cooldown.get(ev.getPlayer()) == 0) {
                    Bukkit.getScheduler().cancelTask(task.get(ev.getPlayer()));
                    cooldown.remove(ev.getPlayer());
                    task.remove(ev.getPlayer());
                }
            }
        }, 20, 20));

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals((e.getEntity()).getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(k== null) return;
        if(k.playersKits == null) return;
        if(!(e.getEntity() instanceof Player)) return;
        if(k.playersKits.get(e.getEntity()) == null) return;
        if(!k.playersKits.get(e.getEntity()).equals(Kit.Kits.KANGAROO)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        if(e.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        if(e.getFinalDamage() > 8) e.setDamage(8);

    }

}
