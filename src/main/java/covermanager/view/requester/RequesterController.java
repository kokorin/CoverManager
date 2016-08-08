package covermanager.view.requester;

import covermanager.domain.Requester;
import covermanager.view.EditController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RequesterController extends EditController<Requester> {
    @FXML
    public TextField nameInput;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void setItem(Requester item) {
        super.setItem(item);
    }
}
