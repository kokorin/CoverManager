package covermanager.util;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerTextFilter implements UnaryOperator<TextFormatter.Change> {
    private static final Pattern INTEGER_REGEX = Pattern.compile("\\d*");

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.isContentChange()) {
            String text = change.getControlNewText();
            Matcher matcher = INTEGER_REGEX.matcher(text);
            if (!matcher.matches()) {
                return null;
            }
        }

        return change;
    }
}
