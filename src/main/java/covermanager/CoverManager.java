package covermanager;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class CoverManager extends Application {
    private Weld weld;

    @Override
    public void init() throws Exception {
        super.init();
        weld = new Weld();
        WeldContainer container = weld.initialize();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        weld.shutdown();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
