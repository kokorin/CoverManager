package covermanager.domain;

public class Assistant extends Person {
    private State state;

    public enum State {
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
