package com.kpi.androidlabs2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class OutputFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_output, container, false);
        Button saveBtn = view.findViewById(R.id.save);
        saveBtn.setOnClickListener(v -> saveResultToDB());
        Button historyBtn = view.findViewById(R.id.history);
        historyBtn.setOnClickListener(v -> showHistory());
        return view;
    }

    public void setSelectedItem(String result) {
        TextView view = getView().findViewById(R.id.result);
        view.setText(result);
    }

    private void message(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void saveResultToDB() {
        TextView textView = getView().findViewById(R.id.result);
        String result = textView.getText().toString();
        if (result.isEmpty()) {
            message("Please, firstly calculate the result");
        } else {
            int resultData = Integer.parseInt(result.replaceAll("\\s", ""));
            DBHelper dbHelper = new DBHelper(getContext());
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_RESULT, resultData);
            database.insert(DBHelper.RESULT_TABLE, null, values);
            message("Result " + result + " was successfully saved");
            dbHelper.close();
        }
    }

    public void showHistory() {
        Intent intent = new Intent(getActivity().getApplicationContext(), HistoryActivity.class);
        startActivity(intent);
    }
}