import okhttp3.*;
import java.io.IOException;


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

  String put(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(json, JSON);
    Request request = new Request.Builder()
            .url(url)
            .put(body)
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
    // String BRIDGE_IP = "192.168.0.2";
    String BRIDGE_IP = "localhost:8000";
    String getUsername = "http://"+BRIDGE_IP+"/api";

    //Json Data String
    String LoginData = ("{\"devicetype\":\"Team_C\"}");
    String TurnOnLightsData = ("{\"on\":true}");

    //Needs testing
    String brightnessData = ("{\"bri\":254}");

    //Getting the Your_User_ID
/*    String YOUR_USERNAME = client.post(getUsername,LoginData);
    System.out.println("Your Username: "+ YOUR_USERNAME);

    //Getting the lights information
    String getLightsIDs = "http://"+BRIDGE_IP+"/api/"+YOUR_USERNAME+"/lights";
    String Response_LIGHTS_STATE = client.run(getLightsIDs);
    System.out.println("Lights information :\n"+Response_LIGHTS_STATE);*/

    // link for lamp 1 and Turning on the lights
    String lightOnURL = "http://"+BRIDGE_IP+"/api/"+"newdeveloper"+"/lights/1/state";
    String Response_lightsON = client.put(lightOnURL,brightnessData);
    System.out.println("Response for Turing on the lights: "+ Response_lightsON);



    ////////////////////////////////// Looping area ////////////////////////////////////////////

    // This Post Request need to be sent for each angle update
/*    String brightnessURL = "http://"+BRIDGE_IP+"/api/"+YOUR_USERNAME+"/lights/1/state";
    String Response_BRIGHTNESS = client.put(brightnessURL,brightnessData);
    System.out.println("Brightness response: " + Response_BRIGHTNESS);*/

    ////////////////////////////////////////////////////////////////////////////////////////////
  }
}