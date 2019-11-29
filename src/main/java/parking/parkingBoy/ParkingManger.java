package parking.parkingBoy;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exception.InvalidTicketException;
import parking.exception.NoMoreSpaceException;

import java.util.Optional;

public class ParkingManger extends SmartParkingBoy {

    private ParkingBoy[] parkingBoys;
    private ParkingLot[] parkingLots;

    public ParkingManger(ParkingLot[] parkingLots, ParkingBoy... parkingBoys) {
        super(parkingLots);
        this.parkingBoys=parkingBoys;
        this.parkingLots = parkingLots;
    }

    public Ticket askParkingBoyPark(Car car) {
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

    public Car askParkingBoyPickUp(Ticket ticket) {
        for (int i = 0; i < parkingBoys.length; i++) {
            Optional<Car> carOptional = parkingBoys[i].tryPickUp(ticket);
            if (carOptional.isPresent()) {
                return carOptional.get();
            } else if (i == parkingBoys.length - 1) {
                throw new InvalidTicketException();
            }
        }
        return null;
    }

    @Override
    public Ticket park(Car car) {
        return super.park(car);
    }

    @Override
    public Car pickUp(Ticket ticket) {
        return super.pickUp(ticket);
    }
}
