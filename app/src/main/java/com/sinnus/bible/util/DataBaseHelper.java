package com.sinnus.bible.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by sinnus on 2015/9/6.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static String db_path = "/data/data/com.sinnus.bible/databases/";
    private static String db_name = "bible.db";
    private SQLiteDatabase database;
    private Context context;

    public DataBaseHelper(Context context) {
        super(context, db_name, null, 1);
        this.context = context;
    }

    public void createDatabase() {
        boolean dbExist = checkDataBase();
        if (dbExist) {

        } else {
            try {
                this.copyDatabase();
            } catch (IOException e) {
                throw new Error("error in copying database");
            }
        }

    }

    private boolean checkDataBase() {
        SQLiteDatabase db = null;
        try {
            String dbPath = db_path + db_name;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException a) {
            throw new Error("databas none");
        }
        if (db != null) {
            db.close();
        }
        return db == null ? false : true;
    }

    private void copyDatabase() throws IOException {
//        InputStream inputStream = context.getResources().openRawResource(R.raw.bible);
//        String dbName = db_path + db_name;
//        FileOutputStream fileOutputStream = new FileOutputStream(dbName);
//        byte[] buf = new byte[1024];
//        int length;
//        while ((length = inputStream.read(buf)) > 0) {
//            fileOutputStream.write(buf, 0, length);
//        }
//        inputStream.close();
//        fileOutputStream.flush();
//        fileOutputStream.close();
    }

    public SQLiteDatabase openDatabase() {
        String dbName = db_path + db_name;
        this.database =
                SQLiteDatabase.openDatabase(dbName, null, SQLiteDatabase.OPEN_READONLY);
        return database;
    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
