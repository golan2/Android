package com.example.IntentHelloWorld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    30/03/2015 10:42
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class SimpleDbCreator extends SQLiteOpenHelper {

         static final int    PRODUCT_VERSION = 1;
         static final String DB_NAME         = "izik_database";
  public static final String COL_DATE        = "activitydate";
  public static final String COL_TYPE        = "type";
  public static final String COL_ID          = "_id";
  public static final String TBL_ACTIVITIES  = "activities";

  public SimpleDbCreator(Context context) {
    super(context, DB_NAME, null, PRODUCT_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    //here we create a DB for the first time

    //try {
      db.execSQL("CREATE TABLE "+ TBL_ACTIVITIES +" ("+ COL_ID +" integer primary key autoincrement, "+ COL_TYPE +" text not null, "+ COL_DATE +" integer not null)");
    //} catch (SQLException e) {
    //  e.printStackTrace(new PrintStream(new ByteArrayOutputStream()));
      //e.printStackTrace();
    //}
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //this method will be called if DB already exists and schema/data upgrade is needed
  }
}
