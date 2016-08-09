package covermanager.view.cover;

import covermanager.domain.Cover;
import covermanager.domain.Payment;
import covermanager.domain.Requester;
import covermanager.util.CloneUtil;
import covermanager.util.ListUtil;
import covermanager.view.EditController;
import covermanager.view.View;
import covermanager.view.requester.RequesterController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Objects;

public class CoverController extends EditController<Cover> {
    private final View view;

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
    @FXML
    public TextField priceLabel;
    @FXML
    public TextField receivedLabel;

    public CoverController(View view) {
        this.view = view;
    }

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

        BooleanBinding noRequesterSelected  = requestersTable.getSelectionModel().selectedItemProperty().isNull();
        editRequesterButton.disableProperty().bind(noRequesterSelected);
        removeRequesterButton.disableProperty().bind(noRequesterSelected);

        addRequesterButton.setOnAction(this::onAddRequesterEvent);
        editRequesterButton.setOnAction(this::onEditRequesterEvent);
        removeRequesterButton.setOnAction(this::onRemoveRequesterEvent);
    }

    @Override
    public void setItem(Cover item) {
        super.setItem(item);

        animeInput.textProperty().bindBidirectional(item.animeProperty());
        songInput.textProperty().bindBidirectional(item.songProperty());

        requestersTable.setItems(item.getRequesters());

        IntegerBinding price = Bindings.createIntegerBinding(
                () -> item.getRequesters().stream().map(Requester::getPayment).mapToInt(Payment::getTotalValue).sum(),
                item.getRequesters()
        );
        priceLabel.textProperty().bind(price.asString());


        IntegerBinding received = Bindings.createIntegerBinding(
                () -> item.getRequesters().stream().map(Requester::getPayment).mapToInt(Payment::getPaidValue).sum(),
                item.getRequesters()
        );
        receivedLabel.textProperty().bind(received.asString());
    }

    protected void onAddRequesterEvent(ActionEvent event) {
        editRequester(new Requester());
    }

    protected void onEditRequesterEvent(ActionEvent event) {
        Requester requester = requestersTable.getSelectionModel().getSelectedItem();
        editRequester(requester);
    }

    protected void onRemoveRequesterEvent(ActionEvent event) {
        Requester requester = requestersTable.getSelectionModel().getSelectedItem();
        getItem().getRequesters().remove(requester);
    }

    protected void editRequester(Requester requester) {
        final Requester edtion = CloneUtil.deepClone(requester);

        RequesterController controller = view
                .forController(RequesterController.class)
                .withTitle("Requester")
                .beforeShow(rc -> rc.setItem(edtion))
                .withModality(root.getScene().getWindow())
                .showAndWait();

        if (controller.isSave()) {
            ListUtil.addOrUpdate(getItem().getRequesters(), edtion, requester);
        }
    }
}
