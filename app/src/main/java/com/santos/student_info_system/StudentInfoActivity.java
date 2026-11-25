package com.santos.student_info_system;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class StudentInfoActivity extends AppCompatActivity {

    private TextInputLayout tilStudentNumber, tilFirstName, tilMiddleName, tilLastName, tilProgram, tilYearLevel;
    private TextInputEditText etStudentNumber, etFirstName, etMiddleName, etLastName;
    private AutoCompleteTextView actProgram, actYearLevel;
    private MaterialButton btnSave, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_student_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupDropdowns();
        setupListeners();
    }

    private void initViews() {
        tilStudentNumber = findViewById(R.id.tilStudentNumber);
        tilFirstName = findViewById(R.id.tilFirstName);
        tilMiddleName = findViewById(R.id.tilMiddleName);
        tilLastName = findViewById(R.id.tilLastName);
        tilProgram = findViewById(R.id.tilProgram);
        tilYearLevel = findViewById(R.id.tilYearLevel);

        etStudentNumber = findViewById(R.id.etStudentNumber);
        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);

        actProgram = findViewById(R.id.actProgram);
        actYearLevel = findViewById(R.id.actYearLevel);

        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);
    }

    private void setupDropdowns() {
        // Programs Dropdown
        String[] programs = new String[]{
                getString(R.string.program_digital_forensics),
                getString(R.string.program_machine_learning)
        };
        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, programs);
        actProgram.setAdapter(programAdapter);

        // Year Levels Dropdown (1st year to 4th year)
        String[] yearLevels = new String[]{
                getString(R.string.year_1),
                getString(R.string.year_2),
                getString(R.string.year_3),
                getString(R.string.year_4)
        };
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, yearLevels);
        actYearLevel.setAdapter(yearAdapter);
    }

    private void setupListeners() {
        btnSave.setOnClickListener(v -> saveStudentInfo());
        btnClear.setOnClickListener(v -> clearForm());
    }

    private void saveStudentInfo() {
        clearErrors();

        boolean isValid = true;

        if (getText(etStudentNumber).isEmpty()) {
            tilStudentNumber.setError(getString(R.string.error_required));
            isValid = false;
        }

        if (getText(etFirstName).isEmpty()) {
            tilFirstName.setError(getString(R.string.error_required));
            isValid = false;
        }

        if (getText(etLastName).isEmpty()) {
            tilLastName.setError(getString(R.string.error_required));
            isValid = false;
        }

        if (actProgram.getText().toString().trim().isEmpty()) {
            tilProgram.setError(getString(R.string.error_required));
            isValid = false;
        }

        if (actYearLevel.getText().toString().trim().isEmpty()) {
            tilYearLevel.setError(getString(R.string.error_required));
            isValid = false;
        }

        if (isValid) {
            Toast.makeText(this, R.string.student_info_saved, Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        clearErrors();
        etStudentNumber.setText("");
        etFirstName.setText("");
        etMiddleName.setText("");
        etLastName.setText("");
        actProgram.setText("", false);
        actYearLevel.setText("", false);
        Toast.makeText(this, R.string.form_cleared, Toast.LENGTH_SHORT).show();
    }

    private void clearErrors() {
        tilStudentNumber.setError(null);
        tilFirstName.setError(null);
        tilMiddleName.setError(null);
        tilLastName.setError(null);
        tilProgram.setError(null);
        tilYearLevel.setError(null);
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() != null ? editText.getText().toString().trim() : "";
    }
}
