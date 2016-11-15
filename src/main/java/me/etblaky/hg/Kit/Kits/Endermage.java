package me.etblaky.hg.Kit.Kits;

import me.etblaky.hg.Game.Game;
import me.etblaky.hg.Kit.Kit;
import me.etblaky.hg.Kit.KitBase;
import me.etblaky.hg.Lobby.Lobby;
import me.etblaky.hg.Main;
import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_10_R1.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ETblaky on 14/11/2016.
 */
public class Endermage extends KitBase {

    public ItemStack[] items = new ItemStack[]{is(Material.ENDER_PORTAL_FRAME, 1)};
    public String name = "Endermage";

    public Kit k;

    public Endermage() {
    }

    public Endermage(Kit k) {
        this.k = k;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void setAbilities(Player p) {
    }

    public void removeAbilities(Player p) {
    }

    @EventHandler
    public void onPlayerUse(final BlockPlaceEvent e) {
        for (Game g : Game.getGames()) {
            for (Player p : g.getLobby().getPlayers()) {
                if (p.getUniqueId().equals((e.getPlayer()).getUniqueId())) {
                    k = g.getLobby().getKit();
                }
            }
        }

        if (k == null) return;
        if (k.playersKits == null) return;
        if (k.playersKits.get(e.getPlayer()) == null) return;
        if (!k.playersKits.get(e.getPlayer()).equals(Kit.Kits.ENDERMAGE)) return;
        if (!k.getLobby().state.equals(Lobby.MatchState.GAME)) return;
        if (e.getBlockPlaced().getType() != Material.ENDER_PORTAL_FRAME) return;

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            public void run() {
                CraftEntity ent = spawn(e.getBlockPlaced().getLocation().add(0, 1, 0), e.getPlayer());

                for (Entity en : ent.getNearbyEntities(3, 200, 3)) {
                    if(!(en instanceof Player)) return;
                    if(en.getUniqueId().equals(e.getPlayer().getUniqueId())) return;

                    en.teleport(e.getBlockPlaced().getLocation().add(0, 1, 0));

                }

                e.getPlayer().getInventory().addItem(new ItemStack(e.getBlockPlaced().getType()));
                e.getBlockPlaced().setType(Material.AIR);

            }
        }, 30);

    }

    public CraftEntity spawn(Location loc, Player p) {
        WorldServer s = ((CraftWorld) loc.getWorld()).getHandle();
        EntityArmorStand stand = new EntityArmorStand(s);

        stand.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
        stand.setNoGravity(false);
        stand.setInvisible(true);
        stand.setSmall(true);
        stand.setSilent(true);
        stand.setInvulnerable(true);

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

        return stand.getBukkitEntity();

    }

}
