package parking;

import java.util.Arrays;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        Optional<ParkingLot> parkingLotOptional = Arrays.stream(this.parkingLots)
                .filter(parkingLot -> parkingLot.getRemainCapacity() > 0)
                .findFirst();
        if (parkingLotOptional.isPresent()) {
            return parkingLotOptional.map(parkingLot -> parkingLot.park(car)).get();
        } else {
            throw new NoMoreSpaceException("No more space");
        }

    }

}
