package parking.parkingBoy;

import parking.Car;
import parking.Ticket;
import parking.exception.NoMoreSpaceException;

import java.util.Optional;

public class ParkingManger {

    private ParkingBoy[] parkingBoys;

    public ParkingManger(ParkingBoy[] parkingBoys) {
        this.parkingBoys=parkingBoys;
    }

    public Ticket askOtherPark(Car car) {
        for (int i = 0; i < parkingBoys.length; i++) {
            Optional<Ticket> ticket = parkingBoys[i].tryPark(car);
            if (ticket.isPresent()){
                return ticket.get();
            } else if (i == parkingBoys.length - 1) {
                throw new NoMoreSpaceException();
            }
        }
        return null;
    }
}
