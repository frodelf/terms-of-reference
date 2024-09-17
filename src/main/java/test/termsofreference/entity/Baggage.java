package test.termsofreference.entity;

public class Baggage {
    private long id;
    private ReceptionPoint receptionPoint;
    private Flight flight;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public ReceptionPoint getReceptionPoint() {
        return receptionPoint;
    }
    public void setReceptionPoint(ReceptionPoint receptionPoint) {
        this.receptionPoint = receptionPoint;
    }
}