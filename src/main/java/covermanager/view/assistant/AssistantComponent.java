package covermanager.view.assistant;

import covermanager.util.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class AssistantComponent extends GridPane {
    @FXML
    public TextField nameInput;
    @FXML
    public TextField skypeInput;
    @FXML
    public TextField vkInput;

    public AssistantComponent() {
        ComponentUtil.construct(this);
    }


}
