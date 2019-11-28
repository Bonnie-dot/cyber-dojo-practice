package parking.parkingBoy;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exception.InvalidTicketException;
import parking.exception.NoMoreSpaceException;

import java.util.Arrays;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return tryPark(car).orElseThrow(NoMoreSpaceException::new);
    }

    @Override
    public Optional<Ticket> tryPark(Car car) {
        return Arrays.stream(this.parkingLots)
                .filter(parkingLot -> parkingLot.getRemainCapacity() > 0)
                .map(parkingLot -> parkingLot.park(car)).findFirst();


    }

}
