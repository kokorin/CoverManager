package covermanager.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cover {
    private final StringProperty anime = new SimpleStringProperty();
    private final StringProperty song = new SimpleStringProperty();
    private final IntegerProperty price = new SimpleIntegerProperty();
    private final ObservableList<Requester> requesters = FXCollections.emptyObservableList();

    private final ObjectProperty<Assistant> translator = new SimpleObjectProperty<>();
    private final ObjectProperty<Assistant> audioMixer = new SimpleObjectProperty<>();
    private final ObjectProperty<Assistant> videoEditor = new SimpleObjectProperty<>();

    public enum State {
        REQUESTED,
        PREPAID,
        PAID,
        DONE,
        PUBLISHED
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

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public ObservableList<Requester> getRequesters() {
        return requesters;
    }

    public Assistant getTranslator() {
        return translator.get();
    }

    public ObjectProperty<Assistant> translatorProperty() {
        return translator;
    }

    public void setTranslator(Assistant translator) {
        this.translator.set(translator);
    }

    public Assistant getAudioMixer() {
        return audioMixer.get();
    }

    public ObjectProperty<Assistant> audioMixerProperty() {
        return audioMixer;
    }

    public void setAudioMixer(Assistant audioMixer) {
        this.audioMixer.set(audioMixer);
    }

    public Assistant getVideoEditor() {
        return videoEditor.get();
    }

    public ObjectProperty<Assistant> videoEditorProperty() {
        return videoEditor;
    }

    public void setVideoEditor(Assistant videoEditor) {
        this.videoEditor.set(videoEditor);
    }
}
