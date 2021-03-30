package com.kpi.androidlabs2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InputFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    interface OnFragmentSendDataListener {
        void onSendResult(String result);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        Button calculateBtn = view.findViewById(R.id.calculate);
        calculateBtn.setOnClickListener(v -> {
            EditText firstNum = view.findViewById(R.id.firstNumber);
            EditText secondNum = view.findViewById(R.id.secondNumber);
            if (firstNum.getText().toString().isEmpty() || secondNum.getText().toString().isEmpty())
                message("Please, fill all text fields");
            else {
                RadioGroup operations = view.findViewById(R.id.operation);
                int operationId = operations.getCheckedRadioButtonId();
                int a = Integer.parseInt(firstNum.getText().toString());
                int b = Integer.parseInt(secondNum.getText().toString());
                if (operationId == -1) {
                    message( "Please, select an operation");
                } else {
                    String result = " "+ calculate(operationId, a, b);
                    fragmentSendDataListener.onSendResult(result);
                }
            }
        });
        return view;
    }

    private void message(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    private int calculate(int checkedId, int a, int b) {
        int result = 0;
        switch (checkedId) {
            case R.id.plus:
                result = a + b;
                break;
            case R.id.minus:
                result = a - b;
                break;
            case R.id.divide:
                if (b == 0) message("You can't divide by 0");
                else result = a / b;
                break;
            case R.id.multiply:
                result = a * b;
                break;
        }return result;
    }

}