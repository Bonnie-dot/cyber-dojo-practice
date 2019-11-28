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

public class parkingManagerTest {

    @Test
    void should_return_ticket_when_ask_to_park_car_having_a_graduate_parking_with_having_a_space_parking_lot() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingManger parkingManger = new ParkingManger(new ParkingBoy[]{graduateParkingBoy});

        Ticket ticket = parkingManger.askParkingBoyPark(new Car());

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_two_tickets_when_ask_to_park_car_having_a_graduate_parking_boy_and_smart_parking_boy_both_having_a_space_parking_lot() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys={graduateParkingBoy,smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingBoys);

        Ticket ticketOne = parkingManger.askParkingBoyPark(new Car());
        Ticket ticketTwo = parkingManger.askParkingBoyPark(new Car());

        assertThat(ticketOne).isNotNull();
        assertThat(ticketTwo).isNotNull();
        assertThat(ticketOne).isNotEqualTo(ticketTwo);
    }

    @Test
    void should_return_a_ticket_when_ask_to_park_car_having_a_graduate_parking_boy_with_no_space_for_parking_lot_and_smart_parking_boy_having_a_space() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(0)});
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingBoys);

        Ticket result = parkingManger.askParkingBoyPark(new Car());

        assertThat(result).isNotNull();
    }

    @Test
    void should_return_throw_no_more_space_exception_when_ask_to_park_car_having_a_graduate_parking_with_having_no_space_parking_lot() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(0)});
        ParkingManger parkingManger = new ParkingManger(new ParkingBoy[]{graduateParkingBoy});

        assertThatThrownBy(() -> parkingManger.askParkingBoyPark(new Car()))
                .isInstanceOf(NoMoreSpaceException.class);
    }

    @Test
    void should_return_a_ticket_and_no_more_space_exception_when_ask_to_park_having_a_graduate_parking_boy_having_no_space_and_a_smart_parking_boy_with_a_space() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(0)});
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingBoys);

        Ticket ticketOne = parkingManger.askParkingBoyPark(new Car());

        assertThat(ticketOne).isNotNull();
        assertThatThrownBy(() -> parkingManger.askParkingBoyPark(new Car())).isInstanceOf(NoMoreSpaceException.class);
    }

    @Test
    void should_return_a_car_when_ask_to_pick_up_car_having_a_graduate_parking_boy_with_having_put_your_cars_parking_lot_() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingManger parkingManger = new ParkingManger(new ParkingBoy[]{graduateParkingBoy});
        Ticket ticket = parkingManger.askParkingBoyPark(new Car());

        Car result = parkingManger.askParkingBoyPickUp(ticket);

        assertThat(result).isNotNull();

    }

    @Test
    void should_return_two_ticket_when_ask_to_pick_up_car_having_a_graduate_parking_boy_and_a_smart_parking_boy_both_parking_your_cars() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingBoys);
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

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingManger parkingManger = new ParkingManger(new ParkingBoy[]{graduateParkingBoy});
        parkingManger.askParkingBoyPark(new Car());

        assertThatThrownBy(() -> parkingManger.askParkingBoyPickUp(new Ticket()))
                .isInstanceOf(InvalidTicketException.class);
    }
}
