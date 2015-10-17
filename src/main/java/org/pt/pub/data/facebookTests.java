package org.pt.pub.data;

import com.restfb.*;
import com.restfb.types.FacebookType;

import java.io.IOException;
import java.util.Date;


/**
 * Created by vitorfernandes on 10/17/15.
 */
public class facebookTests {
    public static final String ACCESS_TOKEN="CAACEdEose0cBALtrW8AgID5OsMLZBooVln5lKldtFkYBrpfJ6WkWlOk8rmnZClbCpgZCW5y4ZCnIgwOlYnMwsxDxo2XdkfJd484PHZAleZBe3SRZAzecYILue4xYw9SupDtyc1tRYg1DxIpQPVyX0hUghZAYGrRvmQPwfRMVQm4xjmDgCw3mplPJZAGOkOrhhBZCXYjZBwcJ0SU5ETGQpsQXqBI";
    public static final String APP_SECRET="1719871d76268f075ad0cafbb492c7fa";
    public static final String APP_ID="328110980599333";
    public static FacebookClient fbClient;
    public static void main(String[] args) throws IOException{

        FacebookClient.AccessToken userTokenT = getFacebookUserToken(APP_ID,"http://git.balhau.net");
        FacebookClient.AccessToken accessTokenT =
                new DefaultFacebookClient().obtainAppAccessToken(APP_ID, APP_SECRET);
        fbClient = new DefaultFacebookClient(accessTokenT.getAccessToken(),APP_SECRET,Version.VERSION_2_0);
        publishPostAndEvent();
    }

    private static FacebookClient.AccessToken getFacebookUserToken(String code, String redirectUrl) throws IOException {
        String appId = "YOUR_APP_ID";
        String secretKey = "YOUR_SECRET_KEY";

        WebRequestor wr = new DefaultWebRequestor();
        WebRequestor.Response accessTokenResponse = wr.executeGet(
                "https://graph.facebook.com/oauth/access_token?client_id=" + appId + "&redirect_uri=" + redirectUrl
                        + "&client_secret=" + secretKey + "&code=" + code);

        return DefaultFacebookClient.AccessToken.fromQueryString(accessTokenResponse.getBody());
    }

    private static void publishPostAndEvent(){

        FacebookType publishMessageResponse =
                fbClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", "Facebook message test..."));

        System.out.println("Published message ID: " + publishMessageResponse.getId());

// Publishing an event

        Date tomorrow = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L);
        Date twoDaysFromNow = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 48L);

        FacebookType publishEventResponse = fbClient.publish("me/events", FacebookType.class,
                Parameter.with("name", "Festarola"), Parameter.with("start_time", tomorrow),
                Parameter.with("end_time", twoDaysFromNow));

        System.out.println("Published event ID: " + publishEventResponse.getId());
    }
}
