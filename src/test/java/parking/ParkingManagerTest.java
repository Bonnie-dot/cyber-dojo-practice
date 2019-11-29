package parking;

import org.junit.jupiter.api.Test;
import parking.exception.InvalidTicketException;
import parking.exception.NoMoreSpaceException;
import parking.parkingBoy.GraduateParkingBoy;
import parking.parkingBoy.ParkingBoy;
import parking.parkingBoy.ParkingManger;
import parking.parkingBoy.SmartParkingBoy;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class ParkingManagerTest {

    @Test
    void should_return_ticket_when_ask_to_park_car_having_a_graduate_parking_with_having_a_space_parking_lot() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingManger parkingManger = new ParkingManger(parkingLots, new ParkingBoy[]{graduateParkingBoy});

        Ticket ticket = parkingManger.askParkingBoyPark(new Car());

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_two_tickets_when_ask_to_park_car_having_a_graduate_parking_boy_and_smart_parking_boy_both_having_a_space_parking_lot() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys={graduateParkingBoy,smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingLots, parkingBoys);

        Ticket ticketOne = parkingManger.askParkingBoyPark(new Car());
        Ticket ticketTwo = parkingManger.askParkingBoyPark(new Car());

        assertThat(ticketOne).isNotNull();
        assertThat(ticketTwo).isNotNull();
        assertThat(ticketOne).isNotEqualTo(ticketTwo);
    }

    @Test
    void should_return_a_ticket_when_ask_to_park_car_having_a_graduate_parking_boy_with_no_space_for_parking_lot_and_smart_parking_boy_having_a_space() {

        ParkingLot[] parkingLots = {new ParkingLot(0)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingLots, parkingBoys);

        Ticket result = parkingManger.askParkingBoyPark(new Car());

        assertThat(result).isNotNull();
    }

    @Test
    void should_return_throw_no_more_space_exception_when_ask_to_park_car_having_a_graduate_parking_with_having_no_space_parking_lot() {

        ParkingLot[] parkingLots = {new ParkingLot(0)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingManger parkingManger = new ParkingManger(parkingLots, new ParkingBoy[]{graduateParkingBoy});

        assertThatThrownBy(() -> parkingManger.askParkingBoyPark(new Car()))
                .isInstanceOf(NoMoreSpaceException.class);
    }

    @Test
    void should_return_a_ticket_and_no_more_space_exception_when_ask_to_park_having_a_graduate_parking_boy_having_no_space_and_a_smart_parking_boy_with_a_space() {

        ParkingLot[] parkingLots = {new ParkingLot(0)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingLots, parkingBoys);

        Ticket ticketOne = parkingManger.askParkingBoyPark(new Car());

        assertThat(ticketOne).isNotNull();
        assertThatThrownBy(() -> parkingManger.askParkingBoyPark(new Car())).isInstanceOf(NoMoreSpaceException.class);
    }

    @Test
    void should_return_a_car_when_ask_to_pick_up_car_having_a_graduate_parking_boy_with_having_put_your_cars_parking_lot_() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingManger parkingManger = new ParkingManger(parkingLots, new ParkingBoy[]{graduateParkingBoy});
        Ticket ticket = parkingManger.askParkingBoyPark(new Car());

        Car result = parkingManger.askParkingBoyPickUp(ticket);

        assertThat(result).isNotNull();

    }

    @Test
    void should_return_two_ticket_when_ask_to_pick_up_car_having_a_graduate_parking_boy_and_a_smart_parking_boy_both_parking_your_cars() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingLots, parkingBoys);
        Car car1 = new Car();
        Ticket ticketOne = parkingManger.askParkingBoyPark(car1);
        Car car2 = new Car();
        Ticket ticketTwo = parkingManger.askParkingBoyPark(car2);

        Car getCarOne = parkingManger.askParkingBoyPickUp(ticketOne);
        Car getCarTwo = parkingManger.askParkingBoyPickUp(ticketTwo);

        assertThat(getCarOne).isEqualTo(car1);
        assertThat(getCarTwo).isEqualTo(car2);

    }

    @Test
    void should_throw_invalid_ticket_exception_when_ask_to_pick_up_car_having_fake_ticket() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingManger parkingManger = new ParkingManger(parkingLots, new ParkingBoy[]{graduateParkingBoy});
        parkingManger.askParkingBoyPark(new Car());

        assertThatThrownBy(() -> parkingManger.askParkingBoyPickUp(new Ticket()))
                .isInstanceOf(InvalidTicketException.class);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_ask_to_pick_up_car_using_a_ticket_twice() {

        ParkingLot[] parkingLots = {new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingManger parkingManger = new ParkingManger(parkingLots, new ParkingBoy[]{graduateParkingBoy});
        Ticket ticket = parkingManger.askParkingBoyPark(new Car());
        parkingManger.askParkingBoyPickUp(ticket);

        assertThatThrownBy(() -> parkingManger.askParkingBoyPickUp(ticket))
                .isInstanceOf(InvalidTicketException.class);
    }

    @Test
    void should_return_a_ticket_when_park_car_with_parking_lot_has_space() {

        ParkingManger parkingManger = new ParkingManger(new ParkingLot[]{new ParkingLot(1)});

        Ticket result = parkingManger.park(new Car());

        assertThat(result).isNotNull();
    }

    @Test
    void should_throw_no_more_space_exception_when_park_car__with_parking_lot_having_no_space() {

        ParkingManger parkingManger = new ParkingManger(new ParkingLot[]{new ParkingLot(0)});

        assertThatThrownBy(() -> parkingManger.park(new Car())).isInstanceOf(NoMoreSpaceException.class);
    }

    @Test
    void should_return_a_car_when_pick_up_with_parking_lot_having_put_your_car() {

        ParkingManger parkingManger = new ParkingManger(new ParkingLot[]{new ParkingLot(1)});
        Car car = new Car();
        Ticket ticket = parkingManger.park(car);

        Car getCar = parkingManger.pickUp(ticket);

        assertThat(getCar).isEqualTo(car);
    }
}
