package com.kpi.androidlabs2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OutputFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_output, container, false);
    }

    public void setSelectedItem(String result) {
        TextView view =  getView().findViewById(R.id.result);
        view.setText(result);
    }
}