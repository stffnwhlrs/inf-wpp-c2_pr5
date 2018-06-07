import org.bson.Document;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

class Transformer {

    Transformer(){

    }

     Document statusZuDocument(Status status) {
        Document result = new Document("user", status.getUser().getScreenName())
                .append("text", status.getText())
                .append("datum", status.getCreatedAt())
                .append("quelle", "direkt");

        if(status.getGeoLocation() != null) {
            result.append("location",status.getGeoLocation().toString());
        }
        return result;
     }


     List<Document> statusListZuDocumentList(List<Status> status) {
        List<Document> result = new ArrayList<Document>();
        for (Status e : status) {
            result.add(statusZuDocument(e));
        }
        return result;
     }
}
