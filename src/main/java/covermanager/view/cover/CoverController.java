package covermanager.view.cover;

import covermanager.domain.Assistant;
import covermanager.domain.Cover;
import covermanager.domain.Requester;
import covermanager.util.CloneUtil;
import covermanager.util.ListUtil;
import covermanager.view.EditController;
import covermanager.view.View;
import covermanager.view.assistant.AssistantController;
import covermanager.view.requester.RequesterController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class CoverController extends EditController<Cover> {
    private final View view;

    @FXML
    protected TextField animeInput;
    @FXML
    protected TextField songInput;
    @FXML
    protected TextField priceLabel;
    @FXML
    protected TextField receivedLabel;
    @FXML
    protected TextField feeLabel;
    @FXML
    protected TextField sendLabel;

    @FXML
    protected Button addRequesterButton;
    @FXML
    protected Button editRequesterButton;
    @FXML
    protected Button removeRequesterButton;
    @FXML
    protected TableView<Requester> requestersTable;
    @FXML
    protected TableColumn<Requester, String> requesterNameColumn;
    @FXML
    protected TableColumn<Requester, Number> requesterValueColumn;
    @FXML
    protected TableColumn<Requester, Number> requesterReceivedColumn;
    @FXML
    protected TableColumn<Requester, String> requesterPaymentSystemColumn;

    @FXML
    protected TableColumn<Requester, String> requesterPaymentDateColumn;
    @FXML
    protected Button addAssistantButton;
    @FXML
    protected Button editAssistantButton;
    @FXML
    protected Button removeAssistantButton;
    @FXML
    protected TableView<Assistant> assistantsTable;
    @FXML
    protected TableColumn<Assistant, String> assistantTypeColumn;
    @FXML
    protected TableColumn<Assistant, String> assistantNameColumn;
    @FXML
    protected TableColumn<Assistant, Number> assistantValueColumn;
    @FXML
    protected TableColumn<Assistant, Number> assistantSentColumn;
    @FXML
    protected TableColumn<Assistant, String> assistantPaymentSystemColumn;
    @FXML
    protected TableColumn<Assistant, String> assistantPaymentDateColumn;
    @FXML
    protected CheckBox publishedInput;

    public CoverController(View view) {
        this.view = view;
    }

    @FXML
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
        Objects.requireNonNull(requesterValueColumn);
        Objects.requireNonNull(requesterReceivedColumn);
        Objects.requireNonNull(requesterPaymentSystemColumn);
        Objects.requireNonNull(requesterPaymentDateColumn);

        Objects.requireNonNull(addAssistantButton);
        Objects.requireNonNull(editAssistantButton);
        Objects.requireNonNull(removeAssistantButton);
        Objects.requireNonNull(assistantsTable);
        Objects.requireNonNull(assistantTypeColumn);
        Objects.requireNonNull(assistantNameColumn);
        Objects.requireNonNull(assistantValueColumn);
        Objects.requireNonNull(assistantSentColumn);
        Objects.requireNonNull(assistantPaymentSystemColumn);
        Objects.requireNonNull(assistantPaymentDateColumn);

        BooleanBinding noRequesterSelected  = requestersTable.getSelectionModel().selectedItemProperty().isNull();
        editRequesterButton.disableProperty().bind(noRequesterSelected);
        removeRequesterButton.disableProperty().bind(noRequesterSelected);

        addRequesterButton.setOnAction(this::onAddRequesterEvent);
        editRequesterButton.setOnAction(this::onEditRequesterEvent);
        removeRequesterButton.setOnAction(this::onRemoveRequesterEvent);

        requesterNameColumn.setCellValueFactory(r -> r.getValue().nameProperty());
        requesterValueColumn.setCellValueFactory(r -> r.getValue().valueProperty());
        requesterReceivedColumn.setCellValueFactory(r -> r.getValue().receivedProperty());
        requesterPaymentSystemColumn.setCellValueFactory(r -> r.getValue().paymentSystemProperty().asString());
        requesterPaymentDateColumn.setCellValueFactory(r -> r.getValue().paymentDateProperty().asString());

        BooleanBinding noAssistantSelected  = assistantsTable.getSelectionModel().selectedItemProperty().isNull();
        editAssistantButton.disableProperty().bind(noAssistantSelected);
        removeAssistantButton.disableProperty().bind(noAssistantSelected);

        addAssistantButton.setOnAction(this::onAddAssistantEvent);
        editAssistantButton.setOnAction(this::onEditAssistantEvent);
        removeAssistantButton.setOnAction(this::onRemoveAssistantEvent);

        assistantTypeColumn.setCellValueFactory(a -> a.getValue().typeProperty().asString());
        assistantNameColumn.setCellValueFactory(a -> a.getValue().nameProperty());
        assistantValueColumn.setCellValueFactory(a -> a.getValue().valueProperty());
        assistantSentColumn.setCellValueFactory(a -> a.getValue().sentProperty());
        assistantPaymentSystemColumn.setCellValueFactory(a -> a.getValue().paymentSystemProperty().asString());
        assistantPaymentDateColumn.setCellValueFactory(a -> a.getValue().paymentDateProperty().asString());
    }


    @Override
    public void setItem(Cover item) {
        super.setItem(item);

        animeInput.textProperty().bindBidirectional(item.animeProperty());
        songInput.textProperty().bindBidirectional(item.songProperty());
        publishedInput.selectedProperty().bindBidirectional(item.publishedProperty());

        requestersTable.setItems(item.getRequesters());
        assistantsTable.setItems(item.getAssistants());

        priceLabel.textProperty().bind(item.priceProperty().asString());
        receivedLabel.textProperty().bind(item.receivedProperty().asString());
        feeLabel.textProperty().bind(item.feeProperty().asString());
        sendLabel.textProperty().bind(item.sentProperty().asString());
    }

    private void onAddRequesterEvent(ActionEvent event) {
        editRequester(new Requester());
    }

    private void onEditRequesterEvent(ActionEvent event) {
        Requester requester = requestersTable.getSelectionModel().getSelectedItem();
        editRequester(requester);
    }

    private void onRemoveRequesterEvent(ActionEvent event) {
        Requester requester = requestersTable.getSelectionModel().getSelectedItem();
        getItem().getRequesters().remove(requester);
    }

    private void editRequester(Requester requester) {
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


    private void onAddAssistantEvent(ActionEvent event) {
        editAssistant(new Assistant());
    }

    private void onEditAssistantEvent(ActionEvent event) {
        Assistant assistant = assistantsTable.getSelectionModel().getSelectedItem();
        editAssistant(assistant);
    }

    private void onRemoveAssistantEvent(ActionEvent event) {
        Assistant assistant = assistantsTable.getSelectionModel().getSelectedItem();
        getItem().getAssistants().remove(assistant);
    }

    private void editAssistant(Assistant assistant) {
        final Assistant edtion = CloneUtil.deepClone(assistant);

        AssistantController controller = view
                .forController(AssistantController.class)
                .withTitle("Assistant")
                .beforeShow(rc -> rc.setItem(edtion))
                .withModality(root.getScene().getWindow())
                .showAndWait();

        if (controller.isSave()) {
            ListUtil.addOrUpdate(getItem().getAssistants(), edtion, assistant);
        }
    }
}
