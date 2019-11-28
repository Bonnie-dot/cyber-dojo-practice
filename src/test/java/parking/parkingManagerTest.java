package parking;

import org.junit.jupiter.api.Test;
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

        Ticket ticket = parkingManger.askOtherPark(new Car());

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_two_tickets_when_ask_to_park_car_having_a_graduate_parking_boy_and_smart_parking_boy_both_having_a_space_parking_lot() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys={graduateParkingBoy,smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingBoys);

        Ticket ticketOne = parkingManger.askOtherPark(new Car());
        Ticket ticketTwo = parkingManger.askOtherPark(new Car());

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

        Ticket result = parkingManger.askOtherPark(new Car());

        assertThat(result).isNotNull();
    }

    @Test
    void should_return_throw_no_more_space_exception_when_ask_to_park_car_having_a_graduate_parking_with_having_no_space_parking_lot() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(0)});
        ParkingManger parkingManger = new ParkingManger(new ParkingBoy[]{graduateParkingBoy});

        assertThatThrownBy(() -> parkingManger.askOtherPark(new Car()))
                .isInstanceOf(NoMoreSpaceException.class);
    }

    @Test
    void should_return_a_ticket_and_no_more_space_exception_when_ask_to_park_having_a_graduate_parking_boy_having_no_space_and_a_smart_parking_boy_with_a_space() {

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot[]{new ParkingLot(0)});
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot[]{new ParkingLot(1)});
        ParkingBoy[] parkingBoys = {graduateParkingBoy, smartParkingBoy};
        ParkingManger parkingManger = new ParkingManger(parkingBoys);

        Ticket ticketOne = parkingManger.askOtherPark(new Car());

        assertThat(ticketOne).isNotNull();
        assertThatThrownBy(() -> parkingManger.askOtherPark(new Car())).isInstanceOf(NoMoreSpaceException.class);
    }
}
