package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 05/11/2016.
 */
public class BeastMaster extends KitBase {

    public ItemStack[] items = new ItemStack[] {name(is(Material.BONE, 1), "Spawne os lobos!")};
    public String name = "BeastMaster";

    public Kit k;

    public BeastMaster(){ }

    public BeastMaster(Kit k){
        this.k = k;
    }

    public ItemStack[] getItems(){
        return items;
    }

    public String getName() {
        return name;
    }

    public void setAbilities(Player p){ }

    public void removeAbilities(Player p){ }

    @EventHandler
    public void onPlayerSpawnWolf(PlayerInteractEvent e){

        k = setKit(k, e.getPlayer());
        if(k == null) return;

        if(!e.getPlayer().getItemInHand().getType().equals(Material.BONE)) return;
        if(!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("Spawne os lobos!")) return;
        if(!k.isKit(e.getPlayer(), Kit.Kits.BEASTMASTER)) return;

        e.getPlayer().getInventory().remove(e.getPlayer().getItemInHand());

        Wolf w1 = (Wolf) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.WOLF);
        Wolf w2 = (Wolf) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.WOLF);
        Wolf w3 = (Wolf) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.WOLF);

        w1.setAdult();
        w2.setAdult();
        w3.setAdult();
        w1.setOwner(e.getPlayer());
        w2.setOwner(e.getPlayer());
        w3.setOwner(e.getPlayer());

    }

}
