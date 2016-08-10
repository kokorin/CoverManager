package covermanager.domain;

import covermanager.xml.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
public class Requester extends Person {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final IntegerProperty received = new SimpleIntegerProperty();
    private final ObjectProperty<PaymentSystem> paymentSystem = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> paymentDate = new SimpleObjectProperty<>();

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public int getReceived() {
        return received.get();
    }

    public IntegerProperty receivedProperty() {
        return received;
    }

    public void setReceived(int received) {
        this.received.set(received);
    }

    public PaymentSystem getPaymentSystem() {
        return paymentSystem.get();
    }

    public ObjectProperty<PaymentSystem> paymentSystemProperty() {
        return paymentSystem;
    }

    public void setPaymentSystem(PaymentSystem paymentSystem) {
        this.paymentSystem.set(paymentSystem);
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getPaymentDate() {
        return paymentDate.get();
    }

    public ObjectProperty<LocalDate> paymentDateProperty() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate.set(paymentDate);
    }
}
