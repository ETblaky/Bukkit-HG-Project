package me.etblaky.hg.Kit;

import me.etblaky.hg.Kit.Kits.Achilles;
import me.etblaky.hg.Kit.Kits.Anchor;
import me.etblaky.hg.Kit.Kits.Archer;
import me.etblaky.hg.Kit.Kits.Basic;
import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;


/**
 * Created by ETblaky on 31/10/2016.
 */
public class Kit {

    public enum Kits {
        BASIC,
        ACHILLES,
        ANCHOR,
        ARCHER
    }

    public Lobby l;

    public HashMap<Player, Kits> playersKits = new HashMap<Player, Kits>();

    public Kit(){
    }

    public Lobby getLobby(){
        return l;
    }

    public String getName(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            return new Basic((this)).getName();
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            return new Achilles(this).getName();
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            return new Anchor(this).getName();
        }
        if(playersKits.get(p).equals(Kits.ARCHER)){
            return new Archer(this).getName();
        }
        return null;
    }

    public ItemStack[] getItems(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            return new Basic(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            return new Achilles(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            return new Anchor(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.ARCHER)){
            return new Archer(this).getItems();
        }
        return null;
    }

    public void setAbilities(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            new Basic((this)).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            new Achilles(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            new Anchor(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ARCHER)){
            new Archer(this).setAbilities(p);
        }
    }

    public void removeAbilities(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            new Basic(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            new Achilles(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            new Anchor(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ARCHER)){
            new Archer(this).removeAbilities(p);
        }
    }

}
