package covermanager.view.table;

import covermanager.util.ComponentUtil;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class TableEdit<T> extends VBox {
    @FXML
    protected Button addButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button removeButton;
    @FXML
    protected TableView<T> table;

    public TableEdit() {
        ComponentUtil.construct(this);
    }

    @FXML
    protected void initialize() {
        Objects.requireNonNull(addButton);
        Objects.requireNonNull(editButton);
        Objects.requireNonNull(removeButton);
        Objects.requireNonNull(table);

        BooleanBinding noItemSelected  = table.getSelectionModel().selectedItemProperty().isNull();
        editButton.disableProperty().bind(noItemSelected);
        removeButton.disableProperty().bind(noItemSelected);
    }

    public ObservableList<TableColumn<T, ?>> getColumns() {
        return table.getColumns();
    }

    public ObservableList<T> getItems() {
        return table.getItems();
    }

    public ObjectProperty<ObservableList<T>> itemsProperty() {
        return table.itemsProperty();
    }

    public void setItems(ObservableList<T> items) {
        table.setItems(items);
    }

    public T getSelectedItem() {
        return table.getSelectionModel().getSelectedItem();
    }

    public ReadOnlyProperty<T> selectedItemProperty() {
        return table.getSelectionModel().selectedItemProperty();
    }

    public void select(T item) {
        table.getSelectionModel().select(item);
    }

    public EventHandler<ActionEvent> getOnAdd() {
        return addButton.getOnAction();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddProperty() {
        return addButton.onActionProperty();
    }

    public void setOnAdd(EventHandler<ActionEvent> onAdd) {
        addButton.setOnAction(onAdd);
    }

    public EventHandler<ActionEvent> getOnEdit() {
        return editButton.getOnAction();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddEdit() {
        return editButton.onActionProperty();
    }

    public void setOnEdit(EventHandler<ActionEvent> onAdd) {
        editButton.setOnAction(onAdd);
    }

    public EventHandler<ActionEvent> getOnRemove() {
        return removeButton.getOnAction();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddRemove() {
        return removeButton.onActionProperty();
    }

    public void setOnRemove(EventHandler<ActionEvent> onAdd) {
        removeButton.setOnAction(onAdd);
    }
}
