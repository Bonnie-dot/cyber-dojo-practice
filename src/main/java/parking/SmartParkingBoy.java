package parking;

import java.util.Arrays;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot[] copyParkingLots = Arrays.copyOf(this.parkingLots, this.parkingLots.length);
        Arrays.sort(copyParkingLots);
        return copyParkingLots[0].park(car);
    }
}
