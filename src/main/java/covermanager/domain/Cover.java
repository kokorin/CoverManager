package covermanager.domain;

import covermanager.xml.LocalDateAdapter;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    private final ObservableList<Requester> requesters = FXCollections.observableArrayList();
    //TODO make Assistants List instead of separate fields.
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
