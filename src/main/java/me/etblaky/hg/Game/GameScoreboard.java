package me.etblaky.hg.Game;

import me.etblaky.vip.VipSys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import static me.etblaky.hg.Lobby.LobbyListener.lobby;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class GameScoreboard {

    public Game game;

    public ScoreboardManager manager;
    public Scoreboard board;

    Objective objective;
    Score kit;
    Score vipKit;
    Score time;
    Score players;

    public GameScoreboard(Game g) {
        game = g;

        manager = Bukkit.getScoreboardManager();

        board = manager.getNewScoreboard();

        objective = board.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(game.getName());

    }

    public void setUpPlayer(Player p){
        board = manager.getNewScoreboard();

        objective = board.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("HG-Project");

        kit = objective.getScore(ChatColor.GREEN + "Kit: " + (game.getKit().getName(p, false) != null ? game.getKit().getName(p, false) : ""));
        if(VipSys.isVip(p)){ vipKit = objective.getScore(ChatColor.GREEN + "Segundo Kit: " + (game.getKit().getName(p, true) != null ? game.getKit().getName(p, true) : "")); }
        time = objective.getScore(ChatColor.GREEN + "Time: " + game.getTimer().getTimeStr());
        players = objective.getScore(ChatColor.GREEN + "Players: " + lobby.players.size());

        kit.setScore(0);
        if(VipSys.isVip(p)){ vipKit.setScore(0); }
        time.setScore(0);
        players.setScore(0);

        p.setScoreboard(board);
    }

}
