package com.example.calculatetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button buttonCE,buttonDivide,buttonMultiply,buttonSubtract,button7,button8,button9,button4,button5,button6,buttonAdd,button1,button2,button3,button0,buttonDot,buttonEqual;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    // 显示的文本内容
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button_CE).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_divide).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_multiply).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_subtract).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_add).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_dot).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_equal).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_0).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_1).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_2).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_3).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_4).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_5).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_6).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_7).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_8).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button_9).setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v) {
        String inputText = ((TextView) v).getText().toString();
        int id = v.getId();
        if (id == R.id.button_CE) {
            clear();
        } else if (id == R.id.button_add || id == R.id.button_subtract || id == R.id.button_multiply || id == R.id.button_divide) {
            operator = inputText; // 运算符
            refreshText(showText + operator);
        } else if (id == R.id.button_equal) {
            // 加减乘除四则运算
            double calculate_result = calculateFour();
            refreshOperate(String.valueOf(calculate_result));
            refreshText(showText + "=" + calculate_result);
        } else {
            // 处理其他按钮，包括数字和小数点
            // 上次的运算结果已经出来了
            if (result.length() > 0 && operator.equals("")) {
                clear();
            }

            // 无运算符，则继续拼接第一个操作数
            if (operator.equals("")) {
                firstNum = firstNum + inputText;
            } else {
                // 有运算符，则继续拼接第二个操作数
                secondNum = secondNum + inputText;
            }
            // 整数不需要前面的0
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }
    }

    private double calculateFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "*":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }

    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    private void refreshText(String text) {
        showText = text;
        textView.setText(showText);
    }
}
