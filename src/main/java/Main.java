import com.mongodb.client.FindIterable;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoCon mongoCon = new MongoCon("localhost", 27017, "mydb", "test");
        TwitterCon twitterCon = new TwitterCon();

        mongoCon.fuegeSampleDokumentHinzu();
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
        twitterCon.showHomeTimeline();
        System.out.println("---");
        twitterCon.updateTweet("Naddel die Maus");
        twitterCon.getUserTimeline("kl_0tz");








    }
}
