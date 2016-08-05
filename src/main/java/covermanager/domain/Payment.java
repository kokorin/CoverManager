package covermanager.domain;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Payment {
    private final IntegerProperty totalValue = new SimpleIntegerProperty();
    private final IntegerProperty  paidValue = new SimpleIntegerProperty();
    private final ObjectProperty<System> system = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    public enum System {
        WEBMONEY,
        QIWI
    }

    public int getTotalValue() {
        return totalValue.get();
    }

    public IntegerProperty totalValueProperty() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue.set(totalValue);
    }

    public int getPaidValue() {
        return paidValue.get();
    }

    public IntegerProperty paidValueProperty() {
        return paidValue;
    }

    public void setPaidValue(int paidValue) {
        this.paidValue.set(paidValue);
    }

    public System getSystem() {
        return system.get();
    }

    public ObjectProperty<System> systemProperty() {
        return system;
    }

    public void setSystem(System system) {
        this.system.set(system);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
