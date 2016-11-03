package me.etblaky.hg.Kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class Kit {

    public enum Kits {
        BASIC;
    }

    public Kits k;

    public HashMap<Player, Kits> playersKits = new HashMap<Player, Kits>();

    public Kit(){

    }

    public Kit getKit(Kits k){
        this.k = k;
        return this;
    }

    public Kit getKit(Player p){
        k = playersKits.get(p);
        if(k == null){
            k = Kits.BASIC;
        }
        return this;
    }

    public void setKit(Player p, Kits k){
        playersKits.put(p, k);
    }

    public String getName(){
        if(k.equals(Kits.BASIC)){
            return Basic.getName();
        }
        return null;
    }

    public ItemStack[] getItems(){
        if(k.equals(Kits.BASIC)){
            return Basic.getItems();
        }
        return null;
    }

    public void setAbilities(Player p){
        if(k.equals(Kits.BASIC)){
            Basic.setAbilities(p);
        }
    }

    public void removeAbilities(Player p){
        if(k.equals(Kits.BASIC)){
            Basic.removeAbilities(p);
        }
    }

}
