import com.mongodb.client.FindIterable;
import org.bson.Document;
import twitter4j.Status;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MongoCon mongoCon = new MongoCon("localhost", 27017, "mydb", "test");
        TwitterCon twitterCon = new TwitterCon();
        Transformer transformer = new Transformer();


        //mongoCon.fuegeSampleDokumentHinzu();
        System.out.println("Erstes Dokument:");
        System.out.println(mongoCon.findeErstesDokument().toJson());
        System.out.println("---");
        System.out.println("findeEinDokumenteKV:");
        System.out.println(mongoCon.findeErstesDokumentKV("name", "steffen"));
        System.out.println("---");
        System.out.println("findeAlleDokumenteKV:");
        mongoCon.findeAlleDokumenteKV("name", "steffen");
        System.out.println("---");

        System.out.println("--- TWITTER ---");
        twitterCon.zeigeHomeTimelineAufConsole();
        System.out.println("---");
        twitterCon.updateTweet("Naddel macht was sie will");
        twitterCon.gibUserTimelineAufConsole("kl_0tz");

        System.out.println(("--- Twitter Mongo ---"));
        List<Status> status = twitterCon.gibUserTimeline("kl_0tz");
        List<Document> documents = transformer.statusListZuDocumentList(status);
        mongoCon.fuegeDokumenteDokumenteHinzu(documents);
        mongoCon.findeAlleDokumenteKV("user","kl_0tz");
        System.out.println("--- Voltextsuche ---");
        mongoCon.volltextsucheAufConsole("extase", false, false);
        System.out.println("--- finde alle Docs ---");
        System.out.println(mongoCon.findeAlleDokumenteKV2("name", "steffen"));



        mongoCon.schliesseVerbindung();









    }
}
