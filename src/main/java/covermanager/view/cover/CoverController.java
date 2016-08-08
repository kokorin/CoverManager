package covermanager.view.cover;

import covermanager.domain.Cover;
import covermanager.domain.Requester;
import covermanager.view.EditController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Objects;

public class CoverController extends EditController<Cover> {
    @FXML
    public TextField animeInput;
    @FXML
    public TextField songInput;

    @FXML
    public Button addRequesterButton;
    @FXML
    public Button editRequesterButton;
    @FXML
    public Button removeRequesterButton;
    @FXML
    public TableView<Requester> requestersTable;
    @FXML
    public TableColumn<Requester, String> requesterNameColumn;
    @FXML
    public TableColumn<Requester, Integer> requesterTotalValueColumn;
    @FXML
    public TableColumn<Requester, Integer> requesterPaidValueColumn;
    @FXML
    public TableColumn<Requester, String> requesterPaymentSystemColumn;

    @FXML
    public TableColumn<Requester, String> requesterPaymentDateColumn;

    @Override
    protected void initialize() {
        super.initialize();

        Objects.requireNonNull(animeInput);
        Objects.requireNonNull(songInput);

        Objects.requireNonNull(addRequesterButton);
        Objects.requireNonNull(editRequesterButton);
        Objects.requireNonNull(removeRequesterButton);
        Objects.requireNonNull(requestersTable);
        Objects.requireNonNull(requesterNameColumn);
        Objects.requireNonNull(requesterTotalValueColumn);
        Objects.requireNonNull(requesterPaidValueColumn);
        Objects.requireNonNull(requesterPaymentSystemColumn);
        Objects.requireNonNull(requesterPaymentDateColumn);

        requesterNameColumn.setCellValueFactory(r -> r.getValue().nameProperty());
        requesterTotalValueColumn.setCellValueFactory(r -> r.getValue().getPayment().totalValueProperty().asObject());
        requesterPaidValueColumn.setCellValueFactory(r -> r.getValue().getPayment().paidValueProperty().asObject());
        requesterPaymentSystemColumn.setCellValueFactory(r -> r.getValue().getPayment().systemProperty().asString());
        requesterPaymentDateColumn.setCellValueFactory(r -> r.getValue().getPayment().dateProperty().asString());
    }

    @Override
    public void setItem(Cover item) {
        super.setItem(item);

        animeInput.textProperty().bindBidirectional(item.animeProperty());
        songInput.textProperty().bindBidirectional(item.songProperty());

        requestersTable.setItems(item.getRequesters());

    }
}
