package parking;

import java.util.Optional;

public class ParkingManger {

    private ParkingBoy[] parkingBoys;

    public ParkingManger(ParkingBoy[] parkingBoys) {
        this.parkingBoys=parkingBoys;
    }

    public Ticket askOtherPark(Car car) {
        for (ParkingBoy parkingBoy : parkingBoys) {
            Optional<Ticket> ticket = parkingBoy.tryPark(car);
            if (ticket.isPresent()){
                return ticket.get();
            }
        }
        return null;
    }
}
