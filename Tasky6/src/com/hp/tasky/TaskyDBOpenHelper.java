package com.hp.tasky;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.golan.utils.MyLog;

public class TaskyDBOpenHelper extends SQLiteOpenHelper {

	public TaskyDBOpenHelper(Context context) {
		super(context, "tasks_db", null, 2);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		MyLog.log(MyLog.LogLevel.DEBUG, "onCreate ~ db=[" + db.getPath() + "]");
		db.execSQL("CREATE TABLE tasks (_id integer primary key autoincrement, title text, completed integer, dueDate integer);");
    db.execSQL("INSERT INTO tasks values ('Izik', 0, 231552000"    );
    db.execSQL("INSERT INTO tasks values ('Liat', 0, 259286400"    );
    db.execSQL("INSERT INTO tasks values ('Shaked', 0, 1109203200" );
    db.execSQL("INSERT INTO tasks values ('Yonatan', 0, 1194912000");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MyLog.log(MyLog.LogLevel.DEBUG, "onUpgrade ~ db=[" + db.getPath() + "] oldVersion=[" + oldVersion + "] newVersion=[" + newVersion + "]");

		final Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='table_name';", null);
		if (cursor.moveToFirst()) {
			MyLog.log(MyLog.LogLevel.DEBUG, " ********* TABLE Exists");
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		MyLog.log(MyLog.LogLevel.DEBUG, "onOpen ~ db=[" + db.getPath() + "]");
	}
}
