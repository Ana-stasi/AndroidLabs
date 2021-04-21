package com.kpi.androidlabs2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    TextView resultList;
    DBHelper databaseHelper;
    SQLiteDatabase database;
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(v -> {
            cursor.close();
            database.close();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
        resultList = (TextView) findViewById(R.id.list);
        databaseHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        database = databaseHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM " + DBHelper.RESULT_TABLE, null);
        if(cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(DBHelper.COLUMN_ID);
                int resultIndex = cursor.getColumnIndex(DBHelper.COLUMN_RESULT);
                String output = "Result № " + cursor.getInt(idIndex) + ": " + cursor.getInt(resultIndex);
                resultList.append(output);
            } while (cursor.moveToNext());
        } else{
            Toast.makeText(getApplicationContext(),"No saved results",Toast.LENGTH_SHORT).show();
        }
    }

}