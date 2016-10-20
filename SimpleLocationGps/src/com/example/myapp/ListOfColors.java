package com.example.myapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    18/03/2015 13:58
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class ListOfColors extends ListActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_of_colors_activity);

    String[] values = getResources().getStringArray(R.array.listColors);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, values);
    setListAdapter(adapter);

    getListView().setOnCreateContextMenuListener(this);
  }

  //////////////////////////////////////////////////////////////////////////////////
  //For Context Menu
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    MyLog.log(MyLog.LogLevel.INFO, "onCreateContextMenu");
    new MenuInflater(getApplicationContext()).inflate(R.menu.color_context_menu, menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    MyLog.log(MyLog.LogLevel.INFO, "onContextItemSelected item=["+item.getTitle()+"]");
    return super.onContextItemSelected(item);
  }
}