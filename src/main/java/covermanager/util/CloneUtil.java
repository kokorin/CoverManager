package covermanager.util;

import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;

public class CloneUtil {
    private CloneUtil() {}

    public static <T> T deepClone(T item) {
        StringWriter writer = new StringWriter();
        JAXB.marshal(item, writer);
        StringReader reader = new StringReader(writer.toString());
        @SuppressWarnings("unchecked")
        T result = (T) JAXB.unmarshal(reader, item.getClass());
        return result;
    }
}
