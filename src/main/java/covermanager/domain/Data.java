package covermanager.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {
    private final ObservableList<Cover> covers = FXCollections.observableArrayList();

    @XmlElementWrapper(name = "covers")
    @XmlElement(name = "cover")
    public void setCovers(ObservableList<Cover> covers) {
        this.covers.setAll(covers);
    }

    public ObservableList<Cover> getCovers() {
        return covers;
    }
}
