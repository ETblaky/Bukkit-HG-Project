package me.etblaky.hg.Kit;

import me.etblaky.hg.Kit.Kits.Achilles;
import me.etblaky.hg.Kit.Kits.Anchor;
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
        ANCHOR
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
            return Basic.getName();
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            return Achilles.getName();
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            return Anchor.getName();
        }
        return null;
    }

    public ItemStack[] getItems(Player p){
        if(playersKits
                .get(p)
                .equals(Kits.BASIC)){
            return Basic.getItems();
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            return Achilles.getItems();
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            return Anchor.getItems();
        }
        return null;
    }

    public void setAbilities(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            Basic.setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            Achilles.setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            Anchor.setAbilities(p);
        }
    }

    public void removeAbilities(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            Basic.removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ACHILLES)){
            Achilles.setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.ANCHOR)){
            Anchor.setAbilities(p);
        }
    }

}
