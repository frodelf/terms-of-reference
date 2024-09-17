package test.termsofreference.entity;

public class Coupon {
    private long id;
    private int discountPercentage;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getDiscountPercentage() {
        return discountPercentage;
    }
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}