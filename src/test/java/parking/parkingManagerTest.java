package parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

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
}
