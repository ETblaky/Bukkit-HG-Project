package me.etblaky.hg.Kit;

import me.etblaky.hg.Kit.Kits.*;
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
        ARCHER,
        ASSASSIN,
        BARBARIAN,
        BEASTMASTER,
        BERSERKER,
        BLINK,
        BOXER,
        CAMEL,
        KANGAROO
    }

    public Lobby l;

    public HashMap<Player, Kits> playersKits = new HashMap<Player, Kits>();

    public Kit(){
    }

    public Kit(Lobby l){
        this.l = l;
    }

    public Lobby getLobby(){
        return l;
    }

    public String getName(Player p){
        if(playersKits.get(p).equals(Kits.BASIC)){
            return new Basic(this).getName();
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
        if(playersKits.get(p).equals(Kits.ASSASSIN)){
            return new Assassin(this).getName();
        }
        if(playersKits.get(p).equals(Kits.BARBARIAN)){
            return new Barbarian(this).getName();
        }
        if(playersKits.get(p).equals(Kits.BEASTMASTER)){
            return new BeastMaster(this).getName();
        }
        if(playersKits.get(p).equals(Kits.BERSERKER)){
            return new Berserker(this).getName();
        }
        if(playersKits.get(p).equals(Kits.BLINK)){
            return new Blink(this).getName();
        }
        if(playersKits.get(p).equals(Kits.BOXER)){
            return new Boxer(this).getName();
        }
        if(playersKits.get(p).equals(Kits.CAMEL)){
            return new Camel(this).getName();
        }
        if(playersKits.get(p).equals(Kits.KANGAROO)){
            return new Kangaroo(this).getName();
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
        if(playersKits.get(p).equals(Kits.ASSASSIN)){
            return new Assassin(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.BARBARIAN)){
            return new Barbarian(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.BEASTMASTER)){
            return new BeastMaster(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.BERSERKER)){
            return new Berserker(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.BLINK)){
            return new Blink(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.BOXER)){
            return new Boxer(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.CAMEL)){
            return new Camel(this).getItems();
        }
        if(playersKits.get(p).equals(Kits.KANGAROO)){
            return new Kangaroo(this).getItems();
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
        if(playersKits.get(p).equals(Kits.ASSASSIN)){
            new Assassin(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BARBARIAN)){
            new Barbarian(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BEASTMASTER)){
            new BeastMaster(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BERSERKER)){
            new Berserker(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BLINK)){
            new Blink(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BOXER)){
            new Boxer(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.CAMEL)){
            new Camel(this).setAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.KANGAROO)){
            new Kangaroo(this).setAbilities(p);
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
        if(playersKits.get(p).equals(Kits.ASSASSIN)){
            new Assassin(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BARBARIAN)){
            new Barbarian(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BEASTMASTER)){
            new BeastMaster(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BERSERKER)){
            new Berserker(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BLINK)){
            new Blink(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.BOXER)){
            new Boxer(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.CAMEL)){
            new Camel(this).removeAbilities(p);
        }
        if(playersKits.get(p).equals(Kits.KANGAROO)){
            new Kangaroo(this).removeAbilities(p);
        }
    }

}
