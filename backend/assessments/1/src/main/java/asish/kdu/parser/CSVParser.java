package asish.kdu.parser;

import asish.kdu.data.IplData;
import asish.kdu.entities.Player;
import asish.kdu.entities.Role;
import asish.kdu.entities.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CSVParser {
    private CSVParser() {

    }

    public static void parseCSV(Path path) {
        IplData INSTANCE = IplData.getINSTANCE();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            Set<Team> teamData = new HashSet<>();
            ArrayList<Player> playerData = new ArrayList<>();
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                Team team = new Team(
                        columns[1],
                        columns[1] + "_Home"
                );

                Player player = new Player(
                        columns[0],
                        team,
                        Integer.parseInt(columns[3]),
                        Integer.parseInt(columns[4]),
                        Double.parseDouble(columns[5]),
                        Double.parseDouble(columns[6]),
                        Integer.parseInt(columns[7]),
                        Role.valueOf(columns[2].replace(" ", "_"))
                );

                teamData.add(team);
                playerData.add(player);
            }

            INSTANCE.setTeams(teamData);
            INSTANCE.setPlayers(playerData);
        } catch (IOException I) {
            System.out.println(I);
        }

    }
}
