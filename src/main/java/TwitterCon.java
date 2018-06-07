import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

class TwitterCon {
    private static final String CONSUMER_KEY = "1eVO4O5mvxFtj37urhNdmruct";
    private static final String CONSUMER_SECRET = "bMaFZPJi7QWfSDtQh7WcoAquUGE7pweAm4IaA5aFZq4PMDrdtl";
    private static final String ACCESS_TOKEN = "929886734-c0qNhrKHpvCEUVeVIOBGqfW65XsAUfSvsW9KXfIs";
    private static final String ACCESS_TOKEN_SECRET = "inB99KEfmS6a0UVc7szIlz4gIFy9Fm39ynTCdZsvMfvXJ";

    private Twitter _twitter;


    TwitterCon() {
        _twitter = getTwitterInstance();
    }


    private Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }


    void showHomeTimeline() {
        List<Status> statuses;
        try {
            statuses = _twitter.getHomeTimeline();

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

    void updateTweet(String tweet) {
        try {
            Status status = _twitter.updateStatus(tweet);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


    void getUserTimeline(String user) {
        try{
            List<Status> statuses = _twitter.getUserTimeline(user);

            for (Status status : statuses) {
                String fmt = "@" + status.getUser().getScreenName() + " - " + status.getText();
                System.out.println(fmt);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
