package parking;

public class ParkingManger {

    private GraduateParkingBoy graduateParkingBoy;

    public ParkingManger(GraduateParkingBoy graduateParkingBoy) {
        this.graduateParkingBoy=graduateParkingBoy;
    }

    public Ticket askOtherPark(Car car) {
        return graduateParkingBoy.park(car);
    }
}
