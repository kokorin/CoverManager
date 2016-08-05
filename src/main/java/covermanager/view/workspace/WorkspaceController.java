package covermanager.view.workspace;

import covermanager.Data;
import covermanager.domain.Cover;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class WorkspaceController {
    private final Data data;

    @FXML
    protected TableView<Cover> coverTable;
    @FXML
    protected Button addCover;
    @FXML
    protected Button editCover;
    @FXML
    protected Button removeCover;

    @Inject
    public WorkspaceController(Data data) {
        this.data = data;
    }

    @FXML
    protected void initialized() {
        Objects.requireNonNull(data);
        Objects.requireNonNull(coverTable);
        Objects.requireNonNull(addCover);
        Objects.requireNonNull(editCover);
        Objects.requireNonNull(removeCover);

        BooleanBinding noItemSelected = coverTable.getSelectionModel().selectedItemProperty().isNull();
        editCover.disableProperty().bind(noItemSelected);
        editCover.disableProperty().bind(noItemSelected);

        coverTable.setItems(data.getCovers());
    }
}
