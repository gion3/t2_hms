package com.example.homos_cristian_cosmin_1101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPatientActivity extends AppCompatActivity {

    private EditText editTextPatientName, editTextExaminationCost;
    private CheckBox checkBoxInsurance;
    private Button buttonAddPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        editTextPatientName = findViewById(R.id.editTextPatientName);
        editTextExaminationCost = findViewById(R.id.editTextExaminationCost);
        checkBoxInsurance = findViewById(R.id.checkBoxInsurance);
        buttonAddPatient = findViewById(R.id.buttonAddPatient);

        buttonAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextPatientName.getText().toString();
                String costStr = editTextExaminationCost.getText().toString();

                if (name.isEmpty() || costStr.isEmpty()) {
                    Toast.makeText(AddPatientActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                float cost;
                try {
                    cost = Float.parseFloat(costStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddPatientActivity.this, "Invalid cost value", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cost < 0) {
                    Toast.makeText(AddPatientActivity.this, "Cost must be greater than or equal to 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean insurance = checkBoxInsurance.isChecked();
                Patient newPatient = new Patient(name, cost, insurance);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newPatient", newPatient);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}