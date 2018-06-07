import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

class MongoCon {

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


    void fuegeDokumentHinzu(Document dokument) {
        _collection.insertOne(dokument);
    }


    void fuegeDokumenteDokumenteHinzu(List<Document> dokumente){
        _collection.insertMany(dokumente);
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


    List<Document> findeAlleDokumenteKV2(String key, String value) {

         List<Document> result = _collection.find(eq(key, value)).into(new ArrayList<Document>());
        return  result;
    }


    List<Document> volltextsuche(String query, boolean caseSensitive, boolean diacriticSensitive) {
        List<Document> result = new ArrayList<Document>();
        try {
            MongoCursor<Document> cursor = null;
            cursor = _collection.find(new Document("$text", new Document("$search", query).append("$caseSensitive", caseSensitive).append("$diacriticSensitive", diacriticSensitive))).iterator();

            while (cursor.hasNext()) {
                Document document = cursor.next();
                result.add(document);
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    void volltextsucheAufConsole(String query, boolean caseSensitive, boolean diacriticSensitive) {
        List<Document> result = volltextsuche(query, caseSensitive, diacriticSensitive);

        for(Document d : result) {
            System.out.println(d.toJson());
        }

    }


    void schliesseVerbindung() {
        _client.close();
    }
}
