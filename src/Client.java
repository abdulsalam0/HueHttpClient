import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;

public class Client {
  OkHttpClient client = new OkHttpClient();
  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  String token = "";

  String run(String url) throws IOException {
    Request request = new Request.Builder()
            .url(url)
            .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
  // making a post request
  String post(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(json, JSON);
    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Accept", "application/json")
            .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Making requests...");
    // client object
    Client client = new Client();

    // Urls
    String BRIDGE_IP = "192.168.0.2";
    String getUsername = "http://"+BRIDGE_IP+"/api";

    //Json Data String
    String LoginData = ("{\"devicetype\":\"Team_C\"}");
    String TurnOnLights = ("{\"on\":true}");


/*    String postResponseToken = client.post(URLCreateAccount,loginJson, "");
    System.out.println(postResponseToken);*/

    //Getting the Your_User_ID
    String YOUR_USERNAME = client.post(getUsername,LoginData);
    System.out.println("Your Username: "+ YOUR_USERNAME);

    //Getting the lights information
    String getLightsIDs = "http://"+BRIDGE_IP+"/api/"+YOUR_USERNAME+"/lights";
    String Response_LIGHTS_STATE = client.run(getLightsIDs);
    System.out.println("Lights information :\n"+Response_LIGHTS_STATE);

    // link for lamp 1 and Turning on the lights
    String lightOnURL = "http://"+BRIDGE_IP+"/api/"+YOUR_USERNAME+"/lights/1/state";
    String Response_lightsON = client.post(lightOnURL,TurnOnLights);
    System.out.println("Response for Turing on the lights: "+ Response_lightsON);


    ////////////////////////////////////////////////////////////////////////////////////

    // This function needs to be sent for each angle update

    String Response_


  }
}