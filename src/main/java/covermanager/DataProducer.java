package covermanager;

import covermanager.domain.Cover;
import covermanager.domain.Data;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataProducer {
    private final Path dataPath;

    public DataProducer() {
        String homePath = System.getProperty("user.home");
        dataPath = Paths.get(homePath, "CoverManager", "Data.xml");
    }

    public Data produceData() {
        Data result = readData();


        result.getCovers().addListener(
                (ListChangeListener.Change<? extends Cover> c) -> saveData(result)
        );

        return result;
    }

    private Data readData() {
        if (!Files.exists(dataPath)) {
            return new Data();
        }

        try {
            Reader reader = Files.newBufferedReader(dataPath);
            return JAXB.unmarshal(reader, Data.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveData(Data data) {
        try {
            Files.createDirectories(dataPath.getParent());
            Writer writer = Files.newBufferedWriter(dataPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            JAXB.marshal(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
