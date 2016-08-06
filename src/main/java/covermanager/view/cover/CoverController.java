package covermanager.view.cover;

import covermanager.domain.Cover;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Objects;

public class CoverController {
    @FXML
    protected Parent root;
    @FXML
    public TextField animeInput;
    @FXML
    public TextField songInput;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button cancelButton;

    private Cover cover;
    private boolean save;

    @FXML
    protected void initialize() {
        Objects.requireNonNull(root);
        Objects.requireNonNull(animeInput);
        Objects.requireNonNull(songInput);
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

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
        animeInput.textProperty().bindBidirectional(cover.animeProperty());
        songInput.textProperty().bindBidirectional(cover.songProperty());
    }

    public boolean isSave() {
        return save;
    }
}
