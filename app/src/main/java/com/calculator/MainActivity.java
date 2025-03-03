package com.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inputTV, outputTV;
    MaterialButton button_clear, button_div, button_percentage, button_AC, button_multi, button_minus, button_plus, button_equal;
    MaterialButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_00, button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextViews
        inputTV = findViewById(R.id.inputTV);
        outputTV = findViewById(R.id.outputTV);

        // Initialize Buttons
        button_0 = findViewById(R.id.button_0);
        button_00 = findViewById(R.id.button_00);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_AC = findViewById(R.id.button_AC);
        button_percentage = findViewById(R.id.button_percentage);
        button_clear = findViewById(R.id.button_clear);
        button_div = findViewById(R.id.button_div);
        button_multi = findViewById(R.id.button_multi);
        button_minus = findViewById(R.id.button_minus);
        button_plus = findViewById(R.id.button_plus);
        button_dot = findViewById(R.id.button_dot);
        button_equal = findViewById(R.id.button_equal);

        // Set OnClickListener for all buttons
        setOnClickListeners();
    }

    // Helper method to set OnClickListener for buttons
    private void setOnClickListeners() {
        button_0.setOnClickListener(this);
        button_00.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_AC.setOnClickListener(this);
        button_percentage.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_multi.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_dot.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = inputTV.getText().toString();

        if (buttonText.equals("AC")) {
            // Clear all input and output
            inputTV.setText("");
            outputTV.setText("0");
            return;
        } else if (buttonText.equals("C")) {
            // Remove the last character
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else if (buttonText.equals("=")) {
            // Evaluate the expression and display the result
            String result = getResult(dataToCalculate);
            outputTV.setText(result);
            inputTV.setText(result); // Optional: Set the result as the new input
            return;
        } else if (buttonText.equals("%")) {
            // Handle percentage calculation
            if (!dataToCalculate.isEmpty()) {
                try {
                    // Evaluate the current expression
                    String currentResult = getResult(dataToCalculate);
                    double value = Double.parseDouble(currentResult);

                    // Calculate percentage (e.g., 10% of value)
                    double percentageValue = value / 100;
                    dataToCalculate = String.valueOf(percentageValue);

                    // Update the input and output
                    inputTV.setText(dataToCalculate);
                    outputTV.setText(dataToCalculate);
                    return;
                } catch (Exception e) {
                    outputTV.setText("Err");
                    return;
                }
            }
        } else {
            // Append the button text to the input
            dataToCalculate = dataToCalculate + buttonText;
        }

        // Update the input TextView
        inputTV.setText(dataToCalculate);

        // Evaluate and update the output TextView in real-time
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")) {
            outputTV.setText(finalResult);
        }
    }

    // Helper method to evaluate the expression using Rhino
    private String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1); // Disable optimization for better compatibility
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            // Remove trailing ".0" for integer results
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }

            return finalResult;
        } catch (Exception e) {
            return "Err"; // Return "Err" if evaluation fails
        } finally {
            Context.exit(); // Exit the Rhino context to avoid memory leaks
        }
    }
}