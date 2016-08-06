package covermanager;

import covermanager.domain.Cover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {
    private final ObservableList<Cover> covers = FXCollections.observableArrayList();

    public ObservableList<Cover> getCovers() {
        return covers;
    }
}
