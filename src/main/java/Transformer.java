import org.bson.Document;
import twitter4j.Status;

public class Transformer {

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
}
