package com.calculator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView inputTV,outputTV;

    MaterialButton button_clear,button_div,button_percentage,button_AC,button_multi,button_minus,button_plus,button_equal,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0,button_00,button_dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputTV = findViewById(R.id.inputTV);
        outputTV = findViewById(R.id.outputTV);


        assignID(button_0,R.id.button_0);
        assignID(button_00,R.id.button_00);
        assignID(button_1,R.id.button_1);
        assignID(button_2,R.id.button_2);
        assignID(button_3,R.id.button_3);
        assignID(button_4,R.id.button_4);
        assignID(button_5,R.id.button_5);
        assignID(button_6,R.id.button_6);
        assignID(button_7,R.id.button_7);
        assignID(button_8,R.id.button_8);
        assignID(button_9,R.id.button_9);
        assignID(button_AC,R.id.button_AC);
        assignID(button_percentage,R.id.button_percentage);
        assignID(button_clear,R.id.button_clear);
        assignID(button_div,R.id.button_div);
        assignID(button_multi,R.id.button_multi);
        assignID(button_minus,R.id.button_minus);
        assignID(button_plus,R.id.button_plus);
        assignID(button_dot,R.id.button_dot);
        assignID(button_equal,R.id.button_equal);




    }
    void assignID(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        inputTV.setText(buttonText);

    }
}