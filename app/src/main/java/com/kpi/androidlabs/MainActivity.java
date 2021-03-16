package com.kpi.androidlabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.submit).setOnClickListener(v -> onSubmitClick());
    }

    private void onSubmitClick() {
        EditText firstNum = findViewById(R.id.firstNumber);
        EditText secondNum = findViewById(R.id.secondNumber);
        RadioGroup operation = findViewById(R.id.operation);
        int operationId = operation.getCheckedRadioButtonId();
        if (firstNum.getText().toString().isEmpty() || secondNum.getText().toString().isEmpty())
            message("Please, fill all text fields");
        else {
            int a = Integer.parseInt(firstNum.getText().toString());
            int b = Integer.parseInt(secondNum.getText().toString());
            if (operationId == -1) {
                message( "Please, select an operation");
            } else {
                TextView result = findViewById(R.id.result);
                result.setText("Result: "+ calculate(operationId, a, b));
            }
        }
    }

    private void message( String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
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