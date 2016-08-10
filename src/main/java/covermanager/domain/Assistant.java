package covermanager.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Assistant extends Person {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final IntegerProperty sent = new SimpleIntegerProperty();
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

    public int getSent() {
        return sent.get();
    }

    public IntegerProperty sentProperty() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent.set(sent);
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
