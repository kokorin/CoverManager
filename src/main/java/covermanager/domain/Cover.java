package covermanager.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cover {
    private final StringProperty anime = new SimpleStringProperty();
    private final StringProperty song = new SimpleStringProperty();
    private final ObservableList<Requester> requesters = FXCollections.observableArrayList();

    private final ObjectProperty<Assistant> translator = new SimpleObjectProperty<>();
    private final ObjectProperty<Assistant> audioMixer = new SimpleObjectProperty<>();
    private final ObjectProperty<Assistant> videoEditor = new SimpleObjectProperty<>();

    private final StringProperty comment = new SimpleStringProperty();

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

    @XmlElementWrapper(name = "requesters")
    @XmlElement(name = "requester")
    public void setRequesters(ObservableList<Requester> items) {
        requesters.setAll(items);
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

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
}
