package covermanager.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Requester extends Person {
    private final Payment payment = new Payment();

    public Payment getPayment() {
        return payment;
    }
}
