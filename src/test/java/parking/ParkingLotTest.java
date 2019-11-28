package parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_return_a_ticket_when_park_the_car_into_the_parking_lot_has_space(){

        ParkingLot parkingLot = new ParkingLot(1);

        Ticket ticket = parkingLot.park(new Car());

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_two_different_tickets_when_park_two_cars_into_the_parking_lot_has_space(){

        ParkingLot parkingLot = new ParkingLot(3);

        Ticket ticketOne = parkingLot.park(new Car());
        Ticket ticketTwo = parkingLot.park(new Car());

        assertThat(ticketOne).isNotEqualTo(ticketTwo);
    }

    @Test
    void should_throw_no_more_space_exception_when_park_the_car_into_the_parking_lot_has_no_space(){

        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        assertThatExceptionOfType(NoMoreSpaceException.class)
                .isThrownBy(() -> parkingLot.park(new Car()))
                .withMessage("No more space");
    }

    @Test
    void should_return_your_car_when_pick_up_car_from_a_parking_lot_parked_your_car(){

        ParkingLot parkingLot = new ParkingLot(1);
        Car yourCar = new Car();
        Ticket ticket = parkingLot.park(yourCar);

        Car pickUpCar = parkingLot.pickUp(ticket);

        assertThat(pickUpCar).isEqualTo(yourCar);
    }

    @Test
    void should_return_your_two_cars_when_pick_up_car_from_a_parking_lot_parked_your_car_with_two_different_tickets(){

        ParkingLot parkingLot = new ParkingLot(3);
        Car yourCarOne = new Car();
        Car yourCarTwo = new Car();
        Ticket ticketOne = parkingLot.park(yourCarOne);
        Ticket ticketTwo = parkingLot.park(yourCarTwo);

        Car pickUpCarOne = parkingLot.pickUp(ticketOne);
        Car pickUpCarTwo = parkingLot.pickUp(ticketTwo);

        assertThat(pickUpCarOne).isNotEqualTo(pickUpCarTwo);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_pick_up_car_from_a_parking_lot_parked_your_car_with_using_a_valid_ticket_twice(){

        ParkingLot parkingLot = new ParkingLot(1);
        Car yourCar = new Car();
        Ticket ticket = parkingLot.park(yourCar);
        parkingLot.pickUp(ticket);

        assertThatExceptionOfType(InvalidTicketException.class)
                .isThrownBy(()->parkingLot.pickUp(ticket))
                .withMessage("Invalid Ticket");

    }

    @Test
    void should_throw_invalid_ticket_exception_when_pick_up_car_from_a_parking_lot_parked_your_car_with_using_a_invalid_ticket(){

        ParkingLot parkingLot = new ParkingLot(1);
        Car yourCar = new Car();
        parkingLot.park(yourCar);
        Ticket ticket = new Ticket();

        assertThatExceptionOfType(InvalidTicketException.class)
                .isThrownBy(()->parkingLot.pickUp(ticket))
                .withMessage("Invalid Ticket");
    }
}
