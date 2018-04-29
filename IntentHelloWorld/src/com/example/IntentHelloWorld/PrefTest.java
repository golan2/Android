package com.example.IntentHelloWorld;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Map;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    30/03/2015 10:04
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class PrefTest extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pref_test_layout);

    //final SharedPreferences defaultPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    final SharedPreferences prefs = getSharedPreferences("izik", MODE_PRIVATE);
    System.out.println("=============================");
    System.out.println("SharedPreferences");
    for (Map.Entry<String, ?> entry : prefs.getAll().entrySet()) {
      System.out.println(entry.getKey()+"="+entry.getValue());
    }
    System.out.println("=============================");

    final SharedPreferences.Editor editPrefs = prefs.edit();
    editPrefs.putInt("izik", 100);
    editPrefs.apply();
  }
}