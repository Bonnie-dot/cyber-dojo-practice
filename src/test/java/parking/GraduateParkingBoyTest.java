package parking;

import org.junit.jupiter.api.Test;
import parking.exception.InvalidTicketException;
import parking.exception.NoMoreSpaceException;
import parking.parkingBoy.GraduateParkingBoy;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class GraduateParkingBoyTest {

    @Test
    void should_return_a_ticket_when_park_car_having_a_graduate_parking_boy_with_having_a_space_parking_lot() {

        ParkingLot[] parkingLots={new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Ticket result = graduateParkingBoy.park(new Car());

        assertThat(result).isNotNull();

    }

    @Test
    void should_return_two_different_tickets_when_park_cars_having_a_graduate_boy_with_having_a_space_parking_lot_and_having_two_space_parking_lot() throws NoMoreSpaceException {

        ParkingLot[] parkingLots={new ParkingLot(1),new ParkingLot(2)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Ticket ticketOne = graduateParkingBoy.park(new Car());
        Ticket ticketTwo = graduateParkingBoy.park(new Car());

        assertThat(graduateParkingBoy.pickUp(ticketOne)).isNotNull();
        assertThat(graduateParkingBoy.pickUp(ticketTwo)).isNotNull();
    }

    @Test
    void should_throw_no_more_space_exception_when_park_car_having_a_graduate_parking_boy_with_having_no_space_parking_lot() {

        ParkingLot[] parkingLots={new ParkingLot(0)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        assertThatThrownBy(()->graduateParkingBoy.park(new Car()))
                .isInstanceOf(NoMoreSpaceException.class);

    }

    @Test
    void should_return_a_car_when_pick_up_car_having_a_graduate_parking_boy_who_having_the_parking_lot_with_parking_my_car() {

        ParkingLot[] parkingLots={new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket parkTicket = graduateParkingBoy.park(myCar);

        Car result = graduateParkingBoy.pickUp(parkTicket);

        assertThat(result).isEqualTo(myCar);
    }

    @Test
    void should_return_two_car_when_get_car_in_different_parking_lots() throws NoMoreSpaceException, InvalidTicketException {

        ParkingLot[] parkingLots={new ParkingLot(1),new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car carOne=new Car();
        Car carTwo=new Car();
        Ticket ticketOne = graduateParkingBoy.park(carOne);
        Ticket ticketTwo = graduateParkingBoy.park(carTwo);

        Car getCarOne = graduateParkingBoy.pickUp(ticketOne);
        Car getCarTwo = graduateParkingBoy.pickUp(ticketTwo);
        assertThat(getCarOne).isEqualTo(carOne);
        assertThat(getCarTwo).isEqualTo(carTwo);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_pick_up_car_having_a_graduate_parking_boy_who_use_the_same_ticket_twice() {

        ParkingLot[] parkingLots={new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket parkTicket = graduateParkingBoy.park(myCar);
        graduateParkingBoy.pickUp(parkTicket);

        assertThatThrownBy(()->graduateParkingBoy.pickUp(parkTicket))
                .isInstanceOf(InvalidTicketException.class);
    }
}
