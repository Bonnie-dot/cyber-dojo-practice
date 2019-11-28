package parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class SmartParkingBoyTest {

    @Test
    void should_return_a_ticket_when_park_car_having_smart_parking_boy_having_a_space_park_lot() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Ticket result = smartParkingBoy.park(new Car());

        assertThat(result).isNotNull();
    }

    @Test
    void should_throw_no_more_space_exception_when_smart_park_boy_park_car_given_full_park_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{parkingLot});

        assertThatThrownBy(() -> smartParkingBoy.park(new Car()))
                .isInstanceOf(NoMoreSpaceException.class)
                .hasMessage("No more space");
    }

    @Test
    void should_return_a_ticket_in_most_space_parking_lot_when_park_car_having_smart_parking_boy_having__two_different_space_park_lots() {

        ParkingLot[] parkingLots = {new ParkingLot(1), new ParkingLot(2)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Ticket result = smartParkingBoy.park(new Car());

        assertThat(parkingLots[1].pickUp(result)).isNotNull();
    }

    @Test
    void should_throw_no_more_space_exception_when_park_car_having_smart_parking_boy_having_no_space_park_lot() {

        ParkingLot[] parkingLots = {new ParkingLot(0)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertThatThrownBy(() -> smartParkingBoy.park(new Car()))
                .isInstanceOf(NoMoreSpaceException.class)
                .hasMessage("No more space");
    }

    @Test
    void should_return_a_ticket_exception_when_park_car_having_smart_parking_boy_having_two_the_same_space_park_lots() {

        ParkingLot[] parkingLots = {new ParkingLot(2), new ParkingLot(2)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Ticket result = smartParkingBoy.park(new Car());

        assertThat(parkingLots[0].pickUp(result)).isNotNull();
    }

    @Test
    void should_return_a_car_when_pick_up_car_having_smart_parking_boy_who_has_a_parking_lot_with_parking_my_car() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car testCar = new Car();
        Ticket parkTicket = smartParkingBoy.park(testCar);

        Car result = smartParkingBoy.pickUp(parkTicket);

        assertThat(result).isEqualTo(testCar);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_smart_parking_boy_pick_up_given_fake_ticket() {
        ParkingLot[] parkingLots = {new ParkingLot(1)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car testCar = new Car();
        smartParkingBoy.park(testCar);

        assertThatThrownBy(()->smartParkingBoy.pickUp(new Ticket()))
                .isInstanceOf(InvalidTicketException.class)
                .hasMessage("Invalid Ticket");
    }
}


