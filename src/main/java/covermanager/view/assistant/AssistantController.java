package covermanager.view.assistant;

import covermanager.domain.Assistant;
import covermanager.domain.PaymentSystem;
import covermanager.domain.Requester;
import covermanager.util.ComponentUtil;
import covermanager.util.IntegerTextFilter;
import covermanager.view.EditController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

import java.util.Objects;


public class AssistantController extends EditController<Assistant> {
    @FXML
    protected ComboBox<Assistant.Type> typeInput;
    @FXML
    protected TextField nameInput;
    @FXML
    protected TextField skypeInput;
    @FXML
    protected TextField vkInput;
    @FXML
    protected TextField valueInput;
    @FXML
    protected TextField sentInput;
    @FXML
    protected ComboBox<PaymentSystem> paymentSystemInput;
    @FXML
    protected DatePicker paymentDateInput;
    @FXML
    protected TextArea commentInput;

    @FXML
    @Override
    protected void initialize() {
        super.initialize();

        Objects.requireNonNull(typeInput);
        Objects.requireNonNull(nameInput);
        Objects.requireNonNull(skypeInput);
        Objects.requireNonNull(vkInput);
        Objects.requireNonNull(valueInput);
        Objects.requireNonNull(sentInput);
        Objects.requireNonNull(paymentDateInput);
        Objects.requireNonNull(paymentDateInput);
        Objects.requireNonNull(commentInput);

        typeInput.setItems(FXCollections.observableArrayList(Assistant.Type.values()));
        paymentSystemInput.setItems(FXCollections.observableArrayList(PaymentSystem.values()));
        valueInput.setTextFormatter(new TextFormatter<>(new IntegerTextFilter()));
        sentInput.setTextFormatter(new TextFormatter<>(new IntegerTextFilter()));
    }

    @Override
    public void setItem(Assistant item) {
        super.setItem(item);

        typeInput.getSelectionModel().select(item.getType());
        item.typeProperty().bind(typeInput.getSelectionModel().selectedItemProperty());

        nameInput.textProperty().bindBidirectional(item.nameProperty());
        nameInput.textProperty().bindBidirectional(item.nameProperty());
        skypeInput.textProperty().bindBidirectional(item.skypeProperty());
        vkInput.textProperty().bindBidirectional(item.vkProperty());
        valueInput.textProperty().bindBidirectional(item.valueProperty(), new NumberStringConverter());
        sentInput.textProperty().bindBidirectional(item.sentProperty(), new NumberStringConverter());

        paymentSystemInput.getSelectionModel().select(item.getPaymentSystem());
        item.paymentSystemProperty().bind(paymentSystemInput.getSelectionModel().selectedItemProperty());

        paymentDateInput.valueProperty().bindBidirectional(item.paymentDateProperty());
        commentInput.textProperty().bindBidirectional(item.commentProperty());
    }
}
