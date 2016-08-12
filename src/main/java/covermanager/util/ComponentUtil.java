package covermanager.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class ComponentUtil {
    private ComponentUtil() {}

    public static void construct(Parent component) {
        String fxml = component.getClass().getSimpleName() + ".fxml";
        URL location = component.getClass().getResource(fxml);
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(component);
        loader.setRoot(component);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
