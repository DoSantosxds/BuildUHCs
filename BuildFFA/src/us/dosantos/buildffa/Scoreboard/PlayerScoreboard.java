package us.dosantos.buildffa.Scoreboard;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import us.dosantos.buildffa.Util.Utils;

public final class PlayerScoreboard {
    private Player player;

    private Scoreboard scoreboard;

    private Objective objective;

    private String title;

    private List inputs;

    private List lastInputs;

    public PlayerScoreboard(Player player, Scoreboard scoreboard, String tittle) {
        this.player = player;
        this.scoreboard = scoreboard;
        Preconditions.checkState((tittle.length() < 32), "Max characters for Title is 32!");
        this.title = Utils.color(tittle);
        (this.objective = getObjective(this.title)).setDisplaySlot(DisplaySlot.SIDEBAR);
        this.inputs = new ArrayList();
        this.lastInputs = new ArrayList();
    }

    public void add(String line1, String line2) {
        if (this.inputs.size() < 14) {
            line1 = Utils.color(line1);
            line2 = Utils.color(line2);
            ScoreboardInput sb = null;
            if (line1.length() <= 16) {
                sb = new ScoreboardInput("", line1, line2, this.inputs.size());
            } else {
                String str1 = line1.substring(line1.length() - 16, line1.length());
                String str2 = line1.substring(0, line1.length() - str1.length());
                sb = new ScoreboardInput(str2, str1, line2, this.inputs.size());
            }
            this.inputs.add(sb);
        }
    }

    public void addLine(String s1, String s2, String s3) {
        s1 = Utils.color(s1);
        s2 = Utils.color(s2);
        s3 = Utils.color(s3);
        this.inputs.add(new ScoreboardInput(s1, s2, s3, this.inputs.size()));
    }

    public void update(Player player) {
        player.setScoreboard(this.scoreboard);
        ArrayList<String> al = new ArrayList<>();
        int sentCount;
        for (sentCount = 0; sentCount < this.inputs.size(); sentCount++) {
            ScoreboardInput scoreBoard = (ScoreboardInput) this.inputs.get(sentCount);
            Team team = getTeam(scoreBoard.getName());
            if (!team.hasEntry(scoreBoard.getName()))
                team.addEntry(scoreBoard.getName());
            team.setPrefix(scoreBoard.getPrefix());
            team.setSuffix(scoreBoard.getSuffix());
            al.add(scoreBoard.getName());
            this.objective.getScore(scoreBoard.getName()).setScore(this.inputs.size() - scoreBoard.getPosition());
        }
        this.lastInputs.removeAll(al);
        for (sentCount = 0; sentCount < this.lastInputs.size(); sentCount++) {
            String str = (String) this.lastInputs.get(sentCount);
            this.scoreboard.resetScores(str);
        }
        this.lastInputs = al;
    }

    public synchronized Team getTeam(String teams) {
        Team team = this.scoreboard.getTeam(teams);
        if (team == null) {
            team = this.scoreboard.registerNewTeam(teams);
            team.addEntry(teams);
        }
        return team;
    }

    public Objective getObjective(String title) {
        Objective obj = this.scoreboard.getObjective("iserycombo");
        if (obj == null)
            obj = this.scoreboard.registerNewObjective("iserycombo", "dummy");
        obj.setDisplayName(title);
        return obj;
    }

    public void clear() {

        this.inputs.clear();
    }

    public Scoreboard getScoreboard() {

        return this.scoreboard;
    }
}