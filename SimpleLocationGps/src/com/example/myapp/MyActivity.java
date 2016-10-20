package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * Get location from GPS
 * Update the location on 2 text boxes on the screen
 * Get the temperature from the internet for this location
 */
public class MyActivity extends Activity   {

  private OCL_btnOpenListActivity   oclBtnOpenListActivity   = new OCL_btnOpenListActivity  ();
  private OCL_btnRefreshGpsLocation oclBtnRefreshGpsLocation = new OCL_btnRefreshGpsLocation();
  private MyLocationListener        locationListener         = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);


    MyLog.log(MyLog.LogLevel.INFO, "onCreate - Begin");

    Location location = getLastKnownLocation();

    updateLocationInTexBoxes(location);

    updateWeatherOnScreen(location);


    findViewById(R.id.btnOpenListActivity).setOnClickListener(oclBtnOpenListActivity);

    findViewById(R.id.btnRefreshGpsLocation).setOnClickListener(oclBtnRefreshGpsLocation);

    MyLog.log(MyLog.LogLevel.INFO, "onCreate - End");
  }

  private void updateWeatherOnScreen(Location location) {
    String locationServiceURL = getLocationServiceURL(location);
    if (locationServiceURL!=null) {
      new GetWeatherAsync(this).execute(location);
    }
    else {
      ((TextView)findViewById(R.id.temperatureText)).setText("Failed to get location");
    }
  }

  /**
   * Communicate with the {@link android.content.Context#LOCATION_SERVICE} in order to get a {@link android.location.Location}
   *
   * For some reason the {@link android.location.LocationManager#getLastKnownLocation(String)} doesn't work well all the times:
   * The [getLastKnownLocation] will return null for all providers.
   * I found that by registering a listener (requestLocationUpdates) we somehow make the [getLastKnownLocation] return a correct location
   * @return
   */
  private Location getLastKnownLocation() {

    LocationManager lm = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

    //make sure we have a listener registered
    if (locationListener==null) {
      locationListener = new MyLocationListener();
    }
    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1000, locationListener);

    Location result = lm.getLastKnownLocation(lm.getBestProvider(new Criteria(), true));

    MyLog.log(MyLog.LogLevel.INFO, "getLastKnownLocation=(" + ( result==null ? "null" : result.getLatitude()+","+result.getLongitude() ) + ")");
    return result;
  }

  private void updateLocationInTexBoxes(Location location) {
    if (location ==null) {
      ((TextView)findViewById(R.id.latText)).setText("");
      ((TextView)findViewById(R.id.longText)).setText("");
    }
    else {
      ((TextView)findViewById(R.id.latText)).setText(""+String.format( "%.5f", location.getLatitude() ));
      ((TextView)findViewById(R.id.longText)).setText(""+String.format("%.5f", location.getLongitude()));
    }
  }

  @Deprecated
  private String getLocationServiceURL(Location location) {
    if (location==null) return null;
    return "http://api.geonames.org/findNearByWeatherJSON?lat="+location.getLatitude()+"&lng="+location.getLongitude()+"&username=demo";
  }

  private static class MyLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
      MyLog.log(MyLog.LogLevel.INFO, "["+hashCode()+"] onLocationChanged - location=(" + location.getLatitude() + "," + location.getLongitude() + ")");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
      MyLog.log(MyLog.LogLevel.INFO, "["+hashCode()+"] onStatusChanged - provider=["+provider+"] status=["+status+"] status=["+status+"] extras=["+extras+"]");
    }

    @Override
    public void onProviderEnabled(String provider) {
      MyLog.log(MyLog.LogLevel.INFO, "["+hashCode()+"] ["+hashCode()+"] onProviderEnabled - provider=["+provider+"]");
    }

    @Override
    public void onProviderDisabled(String provider) {
      MyLog.log(MyLog.LogLevel.INFO, "["+hashCode()+"] onProviderDisabled - provider=["+provider+"]");
    }
  }

  private class OCL_btnOpenListActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(MyActivity.this, MyList.class));
    }
  }

  private class OCL_btnRefreshGpsLocation implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      Location location = getLastKnownLocation();
      updateLocationInTexBoxes(location);
      updateWeatherOnScreen(location);
    }
  }
}