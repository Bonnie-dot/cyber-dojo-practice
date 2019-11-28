package parking.parkingBoy;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exception.InvalidTicketException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return tryPark(car).orElseThrow(InvalidTicketException::new);
    }

    @Override
    Optional<Ticket> tryPark(Car car) {
         return Arrays.stream(this.parkingLots)
                 .max(Comparator.comparing(parkingLot -> parkingLot.getRemainCapacity()))
                 .map(parkingLot -> parkingLot.park(car));
    }
}
