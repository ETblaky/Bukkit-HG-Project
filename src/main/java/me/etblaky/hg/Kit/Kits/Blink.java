package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.hg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class Blink extends KitBase {

    public ItemStack[] items = new ItemStack[] {is(Material.NETHER_STAR, 1)};
    public String name = "Blink";

    public Kit k;

    public Blink(){ }

    public Blink(Kit k){ this.k = k; }

    public ItemStack[] getItems(){ return items; }

    public String getName() { return name; }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

    HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
    HashMap<Player, Integer> task = new HashMap<Player, Integer>();

    @EventHandler
    public void playerBlink(PlayerInteractEvent e){
        for(Game g : Game.getGames()){
            for(Player p : g.getLobby().getPlayers()){
                if(p.getUniqueId().equals(e.getPlayer().getUniqueId())){
                    k = g.getLobby().getKit();
                }
            }
        }

        if(!e.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)) return;
        if(!k.isKit(e.getPlayer(), Kit.Kits.BLINK)) return;
        if(!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;

        if(cooldown.get(e.getPlayer()) == null) { cooldown.put(e.getPlayer(), 0); }
        if(cooldown.get(e.getPlayer()) > 0) { e.getPlayer().sendMessage("Espere mais " + cooldown.get(e.getPlayer()) + " segundos!"); return; }

        Location loc = e.getPlayer().getTargetBlock((HashSet<Byte>) null, 100).getLocation().add(0, 1, 0).setDirection(e.getPlayer().getLocation().getDirection());
        e.getPlayer().teleport(loc);

        final PlayerInteractEvent ev = e;
        cooldown.put(ev.getPlayer(), 10);
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
}
