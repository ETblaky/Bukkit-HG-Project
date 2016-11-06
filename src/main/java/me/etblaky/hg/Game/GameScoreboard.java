package me.etblaky.hg.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * Created by ETblaky on 31/10/2016.
 */
public class GameScoreboard {

    public Game game;

    public ScoreboardManager manager;
    public Scoreboard board;

    Objective objective;
    Score kit;
    Score time;

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

        kit = objective.getScore(ChatColor.GREEN + "Kit: " + game.getKit().getName(p));
        time = objective.getScore(ChatColor.GREEN + "Time: " + (game.getTimer().getTime() - 1));
        time.setScore(0);
        kit.setScore(0);

        p.setScoreboard(board);
    }

}
