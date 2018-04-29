package com.example.myapp;

import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    13/03/2015 01:15
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 * AsyncTask that retrieves the temperature from the internet based on a given location
 * </pre>
 */
public class GetWeatherAsync extends AsyncTask<Location, Integer, String>  {

  private final MyActivity myActivity;

  public GetWeatherAsync(MyActivity myActivity) {this.myActivity = myActivity;}

  @Override
  protected String doInBackground(Location... locations) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
    String responseString = null;
    try {
      response = httpclient.execute(new HttpGet(getLocationServiceURL(locations[0])));
      StatusLine statusLine = response.getStatusLine();
      if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        responseString = out.toString();
        out.close();
      } else{
        //Closes the connection.
        response.getEntity().getContent().close();
        throw new IOException(statusLine.getReasonPhrase());
      }
    } catch (IOException e) {
      MyLog.log(e);
    }
    return responseString;
  }


  private String getLocationServiceURL(Location location) {
    if (location==null) return null;
    return "http://www.myweather2.com/developer/forecast.ashx?uac=v4llSsdbho&query="+location.getLatitude()+","+location.getLongitude()+"&temp_unit=c&output=json";
    //return "http://api.geonames.org/findNearByWeatherJSON?lat="+location.getLatitude()+"&lng="+location.getLongitude()+"&username=demo";
  }


  @Override
  protected void onPostExecute(String result) {
    super.onPostExecute(result);
    try {
      JSONObject json = new JSONObject(result);
      JSONObject weather = json.getJSONObject("weather");
      JSONArray curren_weather = weather.getJSONArray("curren_weather");
      JSONObject currentWeather = curren_weather.getJSONObject(0);
      int temperature = currentWeather.getInt("temp");
      MyLog.log(MyLog.LogLevel.INFO, "temperature=[" + temperature + "]");
      ((TextView)this.myActivity.findViewById(R.id.temperatureText)).setText(""+temperature);  //convert to String otherwise the "int" version of "setText" is called which assumes the parameter is a resource id

      //String value = status.getString("value");
      //MyLog.log(MyLog.LogLevel.INFO, "value=[" + value + "]");
      //((TextView)this.myActivity.findViewById(R.id.temperatureText)).setText(value);

    } catch (JSONException e) {
      MyLog.log(e);
    }
  }
}
