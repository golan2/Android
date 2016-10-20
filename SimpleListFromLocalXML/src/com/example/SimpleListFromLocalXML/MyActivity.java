package com.example.SimpleListFromLocalXML;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import org.json.JSONException;

import java.io.IOException;

public class MyActivity extends ListActivity {

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_layout);
    try {
      Configuration configuration = new Configuration();
      StringBuilder buf = new StringBuilder();
      for (Profile profile : configuration.getProfiles()) {
        buf.append(profile).append("\n");
      }

      MyLog.log(MyLog.LogLevel.DEBUG, buf.toString());

      setListAdapter(new ProfileArrayAdapter(this, configuration.getProfiles() ));

    } catch (IOException | JSONException e) {
      e.printStackTrace();
    }

  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {


    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
    dlgAlert.setMessage(getListAdapter().getItem(position).toString());
    dlgAlert.setTitle("Selected Profile:");
    dlgAlert.setPositiveButton("OK", null);
    dlgAlert.setCancelable(true);
    dlgAlert.create().show();
  }
}
