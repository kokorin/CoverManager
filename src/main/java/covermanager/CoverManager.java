package covermanager;

import covermanager.view.View;
import covermanager.view.cover.CoverController;
import covermanager.view.requester.RequesterController;
import covermanager.view.workspace.WorkspaceController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.PicoContainer;

public class CoverManager extends Application {
    private PicoContainer container;

    @Override
    public void init() throws Exception {
        super.init();

        DefaultPicoContainer container = new DefaultPicoContainer();
        container.addComponent(container)
                .addComponent(View.class)
                .addComponent(WorkspaceController.class)
                .addComponent(CoverController.class)
                .addComponent(RequesterController.class)
                .addComponent(new DataProducer().produceData());

        this.container = container;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = container.getComponent(View.class);

        view.forController(WorkspaceController.class)
                .withTitle("CoverManager")
                .showAndWait();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
