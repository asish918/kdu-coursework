package asish.kdu;

import asish.kdu.logging.CustomLogger;
import asish.kdu.parser.CSVParser;
import asish.kdu.query.Query;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomLogger.LoggerType loggerType = CustomLogger.LoggerType.INFO;
        Query queryFetcher = new Query();

        Scanner sc = new Scanner(System.in);

        CSVParser.parseCSV(Path.of("src/main/resources/IPL_2021-data.csv"));

        int choice = 0;

        while(choice < 4) {
            CustomLogger.printLogger("IPL Extravaganza\n", loggerType);
            CustomLogger.printLogger("1. To find ballers with more than 40 wickets in a team.\n", loggerType);
            CustomLogger.printLogger("2. Display highest wicket taker and run scorer of a team.\n", loggerType);
            CustomLogger.printLogger("3. Display highest wicket taker and run scorer of the season.\n", loggerType);
            CustomLogger.printLogger("4. Exit\n", loggerType);
            CustomLogger.printLogger("Enter your choice - \n", loggerType);
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    CustomLogger.printLogger("Enter team name - \n", loggerType);
                    String name = sc.next();
                    queryFetcher.ballersWithMoreThan40Wickets(name);
                }
                case 2 -> {
                    CustomLogger.printLogger("Enter team name - \n", loggerType);
                    String name = sc.nextLine();
                    queryFetcher.highestWicketAndRun(name);
                }
                case 3 -> queryFetcher.top3RunAndWicket();
                default -> CustomLogger.printLogger("Invalid Choice/Quitting\n", loggerType);
            }
        }
    }
}