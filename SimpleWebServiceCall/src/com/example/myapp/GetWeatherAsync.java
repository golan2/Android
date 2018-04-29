package com.example.myapp;

import android.os.AsyncTask;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
 *
 * </pre>
 */
public class GetWeatherAsync extends AsyncTask<String, String, String>  {

  private final MyActivity myActivity;

  public GetWeatherAsync(MyActivity myActivity) {this.myActivity = myActivity;}

  @Override
  protected String doInBackground(String... uri) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
    String responseString = null;
    try {
      response = httpclient.execute(new HttpGet(uri[0]));
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

  @Override
  protected void onPostExecute(String result) {
    super.onPostExecute(result);
    try {
      JSONObject json = new JSONObject(result);
      JSONObject status = json.getJSONObject("status");
      String value = status.getString("value");
      MyLog.log(MyLog.LogLevel.INFO, "value=[" + value + "]");
      ((TextView)this.myActivity.findViewById(R.id.temperatureText)).setText(value);

    } catch (JSONException e) {
      MyLog.log(e);
    }
  }
}
