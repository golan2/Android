package com.example.IntentHelloWorld;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.SimpleCursorAdapter;

import java.util.Arrays;
import java.util.Random;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    30/03/2015 10:19
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MyPrefActivity extends PreferenceActivity {

  private static final String SQL_GET_ALL_RECORDS =
      "SELECT * " +
          "FROM " + SimpleDbCreator.TBL_ACTIVITIES + " " +
          "ORDER BY " + SimpleDbCreator.COL_TYPE;

  private SQLiteDatabase db;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //noinspection deprecation
    addPreferencesFromResource(R.xml.prefs);

    showTableContent();
    db.close();
  }


  private void showTableContent() {
    db = new SimpleDbCreator(this).getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(SimpleDbCreator.COL_TYPE, "type_"+new Random().nextInt(3));
    values.put(SimpleDbCreator.COL_DATE, new Random().nextInt(1050));
    db.insert(SimpleDbCreator.TBL_ACTIVITIES, null, values);

    System.out.println("=============================");
    final Cursor cursor = db.rawQuery(SQL_GET_ALL_RECORDS, null    );
    System.out.println(Arrays.toString(cursor.getColumnNames()));

    boolean hasNext = cursor.moveToFirst();
    while (hasNext) {
      final long iid = cursor.getLong(cursor.getColumnIndex(SimpleDbCreator.COL_ID));
      final long ddate = cursor.getLong(cursor.getColumnIndex(SimpleDbCreator.COL_DATE));
      final long ttype = cursor.getLong(cursor.getColumnIndex(SimpleDbCreator.COL_TYPE));
      System.out.println(iid+","+ttype+","+ddate);
      hasNext = cursor.moveToNext();
    }
    System.out.println("=============================");

    //SimpleCursorAdapter ==> adapter to populate <ListView> from DB
  }
}