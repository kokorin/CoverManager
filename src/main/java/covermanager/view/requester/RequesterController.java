package covermanager.view.requester;

import covermanager.domain.Payment;
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
    public TextField totalValueInput;
    @FXML
    public TextField paidValueInput;
    @FXML
    public ComboBox<Payment.System> paymentSystemInput;
    @FXML
    public DatePicker paymentDateInput;

    @Override
    protected void initialize() {
        super.initialize();

        Objects.requireNonNull(nameInput);
        Objects.requireNonNull(skypeInput);
        Objects.requireNonNull(vkInput);
        Objects.requireNonNull(totalValueInput);
        Objects.requireNonNull(paidValueInput);
        Objects.requireNonNull(paymentSystemInput);
        Objects.requireNonNull(paymentDateInput);

        paymentSystemInput.setItems(FXCollections.observableArrayList(Payment.System.values()));
        totalValueInput.setTextFormatter(new TextFormatter<>(new IntegerTextFilter()));
        paidValueInput.setTextFormatter(new TextFormatter<>(new IntegerTextFilter()));
    }

    @Override
    public void setItem(Requester item) {
        super.setItem(item);

        nameInput.textProperty().bindBidirectional(item.nameProperty());
        skypeInput.textProperty().bindBidirectional(item.skypeProperty());
        vkInput.textProperty().bindBidirectional(item.vkProperty());
        totalValueInput.textProperty().bindBidirectional(item.getPayment().totalValueProperty(), new NumberStringConverter());
        paidValueInput.textProperty().bindBidirectional(item.getPayment().paidValueProperty(), new NumberStringConverter());

        paymentSystemInput.getSelectionModel().select(item.getPayment().getSystem());
        item.getPayment().systemProperty().bind(paymentSystemInput.getSelectionModel().selectedItemProperty());

        paymentDateInput.valueProperty().bindBidirectional(item.getPayment().dateProperty());
    }
}
