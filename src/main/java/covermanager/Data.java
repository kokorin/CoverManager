package covermanager;

import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import covermanager.domain.Cover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
public class Data {
    private final ObservableList<Cover> covers = FXCollections.emptyObservableList();

    public ObservableList<Cover> getCovers() {
        return covers;
    }
}
