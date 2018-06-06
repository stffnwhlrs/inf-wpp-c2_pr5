import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TwitterCon {
    static final String CONSUMER_KEY = "1eVO4O5mvxFtj37urhNdmruct";
    static final String CONSUMER_SECRET = "bMaFZPJi7QWfSDtQh7WcoAquUGE7pweAm4IaA5aFZq4PMDrdtl";
    static final String ACCESS_TOKEN = "929886734-c0qNhrKHpvCEUVeVIOBGqfW65XsAUfSvsW9KXfIs";
    static final String ACCESS_TOKEN_SECRET = "inB99KEfmS6a0UVc7szIlz4gIFy9Fm39ynTCdZsvMfvXJ";


    public static void main(String[] args) {
        Twitter instance = getTwitterInstance();
        // showHomeTimeline(instance);
        // updateTweet(instance, "Nadja ist ein Hoehlenmensch");
        getUserTimeline(instance,"RealDonad_Trump");

    }


    public static Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }


    private static void showHomeTimeline(Twitter twitter) {

        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();

            System.out.println("Showing home timeline.");

            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":" + status.getText());
                String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/"
                        + status.getId();
                System.out.println("Above tweet URL : " + url);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    private static void updateTweet(Twitter twitter, String tweet) {
        try {
            Status status = twitter.updateStatus(tweet);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


    private static void getUserTimeline(Twitter twitter, String user) {
        try{
            List<Status> statuses = twitter.getUserTimeline(user);

            for (Status status : statuses) {
                String fmt = "@" + status.getUser().getScreenName() + " - " + status.getText();
                System.out.println(fmt);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
