package asish.kdu.utils;

import asish.kdu.data.IplData;
import asish.kdu.logging.CustomLogger;
import asish.kdu.parser.CSVParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class MatchGenerator {

    private static IplData INSTANCE = IplData.getINSTANCE();

    public static void main(String[] args) {
        CustomLogger.LoggerType loggerTypeInfo = CustomLogger.LoggerType.INFO;
        CSVParser.parseCSV(Path.of("src/main/resources/IPL_2021-data.csv"));
        Scanner scanner = new Scanner(System.in);

        // Input the team names
        System.out.print("Enter the number of teams: ");
        int numberOfTeams = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        List<String> teamNames = new ArrayList<>();
        for (int i = 0; i < numberOfTeams; i++) {
            System.out.print("Enter the name of team " + (i + 1) + ": ");
            teamNames.add(scanner.nextLine());
        }

        // Generate match fixtures
        List<String[]> fixtures = generateFixtures(teamNames);

        // Write match fixtures to CSV file
        writeFixturesToCSV(fixtures);

        CustomLogger.printLogger("Match fixtures have been generated and written to match_fixtures.csv.", loggerTypeInfo);
    }

    private static List<String[]> generateFixtures(List<String> teams) {
        List<String[]> fixtures = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd hh:mm a");

        // Generate fixtures
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < INSTANCE.getTeams().size(); j++) {
                c.add(Calendar.DAY_OF_MONTH, 2);
                String[] fixture = new String[5];
                fixture[0] = dateFormat.format(c.getTime());
                fixture[1] = String.valueOf(fixtures.size() + 1);
                fixture[2] = teams.get(i);
                fixture[3] = teams.get(j);
                fixture[4] = teams.get(i) + "_home";

                fixtures.add(fixture);

                // Second match (reverse order)
                String[] reverseFixture = new String[5];
                reverseFixture[0] = dateFormat.format(c.getTime());
                reverseFixture[1] = String.valueOf(fixtures.size() + 1);
                reverseFixture[2] = teams.get(j);
                reverseFixture[3] = teams.get(i);
                reverseFixture[4] = teams.get(j) + "_home";

                fixtures.add(reverseFixture);
            }
        }

        return fixtures;
    }

    private static void writeFixturesToCSV(List<String[]> fixtures) {
        try (FileWriter writer = new FileWriter("match_fixtures.csv")) {
            writer.append("Date,Match number,Team home,Team away,Ground\n");

            for (String[] fixture : fixtures) {
                writer.append(String.join(",", fixture) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}