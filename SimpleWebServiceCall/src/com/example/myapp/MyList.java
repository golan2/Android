package com.example.myapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.Arrays;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    12/03/2015 17:27
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MyList extends ListActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_list);

    MyLog.log(MyLog.LogLevel.INFO, "onCreate - BEGIN");
    String[] values = getResources().getStringArray(R.array.profiles);
    MyLog.log(MyLog.LogLevel.INFO, "values="+ Arrays.toString(values));


    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, values);
    setListAdapter(adapter);




  }

}