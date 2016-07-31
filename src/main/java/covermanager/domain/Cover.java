package covermanager.domain;

import java.util.List;

public class Cover {
    private String anime;
    private String song;
    private int price;
    private List<Requester> requesters;

    private Assistant translator;
    private Assistant audioMixer;
    private Assistant videoEditor;

    public enum State {
        REQUESTED,
        PREPAID,
        PAID,
        DONE,
        PUBLISHED
    }
}
