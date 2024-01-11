package asish.kdu.q2;

import asish.kdu.logging.CustomLogger;
import asish.kdu.logging.CustomLogger.LoggerType;

public class Main {
        public static void main(String[] args) {
                TicketReservation ticket = new TicketReservation();

                CustomLogger.printLogger("Boolean outputs for function calls - ", LoggerType.INFO);
                CustomLogger.printLogger(
                                Boolean.toString(ticket.bookFlight("Tony", "Stark", 34, "male", "premium", "123")),
                                LoggerType.INFO);
                CustomLogger.printLogger(
                                Boolean.toString(ticket.bookFlight("Steve", "Rogers", 100, "male", "economy", "124")),
                                LoggerType.INFO);
                CustomLogger.printLogger(
                                Boolean.toString(
                                                ticket.bookFlight("Thor", "Odinson", 1500, "male", "executive", "125")),
                                LoggerType.INFO);
                CustomLogger.printLogger(
                                Boolean.toString(ticket.bookFlight("Peter", "Parker", 24, "male", "executive", "126")),
                                LoggerType.INFO);
                CustomLogger.printLogger(
                                Boolean.toString(ticket.bookFlight("Natasha", "Romanoff", 30, "female", "premium",
                                                "127")),
                                LoggerType.INFO);
                CustomLogger.printLogger(
                                Boolean.toString(ticket.bookFlight("Bruce", "Banner", 40, "male", "economy", "128")),
                                LoggerType.INFO);

                CustomLogger.printLogger(
                                Boolean.toString(ticket.cancel("123")),
                                LoggerType.INFO);

        }
}