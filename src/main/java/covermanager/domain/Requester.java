package covermanager.domain;

public class Requester extends Person {
    private final Payment payment = new Payment();

    public Payment getPayment() {
        return payment;
    }
}
