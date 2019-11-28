package parking;

import java.util.Arrays;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
    }

    @Override
    Ticket park(Car car) {
        return tryPark(car).orElseThrow(()->new InvalidTicketException("Invalid Ticket"));
    }

    @Override
    public Optional<Ticket> tryPark(Car car) {
        return Arrays.stream(this.parkingLots)
                .filter(parkingLot -> parkingLot.getRemainCapacity() > 0)
                .map(parkingLot -> parkingLot.park(car)).findFirst();


    }

}
