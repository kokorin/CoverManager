package covermanager.view.workspace;

import covermanager.Data;
import covermanager.domain.Cover;
import covermanager.view.View;
import covermanager.view.cover.CoverController;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Objects;


public class WorkspaceController {
    private final Data data;
    private final View view;

    @FXML
    protected Button addCover;
    @FXML
    protected Button editCover;
    @FXML
    protected Button removeCover;
    @FXML
    protected TableView<Cover> coverTable;
    @FXML
    protected TableColumn<Cover, String> animeColumn;
    @FXML
    protected TableColumn<Cover, String> songColumn;

    public WorkspaceController(Data data, View view) {
        this.data = data;
        this.view = view;
    }

    @FXML
    protected void initialize() {
        Objects.requireNonNull(data);
        Objects.requireNonNull(view);

        Objects.requireNonNull(addCover);
        Objects.requireNonNull(editCover);
        Objects.requireNonNull(removeCover);

        Objects.requireNonNull(coverTable);
        Objects.requireNonNull(animeColumn);
        Objects.requireNonNull(songColumn);


        BooleanBinding noItemSelected = coverTable.getSelectionModel().selectedItemProperty().isNull();
        editCover.disableProperty().bind(noItemSelected);
        removeCover.disableProperty().bind(noItemSelected);

        coverTable.setItems(data.getCovers());
        animeColumn.setCellValueFactory(cellData -> cellData.getValue().animeProperty());
        songColumn.setCellValueFactory(cellData -> cellData.getValue().songProperty());

        addCover.setOnAction(this::onAddCoverEvent);
        editCover.setOnAction(this::onEditCoverEvent);
        removeCover.setOnAction(this::onRemoveCoverEvent);
    }

    protected void onAddCoverEvent(ActionEvent event) {
        editCover(null);
    }

    protected void onEditCoverEvent(ActionEvent event) {
        Cover cover = coverTable.getSelectionModel().getSelectedItem();
        editCover(cover);
    }

    protected void editCover(Cover cover) {
        final Cover edtion;

        if (cover == null) {
            edtion = new Cover();
        } else {
            StringWriter writer = new StringWriter();
            JAXB.marshal(cover, writer);
            StringReader reader = new StringReader(writer.toString());
            edtion = JAXB.unmarshal(reader, Cover.class);
        }

        CoverController controller = view
                .forController(CoverController.class)
                .withTitle("Cover")
                .beforeShow(cc -> cc.setCover(edtion))
                .showAndWait();

        if (controller.isSave()) {
            int index = data.getCovers().indexOf(cover);
            if (index == -1) {
                data.getCovers().add(edtion);
            } else {
                data.getCovers().set(index, edtion);
            }
        }
    }

    protected void onRemoveCoverEvent(ActionEvent event) {
        Cover cover = coverTable.getSelectionModel().getSelectedItem();
        data.getCovers().remove(cover);
    }
}
