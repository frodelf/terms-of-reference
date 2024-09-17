package test.termsofreference.entity;

import java.util.Set;

public class Flight {
    private long id;
    private String flightName;
    private Set<Baggage> baggages;
    private Set<Ticket> tickets;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFlightName() {
        return flightName;
    }
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
    public Set<Baggage> getBaggages() {
        return baggages;
    }
    public void setBaggages(Set<Baggage> baggages) {
        this.baggages = baggages;
    }
    public Set<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}