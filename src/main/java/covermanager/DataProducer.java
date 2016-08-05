package covermanager;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class DataProducer {
    @Produces
    @Singleton
    public Data produceData() {
        return new Data();
    }
}
