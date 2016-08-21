package covermanager.view.edit;

import covermanager.util.ComponentUtil;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class EditFooter extends HBox {
    @FXML
    protected Button saveButton;
    @FXML
    protected Button cancelButton;

    public EditFooter() {
        ComponentUtil.construct(this);
    }

    @FXML
    protected void initialize() {
        Objects.requireNonNull(saveButton);
        Objects.requireNonNull(cancelButton);
    }

    public EventHandler<ActionEvent> getOnSave() {
        return saveButton.getOnAction();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onSaveProperty() {
        return saveButton.onActionProperty();
    }

    public void setOnSave(EventHandler<ActionEvent> onSave) {
        saveButton.setOnAction(onSave);
    }

    public EventHandler<ActionEvent> getOnCancel() {
        return cancelButton.getOnAction();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onCancelProperty() {
        return cancelButton.onActionProperty();
    }

    public void setOnCancel(EventHandler<ActionEvent> onCancel) {
        cancelButton.setOnAction(onCancel);
    }
}
