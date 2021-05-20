package com.example.step_hw4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private boolean plus = true;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.txtDisplay);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateTextOnDisplay(String stringToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(stringToAdd);
            display.setSelection(cursorPos+1);
        }
        else{
            display.setText(String.format("%s%s%s",leftStr,stringToAdd,rightStr));
            display.setSelection(cursorPos+1);
        }

    }
    public void zeroBTN(View view){
        updateTextOnDisplay("0");
    }
    public void oneBTN(View view){
        updateTextOnDisplay("1");
    }
    public void twoBTN(View view){
        updateTextOnDisplay("2");
    }
    public void threeBTN(View view){
        updateTextOnDisplay("3");
    }
    public void fourBTN(View view){
        updateTextOnDisplay("4");
    }
    public void fiveBTN(View view){
        updateTextOnDisplay("5");
    }
    public void sixBTN(View view){
        updateTextOnDisplay("6");
    }
    public void sevenBTN(View view){
        updateTextOnDisplay("7");
    }
    public void eightBTN(View view){
        updateTextOnDisplay("8");
    }
    public void nineBTN(View view){
        updateTextOnDisplay("9");
    }

    public void clearBTN(View view){
        display.setText("");
    }
    public void parenthesesBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar+=1;
            }

            if(openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
                updateTextOnDisplay("(");
            }
            else if(closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
                updateTextOnDisplay(")");
            }
            display.setSelection(cursorPos+1);
        }
    }
    public void exponentBTN(View view){
        updateTextOnDisplay("^");
    }
    public void divideBTN(View view){
        updateTextOnDisplay("÷");
    }
    public void multiplyBTN(View view){
        updateTextOnDisplay("×");
    }
    public void substractBTN(View view){
        updateTextOnDisplay("-");
    }
    public void addBTN(View view){
        updateTextOnDisplay("+");
    }
    public void plusMinusBTN(View view){
        String userExp = (String)display.getText().toString();

        if(plus){

                String result = "-";
                for (int i=0; i<userExp.length();i++){
                    result+=userExp.charAt(i);
                }
                display.setText(result);
                display.setSelection(result.length());

        }
        if(!plus){
                String result = "";
                for (int i=1; i<userExp.length();i++){
                    result+=userExp.charAt(i);
                }
                display.setText(result);
                display.setSelection(result.length());

        }
        plus=!plus;
    }
    public void pointBTN(View view){
        updateTextOnDisplay(".");
    }
    public void equalsBTN(View view){
        String userExp = display.getText().toString();

        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
    public void backspaceBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder)display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);

        }
    }
}