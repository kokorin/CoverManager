package covermanager.domain;

import covermanager.xml.LocalDateAdapter;
import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
public class Assistant extends Person {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final IntegerProperty sent = new SimpleIntegerProperty();
    private final ObjectProperty<PaymentSystem> paymentSystem = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> paymentDate = new SimpleObjectProperty<>();

    private final BooleanProperty completed = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> completionDate = new SimpleObjectProperty<>();

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

    public boolean isCompleted() {
        return completed.get();
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getCompletionDate() {
        return completionDate.get();
    }

    public ObjectProperty<LocalDate> completionDateProperty() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate.set(completionDate);
    }
}
