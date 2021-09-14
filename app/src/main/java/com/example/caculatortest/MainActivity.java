package com.example.caculatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caculatortest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    boolean isOperatorClick = false;
    double resultNumber = 0;
    double inputNumber = 0;
    String operator = "=";
    String lastoperator = "＋";
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    public void numButtonClick(View view){
        if(isFirstInput){
            activityMainBinding.resultTextView.setText(view.getTag().toString());
            isFirstInput = false;
            if(operator.equals("=")){
                activityMainBinding.mathTextView.setText(null);
                isOperatorClick = false;
            }
        }else{
            if(activityMainBinding.resultTextView.getText().toString().equals("0")){
                Toast.makeText(this," 0으로 시작되는 숫자는 없습니다.",Toast.LENGTH_SHORT).show();
                isFirstInput = true;
            }else {
                activityMainBinding.resultTextView.append(view.getTag().toString());

            }
        }
    }

    public void allClearButtonClick(View v){
        activityMainBinding.resultTextView.setText("0");
        activityMainBinding.mathTextView.setText("");
        resultNumber = 0;
        operator = "=";
        isFirstInput = true;
        isOperatorClick = false;
    }

    public void pointButtonClick(View v){
        if(isFirstInput){
            activityMainBinding.resultTextView.setText("0" + v.getTag().toString());
            isFirstInput = false;
        }else{
            if(activityMainBinding.resultTextView.getText().toString().contains(".")){
                Toast.makeText(this,"이미 소숫점이 존재합니다.",Toast.LENGTH_SHORT).show();
            }else{
                activityMainBinding.resultTextView.append(v.getTag().toString());
            }
        }
    }

    public void operatorClick (View v){
        isOperatorClick = true;
        lastoperator = v.getTag().toString();
        if(isFirstInput){
            if(operator.equals("=")){
                operator = v.getTag().toString();
                resultNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());
                activityMainBinding.mathTextView.setText(resultNumber + " " + operator + " ");
            }else{
                operator = v.getTag().toString();
                String getMathText = activityMainBinding.mathTextView.getText().toString();
                String subString = getMathText.substring(0, getMathText.length() - 2);
                activityMainBinding.mathTextView.setText(subString);
                activityMainBinding.mathTextView.append(operator + " ");
            }

        }else {
            inputNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());

            resultNumber = calculator(resultNumber,inputNumber,operator);

            activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = v.getTag().toString();
            activityMainBinding.mathTextView.append(inputNumber + " " + operator + " ");
        }

    }


    public void equalsButtonClick (View v){
        if(isFirstInput){
            if(isOperatorClick){
                activityMainBinding.mathTextView.setText(resultNumber + " " + lastoperator + " " + inputNumber + " =");
                resultNumber = calculator(resultNumber,inputNumber,lastoperator);
                activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            } }else{
            inputNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());
            resultNumber = calculator(resultNumber,inputNumber,operator);
            activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = v.getTag().toString();
            activityMainBinding.mathTextView.append(inputNumber + " " + operator + " ");
        }

    }

    private double calculator(double resultNumber, double inputNumber, String operator) {
        switch (operator){
            case "=":
                resultNumber = inputNumber;
                break;
            case "＋":
                resultNumber = resultNumber + inputNumber;
                break;
            case "－":
                resultNumber = resultNumber - inputNumber;
                break;
            case "×":
                resultNumber = resultNumber * inputNumber;
                break;
            case "÷":
                resultNumber = resultNumber / inputNumber;
                break;
            default:
                Log.e("calculator", resultNumber + " " +inputNumber+ " "+ operator);
        }
        return resultNumber;
    }



}