package covermanager;

import covermanager.domain.Cover;

public class DataProducer {
    public Data produceData() {
        Data result =  new Data();
        Cover cover = new Cover();
        cover.setAnime("Sailor Moon");
        cover.setSong("Gangnam Style");
        result.getCovers().add(cover);

        return result;
    }
}
