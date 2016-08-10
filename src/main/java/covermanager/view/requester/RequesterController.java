package covermanager.view.requester;

import covermanager.domain.PaymentSystem;
import covermanager.domain.Requester;
import covermanager.util.IntegerTextFilter;
import covermanager.view.EditController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

import java.util.Objects;

public class RequesterController extends EditController<Requester> {
    @FXML
    public TextField nameInput;
    @FXML
    public TextField skypeInput;
    @FXML
    public TextField vkInput;
    @FXML
    public TextField valueInput;
    @FXML
    public TextField receivedInput;
    @FXML
    public ComboBox<PaymentSystem> paymentSystemInput;
    @FXML
    public DatePicker paymentDateInput;

    @Override
    protected void initialize() {
        super.initialize();

        Objects.requireNonNull(nameInput);
        Objects.requireNonNull(skypeInput);
        Objects.requireNonNull(vkInput);
        Objects.requireNonNull(valueInput);
        Objects.requireNonNull(receivedInput);
        Objects.requireNonNull(paymentSystemInput);
        Objects.requireNonNull(paymentDateInput);

        paymentSystemInput.setItems(FXCollections.observableArrayList(PaymentSystem.values()));
        valueInput.setTextFormatter(new TextFormatter<>(new IntegerTextFilter()));
        receivedInput.setTextFormatter(new TextFormatter<>(new IntegerTextFilter()));
    }

    @Override
    public void setItem(Requester item) {
        super.setItem(item);

        nameInput.textProperty().bindBidirectional(item.nameProperty());
        skypeInput.textProperty().bindBidirectional(item.skypeProperty());
        vkInput.textProperty().bindBidirectional(item.vkProperty());
        valueInput.textProperty().bindBidirectional(item.valueProperty(), new NumberStringConverter());
        receivedInput.textProperty().bindBidirectional(item.receivedProperty(), new NumberStringConverter());

        paymentSystemInput.getSelectionModel().select(item.getPaymentSystem());
        item.paymentSystemProperty().bind(paymentSystemInput.getSelectionModel().selectedItemProperty());

        paymentDateInput.valueProperty().bindBidirectional(item.paymentDateProperty());
    }
}
