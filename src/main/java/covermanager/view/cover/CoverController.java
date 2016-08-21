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
import covermanager.view.table.TableEdit;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    protected TextField sentLabel;
    @FXML
    protected TextArea commentInput;

    @FXML
    protected TableEdit<Requester> requestersTable;
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
    protected TableEdit<Assistant> assistantsTable;
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
        Objects.requireNonNull(songInput);
        Objects.requireNonNull(priceLabel);
        Objects.requireNonNull(receivedLabel);
        Objects.requireNonNull(feeLabel);
        Objects.requireNonNull(sentLabel);
        Objects.requireNonNull(commentInput);

        Objects.requireNonNull(requestersTable);
        Objects.requireNonNull(requesterNameColumn);
        Objects.requireNonNull(requesterValueColumn);
        Objects.requireNonNull(requesterReceivedColumn);
        Objects.requireNonNull(requesterPaymentSystemColumn);
        Objects.requireNonNull(requesterPaymentDateColumn);

        Objects.requireNonNull(assistantsTable);
        Objects.requireNonNull(assistantTypeColumn);
        Objects.requireNonNull(assistantNameColumn);
        Objects.requireNonNull(assistantValueColumn);
        Objects.requireNonNull(assistantSentColumn);
        Objects.requireNonNull(assistantPaymentSystemColumn);
        Objects.requireNonNull(assistantPaymentDateColumn);


        requesterNameColumn.setCellValueFactory(r -> r.getValue().nameProperty());
        requesterValueColumn.setCellValueFactory(r -> r.getValue().valueProperty());
        requesterReceivedColumn.setCellValueFactory(r -> r.getValue().receivedProperty());
        requesterPaymentSystemColumn.setCellValueFactory(r -> r.getValue().paymentSystemProperty().asString());
        requesterPaymentDateColumn.setCellValueFactory(r -> r.getValue().paymentDateProperty().asString());

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
        commentInput.textProperty().bindBidirectional(item.commentProperty());

        requestersTable.setItems(item.getRequesters());
        assistantsTable.setItems(item.getAssistants());

        priceLabel.textProperty().bind(item.priceProperty().asString());
        receivedLabel.textProperty().bind(item.receivedProperty().asString());
        feeLabel.textProperty().bind(item.feeProperty().asString());
        sentLabel.textProperty().bind(item.sentProperty().asString());
    }

    @FXML
    protected void onAddRequester() {
        editRequester(new Requester());
    }

    @FXML
    protected void onEditRequester() {
        Requester requester = requestersTable.getSelectedItem();
        editRequester(requester);
    }

    @FXML
    protected void onRemoveRequester() {
        Requester requester = requestersTable.getSelectedItem();
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


    @FXML
    protected void onAddAssistant() {
        editAssistant(new Assistant());
    }

    @FXML
    protected void onEditAssistant() {
        Assistant assistant = assistantsTable.getSelectedItem();
        editAssistant(assistant);
    }

    @FXML
    protected void onRemoveAssistant() {
        Assistant assistant = assistantsTable.getSelectedItem();
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
