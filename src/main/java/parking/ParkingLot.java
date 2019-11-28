package parking;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Comparable<ParkingLot> {
    private int space;
    private Map<Ticket, Car> ticketCarMap;

    public ParkingLot(int space) {
        this.space = space;
        this.ticketCarMap = new HashMap<>(space);
    }

    public Ticket park(Car car) {
        if (this.ticketCarMap.size() == space) {
            throw new NoMoreSpaceException("No more space");
        }
        Ticket ticket = new Ticket();
        this.ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!this.ticketCarMap.containsKey(ticket)) {
            throw new InvalidTicketException("Invalid Ticket");
        }
        return this.ticketCarMap.remove(ticket);
    }

    public Integer getRemainCapacity() {
        return this.space - this.ticketCarMap.size();
    }

    @Override
    public int compareTo(ParkingLot o) {
        return o.getRemainCapacity() - this.getRemainCapacity();
    }

    public Boolean isValidTicket(Ticket ticket){
        System.out.println(this.ticketCarMap.containsKey(ticket));
        return this.ticketCarMap.containsKey(ticket);
    }
}
