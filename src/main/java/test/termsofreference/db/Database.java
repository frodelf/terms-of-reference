package test.termsofreference.db;

import test.termsofreference.entity.*;

import java.math.BigDecimal;
import java.util.*;

public class Database {
    private static Database instance;
    private final Map<Long, Ticket> tickets = new HashMap<>();
    private final Map<Long, Baggage> baggages = new HashMap<>();
    private final Map<Long, Coupon> coupons = new HashMap<>();
    private final Map<Long, Flight>  flights= new HashMap<>();
    private final Map<Long, Customer> customers = new HashMap<>();
    private final Map<Long, ReceptionPoint> receptionPoints = new HashMap<>();

    private Database() {
        Flight flight = new Flight();
        flight.setId(1);
        flight.setFlightName("flight 1");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setSurname("Smith");

        Ticket ticket1 = new Ticket();
        ticket1.setId(1);
        ticket1.setPrice(BigDecimal.valueOf(1000));

        Ticket ticket2 = new Ticket();
        ticket2.setId(2);
        ticket2.setPrice(BigDecimal.valueOf(1000));

        Ticket ticket3 = new Ticket();
        ticket3.setId(3);
        ticket3.setPrice(BigDecimal.valueOf(1000));

        Baggage baggage1 = new Baggage();
        baggage1.setId(1);

        Baggage baggage2 = new Baggage();
        baggage2.setId(2);

        Coupon coupon1 = new Coupon();
        coupon1.setId(1);
        coupon1.setDiscountPercentage(10);

        Coupon coupon2 = new Coupon();
        coupon2.setId(2);
        coupon2.setDiscountPercentage(50);

        Coupon coupon3 = new Coupon();
        coupon3.setId(3);
        coupon3.setDiscountPercentage(60);

        ReceptionPoint receptionPoint1 = new ReceptionPoint();
        receptionPoint1.setId(1);
        receptionPoint1.setName("Point 1");

        ReceptionPoint receptionPoint2 = new ReceptionPoint();
        receptionPoint2.setId(2);
        receptionPoint2.setName("Point 2");

        receptionPoint1.setBaggages(Set.of(baggage1));
        baggage1.setReceptionPoint(receptionPoint1);

        customer.setTickets(Set.of(ticket1, ticket2));
        customer.setBaggages(Set.of(baggage1, baggage2));

        ticket1.setCustomer(customer);
        ticket2.setCustomer(customer);

        ticket1.setFlight(flight);
        ticket2.setFlight(flight);
        ticket3.setFlight(flight);

        flight.setTickets(Set.of(ticket1, ticket2, ticket3));

        baggage1.setCustomer(customer);
        baggage2.setCustomer(customer);
        baggage1.setFlight(flight);
        baggage2.setFlight(flight);

        flight.setBaggages(Set.of(baggage1, baggage2));

        tickets.put(ticket1.getId(), ticket1);
        tickets.put(ticket2.getId(), ticket2);
        tickets.put(ticket3.getId(), ticket3);

        baggages.put(baggage1.getId(), baggage1);
        baggages.put(baggage2.getId(), baggage2);

        coupons.put(coupon1.getId(), coupon1);
        coupons.put(coupon2.getId(), coupon2);
        coupons.put(coupon3.getId(), coupon3);

        flights.put(flight.getId(), flight);

        customers.put(customer.getId(), customer);

        receptionPoints.put(receptionPoint1.getId(), receptionPoint1);
        receptionPoints.put(receptionPoint2.getId(), receptionPoint2);
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Ticket getTicket(long id) {
        return tickets.get(id);
    }
    public Baggage getBaggage(long id) {
        return baggages.get(id);
    }
    public Coupon getCoupon(long id) {
        return coupons.get(id);
    }
    public Flight getFlight(long id) {
        return flights.get(id);
    }
    public Customer getCustomer(long id) {
        return customers.get(id);
    }
    public ReceptionPoint getReceptionPoint(long id) {
        return receptionPoints.get(id);
    }

    public void setBaggage(Baggage baggage) {
        baggages.put(baggage.getId(), baggage);
    }
}