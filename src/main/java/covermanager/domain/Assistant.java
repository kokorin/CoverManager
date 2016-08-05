package covermanager.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Assistant extends Person {
    private final ObjectProperty<State> state = new SimpleObjectProperty<>();
    private final Payment payment = new Payment();

    public enum State {
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }

    public State getState() {
        return state.get();
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }

    public void setState(State state) {
        this.state.set(state);
    }

    public Payment getPayment() {
        return payment;
    }
}
