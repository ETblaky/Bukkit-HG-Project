package me.etblaky.hg.Kit;

import me.etblaky.hg.Lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
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
        KANGAROO,
        ENDERMAGE,
        STOMPER

    }

    public Lobby l;

    public HashMap<Player, Kits> playersKits = new HashMap<Player, Kits>();
    public HashMap<Player, Kits> vipsSecondKits = new HashMap<Player, Kits>();

    public Kit(){
    }

    public Kit(Lobby l){
        this.l = l;
    }

    public Lobby getLobby(){
        return l;
    }

    private Class<?> getKitClass(Kits k) {

        String className = k.toString().toLowerCase().substring(0, 1).toUpperCase() + k.toString().toLowerCase().substring(1);

        if(k == Kits.BEASTMASTER){
            className = "BeastMaster"; //Two capital letters
        }

        try {
            return Class.forName("me.etblaky.hg.Kit.Kits." + className);
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName(Player p, boolean isVip){

        if(isVip){
            try{
                for(Kits k : Kits.values()){
                    if(vipsSecondKits.get(p).equals(k)){

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("getName");

                        return (String) getName.invoke(kitClass);
                    }
                }
            }

            catch (Exception e){
                e.printStackTrace();
            }
        }

        else {
            try{
                for(Kits k : Kits.values()){
                    if(playersKits.get(p).equals(k)){

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("getName");

                        return (String) getName.invoke(kitClass);
                    }
                }
            }

            catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;

    }

    public ItemStack[] getItems(Player p, boolean isVip){

        if(isVip){
            try{
                for(Kits k : Kits.values()){
                    if(vipsSecondKits.get(p).equals(k)){

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("getItems");

                        return (ItemStack[]) getName.invoke(kitClass);
                    }
                }
            }

            catch (Exception e){
                e.printStackTrace();
            }
        }

        else {

            try {
                for (Kits k : Kits.values()) {
                    if (playersKits.get(p).equals(k)) {

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("getItems");

                        return (ItemStack[]) getName.invoke(kitClass);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public void setAbilities(Player p, boolean isVip){
        if(isVip) {

            try {
                for (Kits k : Kits.values()) {
                    if (vipsSecondKits.get(p).equals(k)) {

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("setAbilities", Player.class);

                        getName.invoke(kitClass, p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {

            try {
                for (Kits k : Kits.values()) {
                    if (playersKits.get(p).equals(k)) {

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("setAbilities", Player.class);

                        getName.invoke(kitClass, p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void removeAbilities(Player p, boolean isVip){
        if(isVip) {

            try {
                for (Kits k : Kits.values()) {
                    if (vipsSecondKits.get(p).equals(k)) {

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("setAbilities", Player.class);

                        getName.invoke(kitClass, p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {

            try {
                for (Kits k : Kits.values()) {
                    if (playersKits.get(p).equals(k)) {

                        Object kitClass = getKitClass(k).newInstance();

                        Method getName = kitClass.getClass().getDeclaredMethod("setAbilities", Player.class);

                        getName.invoke(kitClass, p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public boolean isKit(Player p, Kits k){

        if(p == null || k == null) return false;

        if(playersKits.get(p).equals(k)) { return true; }
        if(vipsSecondKits.get(p).equals(k)) { return true; }

        return false;
    }

}
