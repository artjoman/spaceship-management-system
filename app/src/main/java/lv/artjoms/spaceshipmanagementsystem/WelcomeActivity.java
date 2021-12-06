package lv.artjoms.spaceshipmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lv.artjoms.spaceshipmanagementsystem.db.SqliteHelper;

public class WelcomeActivity extends AppCompatActivity {

    private ListView shipList;

    // Data management variables
    List<String> shipsFromDb = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SqliteHelper dbHelper = new SqliteHelper(this);


        // TODO: Move code to own methods

        String title = "Loud and Proud";
        String myColor = "LED";
        String mass = "100";
        String location = "Riga";
        String image = "";

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_TITLE, title);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_MASS, mass);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_COLOR, myColor);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_LOCATION, location);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_IMAGE_URL, image);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SqliteHelper.SpaceshipEntry.TABLE_NAME, null, values);

        SQLiteDatabase readDb = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                SqliteHelper.SpaceshipEntry.COLUMN_NAME_TITLE
        };

        Cursor cursor = db.query(
                SqliteHelper.SpaceshipEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            String myTitle = cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.SpaceshipEntry.COLUMN_NAME_TITLE));
            shipsFromDb.add(myTitle);
        }
        cursor.close();

        // Reference to the Layout
        shipList = (ListView) findViewById(R.id.shipList);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                shipsFromDb
        );

        shipList.setAdapter(arrayAdapter);

    }

    public void createSpaceship(View view) {
        Intent createNewSpaceship = new Intent(this, CreateOrEditSpaceship.class);
        startActivity(createNewSpaceship);
    }
}