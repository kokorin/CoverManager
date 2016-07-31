package covermanager.domain;

import java.time.LocalDate;

public class Payment {
    private int totalValue;
    private int paidValue;
    private System system;
    private LocalDate date;

    public enum System {
        WEBMONEY,
        QIWI
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public int getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(int paidValue) {
        this.paidValue = paidValue;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}
