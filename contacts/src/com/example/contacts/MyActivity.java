package com.example.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.golan.utils.MyLog;

public class MyActivity extends Activity {
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    MyLog.log(MyLog.LogLevel.DEBUG, "asdasdasasdasdasdasd;aslmdfasd;4l=nk ;flnisdg;flkadfng alifd ");
  }

  public void f() {
    ContentResolver cr = getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
        null, null, null, null);
    if (cur.getCount() > 0) {
      while (cur.moveToNext()) {
        String id = cur.getString(
            cur.getColumnIndex(ContactsContract.Contacts._ID));
        String name = cur.getString(
            cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
          //Query phone here.  Covered next
        }
      }
    }
  }
}
