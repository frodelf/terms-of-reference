package test.termsofreference.entity;

import java.util.Set;

public class Customer {
    private long id;
    private String firstName;
    private String surname;
    private Set<Ticket> tickets;
    private Set<Baggage> baggages;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Set<Baggage> getBaggages() {
        return baggages;
    }
    public void setBaggages(Set<Baggage> baggages) {
        this.baggages = baggages;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Set<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}