import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class MongoCon {

    private MongoClient _client;
    private MongoDatabase _database;
    private MongoCollection<Document> _collection;


    MongoCon(String host, int port, String database, String collection) {
        _client = new MongoClient(host, port);
        _database = _client.getDatabase(database);
        _collection = _database.getCollection(collection);
    }


    void fuegeSampleDokumentHinzu() {
        Document doc = new Document("name", "steffen")
                .append("alter", 24)
                .append("lieblingsfarben", Arrays.asList("schwarz", "weiss"))
                .append("adresse", new Document("strasse", "schenkendorfstraße 35").append("ort", "hamburg").append("plz", 22085));

        _collection.insertOne(doc);
    }


    Document findeErstesDokument() {
        Document result = _collection.find().first();
        return result;
    }


    Document findeErstesDokumentKV(String key, String value) {
        Document result = _collection.find(eq(key, value)).first();
        return result;
    }

    void findeAlleDokumenteKV(String key, String value) {
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        _collection.find(eq(key, value)).forEach(printBlock);
    }


}
