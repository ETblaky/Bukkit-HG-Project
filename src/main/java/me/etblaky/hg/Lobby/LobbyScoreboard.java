package me.etblaky.hg.Lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.bukkit.ChatColor;

/**
 * Created by Meu computador on 31/10/2016.
 */
public class LobbyScoreboard {

    public Lobby lobby;

    public ScoreboardManager manager;
    public Scoreboard board;

    Objective objective;
    Score kit;
    Score time;

    public LobbyScoreboard(Lobby l) {
        lobby = l;

        manager = Bukkit.getScoreboardManager();

        board = manager.getNewScoreboard();

        objective = board.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(
                lobby.getGame().getName());

    }

    public void setUpPlayer(Player p) {
        board = manager.getNewScoreboard();

        objective = board.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(lobby.getGame().name);

        kit = objective.getScore(ChatColor.GREEN + "Kit: " + lobby.getKit().getKit(p).getName());
        time = objective.getScore(ChatColor.GREEN + "Time: " + (lobby.getTimer().getTime() - 1));
        time.setScore(0);
        kit.setScore(0);

        p.setScoreboard(board);

    }


}
