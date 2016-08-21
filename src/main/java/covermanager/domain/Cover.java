package covermanager.domain;

import covermanager.xml.LocalDateAdapter;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
public class Cover {
    private final StringProperty anime = new SimpleStringProperty();
    private final StringProperty song = new SimpleStringProperty();
    private final BooleanProperty published = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> publishDate = new SimpleObjectProperty<>();
    private final StringProperty comment = new SimpleStringProperty();

    private final ReadOnlyIntegerWrapper price = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerWrapper received = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerWrapper fee = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerWrapper sent = new ReadOnlyIntegerWrapper();

    private final ObservableList<Requester> requesters = FXCollections.observableArrayList();
    private final ObservableList<Assistant> assistants = FXCollections.observableArrayList();

    public static final Callback<Cover, Observable[]> EXTRACTOR = c -> new Observable[]{
            c.anime, c.song, c.published, c.publishDate, c.requesters, c.assistants
    };

    public Cover() {
        price.bind(Bindings.createIntegerBinding(
                () -> requesters.stream().mapToInt(Requester::getValue).sum(),
                requesters
        ));
        received.bind(Bindings.createIntegerBinding(
                () -> requesters.stream().mapToInt(Requester::getReceived).sum(),
                requesters
        ));

        fee.bind(Bindings.createIntegerBinding(
                () -> assistants.stream().mapToInt(Assistant::getValue).sum(),
                assistants
        ));
        sent.bind(Bindings.createIntegerBinding(
                () -> assistants.stream().mapToInt(Assistant::getSent).sum(),
                assistants
        ));
    }

    public String getAnime() {
        return anime.get();
    }

    public StringProperty animeProperty() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime.set(anime);
    }

    public String getSong() {
        return song.get();
    }

    public StringProperty songProperty() {
        return song;
    }

    public void setSong(String song) {
        this.song.set(song);
    }

    public boolean isPublished() {
        return published.get();
    }

    public BooleanProperty publishedProperty() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published.set(published);
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getPublishDate() {
        return publishDate.get();
    }

    public ObjectProperty<LocalDate> publishDateProperty() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate.set(publishDate);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getPrice() {
        return price.get();
    }

    public ReadOnlyIntegerProperty priceProperty() {
        return price.getReadOnlyProperty();
    }

    public int getReceived() {
        return received.get();
    }

    public ReadOnlyIntegerProperty receivedProperty() {
        return received.getReadOnlyProperty();
    }

    public int getFee() {
        return fee.get();
    }

    public ReadOnlyIntegerProperty feeProperty() {
        return fee.getReadOnlyProperty();
    }

    public int getSent() {
        return sent.get();
    }

    public ReadOnlyIntegerProperty sentProperty() {
        return sent.getReadOnlyProperty();
    }

    @XmlElementWrapper(name = "requesters")
    @XmlElement(name = "requester")
    public void setRequesters(ObservableList<Requester> requesters) {
        this.requesters.setAll(requesters);
    }

    public ObservableList<Requester> getRequesters() {
        return requesters;
    }

    @XmlElementWrapper(name = "assistants")
    @XmlElement(name = "assistant")
    public void setAssistants(ObservableList<Assistant> assistants) {
        this.assistants.setAll(assistants);
    }

    public ObservableList<Assistant> getAssistants() {
        return assistants;
    }
}
