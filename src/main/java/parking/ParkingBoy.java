package parking;

import java.util.Arrays;

public abstract class ParkingBoy {

    ParkingLot[] parkingLots;

    public ParkingBoy(ParkingLot[] parkingLots) {
        this.parkingLots = parkingLots;
    }

    abstract Ticket park(Car car);

    public Car pickUp(Ticket ticket) {
        return Arrays.stream(this.parkingLots).filter(parkingLot -> parkingLot.isValidTicket(ticket))
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException("Invalid Ticket"))
                .pickUp(ticket);
    }
}
