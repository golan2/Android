package com.example.IntentHelloWorld;

import android.app.Activity;
import android.os.Bundle;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    30/03/2015 11:51
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class SqlLiteTableAdapterExample extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sql_lite_table_adapter_example);

    //[1] new SimpleDbCreator(this).getWritableDatabase();
    //[2] SqlLiteTableAdapterExample
  }
}