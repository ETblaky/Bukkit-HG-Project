package me.etblaky.hg.Lobby;

import me.etblaky.vip.VipSys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.bukkit.ChatColor;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class LobbyScoreboard {

    public Lobby lobby;

    public ScoreboardManager manager;
    public Scoreboard board;

    Objective objective;
    Score kit;
    Score vipKit;
    Score time;
    Score players;

    public LobbyScoreboard(Lobby l) {
        lobby = l;

        manager = Bukkit.getScoreboardManager();

        board = manager.getNewScoreboard();

        objective = board.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(lobby.getGame().getName());

    }

    public void setUpPlayer(Player p) {
        board = manager.getNewScoreboard();

        objective = board.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(lobby.getGame().name);

        if(VipSys.isVip(p)){
            kit = objective.getScore(ChatColor.GREEN + "Kit 1: " + (lobby.getKit().getName(p, false) != null ? lobby.getKit().getName(p, false) : ""));
            vipKit = objective.getScore(ChatColor.GREEN + "Kit 2: " + (lobby.getKit().getName(p, true) != null ? lobby.getKit().getName(p, true) : ""));
        }
        else {
            kit = objective.getScore(ChatColor.GREEN + "Kit: " + (lobby.getKit().getName(p, false) != null ? lobby.getKit().getName(p, false) : ""));
        }
        time = objective.getScore(ChatColor.GREEN + "Time: " + lobby.getTimer().getTimeStr());
        players = objective.getScore(ChatColor.GREEN + "Players: " + lobby.players.size());

        kit.setScore(0);
        if(VipSys.isVip(p)){ vipKit.setScore(0); }
        time.setScore(0);
        players.setScore(0);

        p.setScoreboard(board);

    }


}
