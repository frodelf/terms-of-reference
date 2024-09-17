package test.termsofreference.entity;

import java.util.Set;

public class ReceptionPoint {
    private long id;
    private String name;
    private Set<Baggage> baggages;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Baggage> getBaggages() {
        return baggages;
    }
    public void setBaggages(Set<Baggage> baggages) {
        this.baggages = baggages;
    }
}