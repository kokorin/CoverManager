package covermanager;

import covermanager.domain.Cover;
import covermanager.domain.Requester;

import java.time.LocalDate;

public class DataProducer {
    public Data produceData() {
        Data result =  new Data();

        Cover cover = new Cover();
        cover.setAnime("Sailor Moon");
        cover.setSong("Gangnam Style");
        result.getCovers().add(cover);

        Requester requester = new Requester();
        requester.setName("Psy");
        requester.setValue(100);
        requester.setReceived(70);
        requester.setPaymentDate(LocalDate.of(2016, 6, 24));

        cover.getRequesters().add(requester);


        return result;
    }
}
