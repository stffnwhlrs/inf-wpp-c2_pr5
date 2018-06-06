import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class EinfacheMongoCon {

    public static void main(String[] args) {

        // Verbindung herstellen
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("mydb");


        //Dokument erstellen
        Document doc = new Document("name", "steffen")
                .append("alter", 24)
                .append("lieblingsfarben", Arrays.asList("schwarz", "weiss"))
                .append("adresse", new Document("strasse", "schenkendorfstraße 35").append("ort", "hamburg"));


        //  Ein Dokument hinzufügen
        collection.insertOne(doc);



    }
}
