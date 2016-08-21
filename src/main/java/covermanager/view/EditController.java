package covermanager.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;

import java.util.Objects;

public abstract class EditController<T> {
    @FXML
    protected Parent root;

    private T item;
    private boolean save;

    @FXML
    protected void initialize() {
        Objects.requireNonNull(root);
    }

    public void save() {
        save = true;
        close();
    }

    public void cancel() {
        close();
    }

    protected void close() {
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
