package covermanager.view.workspace;

import covermanager.domain.Data;
import covermanager.domain.Cover;
import covermanager.util.CloneUtil;
import covermanager.util.ListUtil;
import covermanager.view.View;
import covermanager.view.cover.CoverController;
import covermanager.view.table.TableEdit;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.util.Objects;


public class WorkspaceController {
    private final Data data;
    private final View view;

    @FXML
    protected Parent root;
    @FXML
    protected TableEdit<Cover> coverTable;
    @FXML
    protected TableColumn<Cover, String> animeColumn;
    @FXML
    protected TableColumn<Cover, String> songColumn;
    @FXML
    protected TableColumn<Cover, Boolean> publishedColumn;
    @FXML
    protected TableColumn<Cover, Number> priceColumn;
    @FXML
    protected TableColumn<Cover, Number> receivedColumn;
    @FXML
    protected TableColumn<Cover, Number> feeColumn;
    @FXML
    protected TableColumn<Cover, Number> sentColumn;

    public WorkspaceController(Data data, View view) {
        this.data = data;
        this.view = view;
    }

    @FXML
    protected void initialize() {
        Objects.requireNonNull(root);

        Objects.requireNonNull(data);
        Objects.requireNonNull(view);

        Objects.requireNonNull(coverTable);
        Objects.requireNonNull(animeColumn);
        Objects.requireNonNull(songColumn);
        Objects.requireNonNull(publishedColumn);
        Objects.requireNonNull(priceColumn);
        Objects.requireNonNull(receivedColumn);
        Objects.requireNonNull(feeColumn);
        Objects.requireNonNull(sentColumn);

        coverTable.setItems(data.getCovers());
        animeColumn.setCellValueFactory(c -> c.getValue().animeProperty());
        songColumn.setCellValueFactory(c -> c.getValue().songProperty());
        publishedColumn.setCellValueFactory(c -> c.getValue().publishedProperty());
        priceColumn.setCellValueFactory(c -> c.getValue().priceProperty());
        receivedColumn.setCellValueFactory(c -> c.getValue().receivedProperty());
        feeColumn.setCellValueFactory(c -> c.getValue().feeProperty());
        sentColumn.setCellValueFactory(c -> c.getValue().sentProperty());
    }

    @FXML
    protected void onAddCover(ActionEvent event) {
        editCover(new Cover());
    }

    @FXML
    protected void onEditCover(ActionEvent event) {
        Cover cover = coverTable.getSelectedItem();
        editCover(cover);
    }

    private void editCover(Cover cover) {
        final Cover edtion = CloneUtil.deepClone(cover);

        CoverController controller = view
                .forController(CoverController.class)
                .withTitle("Cover")
                .beforeShow(cc -> cc.setItem(edtion))
                .withModality(root.getScene().getWindow())
                .showAndWait();

        if (controller.isSave()) {
            ListUtil.addOrUpdate(data.getCovers(), edtion, cover);
        }
    }

    @FXML
    protected void onRemoveCover(ActionEvent event) {
        Cover cover = coverTable.getSelectedItem();
        data.getCovers().remove(cover);
    }
}
