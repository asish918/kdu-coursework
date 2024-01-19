package asish.kdu.data;

import asish.kdu.entities.Player;
import asish.kdu.entities.Team;

import java.util.ArrayList;
import java.util.Set;

public class IplData {

    private IplData() {

    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public static IplData getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new IplData();
        }
        return INSTANCE;

    }

    private Set<Team> teams;
    private ArrayList<Player> players;
    private static IplData INSTANCE;
}
