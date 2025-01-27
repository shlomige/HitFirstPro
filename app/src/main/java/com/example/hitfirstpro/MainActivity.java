package com.example.hitfirstpro;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private int first_num = Integer.MIN_VALUE;
    private int second_num = Integer.MIN_VALUE;
    private String operator = "";
    private int res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result = findViewById(R.id.textView);
        result.setText("");

    }

    public void numfunc(View view) {
        Button button = (Button) view;
        result.append(button.getText().toString());

    }
    public void clear_second_num(View view){
        first_num = Integer.parseInt(result.getText().toString());
        second_num = Integer.MIN_VALUE;
        operator = "";

    }
    public void operatorfunc (View view){
        Button button = (Button) view;
        String operator_that_was_clicked = button.getText().toString();
        if (!operator_that_was_clicked.equals("=")){
            if (!operator.isEmpty()){
                result.setText("");
                result.setHint("error 2 operators in a row");
                clear_numbers_and_operator();
            }
            else {
                try {
                    first_num = Integer.parseInt(result.getText().toString());
                    result.append(button.getText().toString());
                    operator = operator_that_was_clicked;
                }catch (NumberFormatException e){
                    result.setText("");
                    result.setHint("error can't get first number");
                    clear_numbers_and_operator();
                    return;
                }

            }
        }
        if (operator_that_was_clicked.equals("=")){
            if(!operator.isEmpty()){
                try {
                    // Split the input and parse the numbers
                    String[] parts = result.getText().toString().split(Pattern.quote(operator));
                    first_num = Integer.parseInt(parts[0]); // Parse first number
                    second_num = Integer.parseInt(parts[1]); // Parse second number
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    result.setText("");
                    result.setHint("error not enough number");
                    clear_numbers_and_operator();
                    return;
                }
            }
            if (first_num != Integer.MIN_VALUE && second_num != Integer.MIN_VALUE){
                switch (operator){
                    case "+":
                        res = first_num+second_num;
                        break;
                    case "*":
                        res = first_num*second_num;
                        break;
                    case "-":
                        res = first_num - second_num;
                        break;
                    case"/":
                        if (second_num != 0){
                            res = first_num / second_num;
                        }
                            break;

                }
                if (operator.equals("/") && second_num == 0){
                    result.setText("");
                    result.setHint("can't be divided by zero");
                    clear_numbers_and_operator();
                }
                else {
                    result.setText(String.valueOf(res));
                    clear_second_num(view);
                }

            }
            else{
                result.setText("");
                result.setHint("error");
                clear_numbers_and_operator();
            }

        }
        //Log.d(result.getText().toString(),result.getText().toString());
        //Log.d(operator,operator);
    }
    public void clear_all(View view){
        clear_numbers_and_operator();
        result.setText("");
        result.setHint("Enter some numbers");
    }
    public void clear_numbers_and_operator(){
        first_num = Integer.MIN_VALUE;
        second_num = Integer.MIN_VALUE;
        operator = "";

    }
}