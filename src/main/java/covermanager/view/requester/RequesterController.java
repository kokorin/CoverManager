package covermanager.view.requester;

import covermanager.domain.PaymentSystem;
import covermanager.domain.Requester;
import covermanager.util.IntegerTextFilter;
import covermanager.view.EditController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.util.Objects;

public class RequesterController extends EditController<Requester> {
    @FXML
    protected TextField nameInput;
    @FXML
    protected TextField skypeInput;
    @FXML
    protected TextField vkInput;
    @FXML
    protected TextField valueInput;
    @FXML
    protected TextField receivedInput;
    @FXML
    protected ComboBox<PaymentSystem> paymentSystemInput;
    @FXML
    protected DatePicker paymentDateInput;
    @FXML
    protected TextArea commentInput;

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
        Objects.requireNonNull(commentInput);

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
        commentInput.textProperty().bindBidirectional(item.commentProperty());
    }
}
