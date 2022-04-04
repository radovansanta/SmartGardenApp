package com.example.smartgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ValveInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valve_info);

        TextInputEditText nameTextInput = findViewById(R.id.name_textview);
        TextInputEditText descriptionTextInput = findViewById(R.id.description_textview);


        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");


        nameTextInput.setText(name);
        descriptionTextInput.setText(description);

    }
}