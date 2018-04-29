package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    18/03/2015 11:53
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MenuExample extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_example_layout);




  }


  ////////////////////////////////////////////////////////////////////////////////////
  // For Activity Menu


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menus, menu);
    return true;
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    MyLog.log(MyLog.LogLevel.INFO, "onMenuItemSelected item=["+item.getTitle()+"]");
    if (R.id.mnuOpenListOfColors == item.getItemId()) {
      MyLog.log(MyLog.LogLevel.INFO, "startActivity: ListOfColors");
      startActivity(new Intent(this, ListOfColors.class));
    }
    return super.onMenuItemSelected(featureId, item);
  }



}