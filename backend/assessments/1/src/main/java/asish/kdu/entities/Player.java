package asish.kdu.entities;

public class Player {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(Double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public Integer getWickets() {
        return wickets;
    }

    public void setWickets(Integer wickets) {
        this.wickets = wickets;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Player(String name, Team team, Integer matches, Integer runs, Double average, Double strikeRate, Integer wickets, Role role) {
        this.name = name;
        this.team = team;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.wickets = wickets;
        this.role = role;
    }

    public static int compareByWickets(Player p, Player q) {
        return p.getWickets() - q.getWickets();
    }

    public static int compareByRuns(Player p, Player q) {
        return p.getRuns() - q.getRuns();
    }

    @Override
    public String toString() {
        return "Player Details - \n" +
                "Name - " + name + '\n' +
                "Role - " + role.toString() + '\n' +
                "Team - " + team.getName() + '\n' +
                "Matches - " + matches + "\n" +
                "Runs - " + runs + '\n' +
                "Average - " + average + '\n' +
                "Wickets - " + wickets  + '\n' +
                "Strike rate - " + strikeRate  + '\n';
    }

    private String name;
    private Team team;
    private Integer matches;
    private Integer runs;
    private Double average;
    private Double strikeRate;
    private Integer wickets;
    private Role role;
}
