package us.dosantos.buildffa.Scoreboard;

public class ScoreboardInput {
    private final String prefix;

    private final String name;

    private final String suffix;

    private final int position;

    public ScoreboardInput(String prefix, String name, String suffix, int position) {
        this.prefix = prefix;
        this.name = name;
        this.suffix = suffix;
        this.position = position;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getName() {
        return this.name;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public int getPosition() {
        return this.position;
    }
}
