package asish.kdu.q2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TicketReservation {
    private static final int CONFIRMEDLIST_LIMIT = 1;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    /**
     * As asked in the question, we define a function that removes one
     * entry from confirmedList and adds one from waitingList
     * 
     * @param confirmationNumber
     */
    public void removePassengerFromConfirmedList(String confirmationNumber) {
        for (Passenger passenger : confirmedList) {
            if (Objects.equals(confirmationNumber, passenger.getConfirmationNumber()))
                confirmedList.remove(passenger);
        }

        if (!waitingList.isEmpty())
            confirmedList.add(waitingList.getFirst());
    }

    /**
     * Similarly in the case of waiting list we onyl remove the passenger
     * 
     * @param confirmationNumber
     */

    public void removePassengerFromWaitingList(String confirmationNumber) {
        for (Passenger passenger : waitingList) {
            if (Objects.equals(confirmationNumber, passenger.getConfirmationNumber()))
                waitingList.remove(passenger);
        }
    }

    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass,
            String confirmationNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if (confirmedList.size() < CONFIRMEDLIST_LIMIT)
            confirmedList.add(passenger);
        else if (waitingList.size() < WAITINGLIST_LIMIT)
            waitingList.add(passenger);
        else
            return false;

        return true;
    }

    public boolean cancel(String confirmationNumber) {
        if (confirmedList.isEmpty() && waitingList.isEmpty())
            return true;

        Iterator<Passenger> confirmedIterator = confirmedList.iterator();
        Iterator<Passenger> waitlistIterator = waitingList.iterator();

        if (!confirmedList.isEmpty() && removePassenger(confirmedIterator, confirmationNumber)) {
            removePassengerFromConfirmedList(confirmationNumber);
        } else if (!waitingList.isEmpty() && removePassenger(waitlistIterator, confirmationNumber)) {
            removePassengerFromWaitingList(confirmationNumber);
        } else {
            return false;
        }

        return true;
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        if (iterator.hasNext()) {
            String confNumber = iterator.next().getConfirmationNumber();

            if (Objects.equals(confNumber, confirmationNumber))
                return true;
        }

        return false;
    }
}
