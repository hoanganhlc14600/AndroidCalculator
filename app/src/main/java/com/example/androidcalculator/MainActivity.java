package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inp;
    private TextView outp;
    private StringBuilder stringBuilder;
    private String operator;
    private boolean clearInp;
    private boolean preCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inp = findViewById(R.id.input);
        outp = findViewById(R.id.output);
        stringBuilder = new StringBuilder();
        operator = "";
        clearInp = false;
        preCalculate = false;
    }

    public void onClickDel(View view){
        if (stringBuilder.length() > 0 ) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            outp.setText(stringBuilder.toString());
        }
        if (stringBuilder.length() == 0) {
            stringBuilder = new StringBuilder("0");
            outp.setText(stringBuilder.toString());
        }
    }

    public void onClickC(View view) {
        stringBuilder = new StringBuilder();
        operator = "";
        clearInp = false;
        preCalculate = false;
        inp.setText("");
        outp.setText("0");
    }

    public void onClickCE(View view) {
        stringBuilder = new StringBuilder();
        outp.setText("0");
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }
    public void onClickChange(View view){
        String number = outp.getText().toString();

        if(number.charAt(0) != '-') {
            stringBuilder.insert(0, "-");
            outp.setText(stringBuilder.toString());
        }
        else {
            stringBuilder.delete(0,1);
            outp.setText(stringBuilder.toString());
        }
    }

    public void onClickEqual(View view) {
        int result = 0;
        if (operator.equals("+")) {
            result = Integer.parseInt(removeLastCharacter(inp.getText().toString())) + Integer.parseInt(outp.getText().toString());
        }
        if (operator.equals("-")) {
            result = Integer.parseInt(removeLastCharacter(inp.getText().toString())) - Integer.parseInt(outp.getText().toString());
        }
        if (operator.equals("×")) {
            result = Integer.parseInt(removeLastCharacter(inp.getText().toString())) * Integer.parseInt(outp.getText().toString());
        }
        if (operator.equals("÷")) {
            result = Integer.parseInt(removeLastCharacter(inp.getText().toString())) + Integer.parseInt(outp.getText().toString());
        }
        inp.append(stringBuilder.toString() + "=");
        outp.setText(Integer.toString(result));
        preCalculate = true;
    }

    public void onClickNum(View view) {
        if (!inp.getText().toString().isEmpty() && clearInp == true) {
            clearInp = false;
            stringBuilder = new StringBuilder();
        }
        if (inp.getText().toString().isEmpty() && preCalculate == true) {
            preCalculate = false;
            stringBuilder = new StringBuilder();
        }

        if (!inp.getText().toString().isEmpty() && preCalculate == true) {
            preCalculate = false;
            inp.setText("");
            stringBuilder = new StringBuilder();
        }
        stringBuilder.append(((Button)view).getText());
        outp.setText(stringBuilder.toString());
    }

    public void onClickOperator(View view) {
        if (inp.getText().toString().isEmpty()) {
            stringBuilder.append(((Button)view).getText());
            inp.setText(stringBuilder.toString());
            operator = ((Button)view).getText().toString();
            clearInp = true;
        } else {
            if (clearInp) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(((Button)view).getText());
                inp.setText(stringBuilder.toString());
                operator = ((Button)view).getText().toString();
            } else {
                onClickEqual(findViewById(R.id.equal));
                stringBuilder.append(((Button)view).getText());
                inp.setText(stringBuilder.toString());
                operator = ((Button)view).getText().toString();
                clearInp = true;
            }
        }
    }

}