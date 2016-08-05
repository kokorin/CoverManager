package covermanager.view.cover;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoverController {
    @FXML
    protected Button saveButton;
    @FXML
    protected Button cancelButton;

    @FXML
    protected void initialized() {

    }
}
