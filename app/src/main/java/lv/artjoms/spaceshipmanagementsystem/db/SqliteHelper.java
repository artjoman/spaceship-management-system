package lv.artjoms.spaceshipmanagementsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SqliteHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "spaceships.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SpaceshipEntry.TABLE_NAME + " (" +
                    SpaceshipEntry._ID + " INTEGER PRIMARY KEY," +
                    SpaceshipEntry.COLUMN_NAME_TITLE + " TEXT," +
                    SpaceshipEntry.COLUMN_NAME_MASS + " TEXT," +
                    SpaceshipEntry.COLUMN_NAME_COLOR + " TEXT," +
                    SpaceshipEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    SpaceshipEntry.COLUMN_NAME_IMAGE_URL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SpaceshipEntry.TABLE_NAME;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    /* Inner class that defines the table contents */
    public static class SpaceshipEntry implements BaseColumns {
        public static final String TABLE_NAME = "spaceships";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_MASS = "mass";
        public static final String COLUMN_NAME_COLOR = "color";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_IMAGE_URL = "image";
    }
}
