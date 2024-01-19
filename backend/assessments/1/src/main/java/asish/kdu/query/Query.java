package asish.kdu.query;

import asish.kdu.data.IplData;
import asish.kdu.entities.Player;
import asish.kdu.entities.Role;
import asish.kdu.logging.CustomLogger;

import java.util.*;

public class Query {
    private final IplData INSTANCE;

    public Query() {
        this.INSTANCE = IplData.getINSTANCE();
    }

//    i. Given the team's name, return all the bowlers who have taken at least 40 wickets.
//ii. Given a team display the details of the highest wicket-taker and highest run-scorer
//iii. Fetch the top 3 run-scorer and top 3 wicket-takers of the season.

    public void ballersWithMoreThan40Wickets(String name) {
        CustomLogger.LoggerType loggerType = CustomLogger.LoggerType.INFO;
        List<Player> ballers = INSTANCE.getPlayers().stream().filter(p -> Objects.equals(p.getTeam().getName(), name)).filter(p -> p.getWickets() >= 40).toList();

        CustomLogger.printLogger("Ballers of team - " + name + "with atleast 40 wickets -", loggerType);
        ballers.forEach(b -> CustomLogger.printLogger(b.toString(), loggerType));
    }

    public void highestWicketAndRun(String name) {
        CustomLogger.LoggerType loggerTypeInfo = CustomLogger.LoggerType.INFO;
        CustomLogger.LoggerType loggerTypeError = CustomLogger.LoggerType.ERROR;
        Optional<Player> highestWicket = INSTANCE.getPlayers().stream().filter(p -> Objects.equals(p.getTeam().getName(), name)).max(Player::compareByWickets);

        if(highestWicket.isEmpty()) {
            CustomLogger.printLogger("Some error occured in fetching highest wicket taker", loggerTypeError);
        }

        CustomLogger.printLogger("Highest wicket taker of team - " + name + "is", loggerTypeInfo);
        CustomLogger.printLogger(highestWicket.toString(), loggerTypeError);

        Optional<Player> highestRun = INSTANCE.getPlayers().stream().filter(p -> Objects.equals(p.getTeam().getName(), name)).max(Player::compareByRuns);

        if(highestWicket.isEmpty()) {
            CustomLogger.printLogger("Some error occured in fetching highest run scorer", loggerTypeError);
        }

        CustomLogger.printLogger("Highest run scorer of team - " + name + "is", loggerTypeInfo);
        CustomLogger.printLogger(highestRun.toString(), loggerTypeError);
    }

    public void top3RunAndWicket() {
        CustomLogger.LoggerType loggerType = CustomLogger.LoggerType.INFO;
        List<Player> top3Run = INSTANCE.getPlayers().stream().sorted(Player::compareByRuns).limit(3).toList();
        List<Player> top3Wicket = INSTANCE.getPlayers().stream().sorted(Player::compareByWickets).limit(3).toList();

        CustomLogger.printLogger("Top 3 run scorers of the season - ", loggerType);
        top3Run.forEach(player -> CustomLogger.printLogger(player.toString(), loggerType));

        CustomLogger.printLogger("Top 3 run wicket takers of the season - ", loggerType);
        top3Wicket.forEach(player -> CustomLogger.printLogger(player.toString(), loggerType));
    }
}


//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
//public class MatchFixtureGenerator {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Input the team names
//        System.out.print("Enter the number of teams: ");
//        int numberOfTeams = scanner.nextInt();
//        scanner.nextLine(); // consume the newline character
//
//        List<String> teamNames = new ArrayList<>();
//        for (int i = 0; i < numberOfTeams; i++) {
//            System.out.print("Enter the name of team " + (i + 1) + ": ");
//            teamNames.add(scanner.nextLine());
//        }
//
//        // Generate match fixtures
//        List<String[]> fixtures = generateFixtures(teamNames);
//
//        // Write match fixtures to CSV file
//        writeFixturesToCSV(fixtures);
//
//        System.out.println("Match fixtures have been generated and written to match_fixtures.csv.");
//    }
//
//    private static List<String[]> generateFixtures(List<String> teams) {
//        List<String[]> fixtures = new ArrayList<>();
//        int numberOfTeams = teams.size();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd hh:mm a");
//
//        // Generate fixtures
//        for (int i = 0; i < numberOfTeams - 1; i++) {
//            for (int j = i + 1; j < numberOfTeams; j++) {
//                String[] fixture = new String[5];
//                fixture[0] = dateFormat.format(new Date()); // Dummy date for illustration
//                fixture[1] = String.valueOf(fixtures.size() + 1);
//                fixture[2] = teams.get(i);
//                fixture[3] = teams.get(j);
//                fixture[4] = teams.get(i) + "_home";
//
//                fixtures.add(fixture);
//
//                // Second match (reverse order)
//                String[] reverseFixture = new String[5];
//                reverseFixture[0] = dateFormat.format(new Date()); // Dummy date for illustration
//                reverseFixture[1] = String.valueOf(fixtures.size() + 1);
//                reverseFixture[2] = teams.get(j);
//                reverseFixture[3] = teams.get(i);
//                reverseFixture[4] = teams.get(j) + "_home";
//
//                fixtures.add(reverseFixture);
//            }
//        }
//
//        return fixtures;
//    }
//
//    private static void writeFixturesToCSV(List<String[]> fixtures) {
//        try (FileWriter writer = new FileWriter("match_fixtures.csv")) {
//            // Write CSV header
//            writer.append("Date,Match number,Team home,Team away,Ground\n");
//
//            // Write match fixtures
//            for (String[] fixture : fixtures) {
//                writer.append(String.join(",", fixture) + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}