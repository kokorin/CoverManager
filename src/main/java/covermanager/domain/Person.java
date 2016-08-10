package covermanager.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Person {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty skype = new SimpleStringProperty();
    private final StringProperty vk = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSkype() {
        return skype.get();
    }

    public StringProperty skypeProperty() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype.set(skype);
    }

    public String getVk() {
        return vk.get();
    }

    public StringProperty vkProperty() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk.set(vk);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
}
