package covermanager.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.util.Objects;

public abstract class EditController<T> {
    @FXML
    protected Parent root;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button cancelButton;

    private T item;
    private boolean save;

    @FXML
    protected void initialize() {
        Objects.requireNonNull(root);
        Objects.requireNonNull(saveButton);
        Objects.requireNonNull(cancelButton);


        saveButton.setOnAction(this::save);
        cancelButton.setOnAction(this::close);
    }

    protected void save(ActionEvent event) {
        save = true;
        close(event);
    }

    protected void close(ActionEvent event) {
        root.getScene().getWindow().hide();
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isSave() {
        return save;
    }
}
