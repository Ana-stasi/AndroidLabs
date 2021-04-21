package com.kpi.androidlabs2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentSendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSendResult(String result) {
        OutputFragment fragment = (OutputFragment) getSupportFragmentManager().findFragmentById(R.id.fr_output);
        if (fragment != null) {
            fragment.setSelectedItem(result);
        }

    }

}