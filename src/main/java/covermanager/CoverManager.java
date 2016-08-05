package covermanager;

import covermanager.view.View;
import covermanager.view.workspace.WorkspaceController;
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

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WeldContainer container = weld.initialize();
        View view = container.select(View.class).get();

        view.forController(WorkspaceController.class)
                .withTitle("CoverManager")
                .showAndWait();
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
