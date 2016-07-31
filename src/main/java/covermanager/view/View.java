package covermanager.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

@Singleton
public class View {
    private final BeanManager beanManager;

    @Inject
    public View(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public <T> ControllerBuilder<T> forController(Class<T> controllerClass) {
        return new ControllerBuilder<T>(controllerClass);
    }

    public class ControllerBuilder<T> {
        private final Class<T> controllerClass;

        private FXMLLoader fxmlLoader;
        private Stage stage;
        private String title;

        public ControllerBuilder(Class<T> controllerClass) {
            this.controllerClass = controllerClass;
        }

        public FXMLLoader getFxmlLoader() {
            if (fxmlLoader == null) {
                String fxml = controllerClass.getSimpleName().replace("Controller", "View.fxml");
                URL location = controllerClass.getResource(fxml);
                Objects.requireNonNull(location, "FXML not found: " + fxml);

                fxmlLoader = new FXMLLoader(location);
                fxmlLoader.setControllerFactory(clazz -> {
                    if (!controllerClass.equals(clazz)) {
                        throw new RuntimeException("Expecting controller " + controllerClass + " but FXML specifies " + clazz);
                    }

                    Set<Bean<?>> beans = beanManager.getBeans(controllerClass);

                    if (beans.size() > 1) {
                        throw new RuntimeException("Ambiguous bean type: " + controllerClass);
                    }
                    if (beans.isEmpty()) {
                        throw new RuntimeException("No bean found: " + controllerClass);
                    }

                    Bean<?> bean = beans.iterator().next();
                    CreationalContext<?> creationalContext = beanManager.createCreationalContext(bean);
                    @SuppressWarnings("unchecked")
                    T controller = (T)beanManager.getReference(bean, controllerClass, creationalContext);

                    return controller;
                });

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return fxmlLoader;
        }

        public T getController() {
            return getFxmlLoader().getController();
        }

        public Parent getRoot() {
            return getFxmlLoader().getRoot();
        }

        public ControllerBuilder<T> onStage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public ControllerBuilder<T> withTitle(String title) {
            this.title = title;
            return this;
        }

        public ControllerBuilder<T> withController(Consumer<T> consumer) {
            consumer.accept(getController());
            return this;
        }

        public T showAndWait() {
            if (stage == null) {
                stage = new Stage();
            }
            if (title != null) {
                stage.setTitle(title);
            }

            stage.setScene(new Scene(getRoot()));
            stage.sizeToScene();
            stage.showAndWait();

            return getController();
        }
    }
}
