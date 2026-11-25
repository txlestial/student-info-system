package com.santos.student_info_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);

        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> attemptLogin());
        }

        if (tvForgotPassword != null) {
            tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, R.string.forgot_password, Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void attemptLogin() {
        tilUsername.setError(null);
        tilPassword.setError(null);

        String username = etUsername.getText() != null ? etUsername.getText().toString().trim() : "";
        String password = etPassword.getText() != null ? etPassword.getText().toString().trim() : "";

        boolean isValid = true;
        if (username.isEmpty()) {
            tilUsername.setError(getString(R.string.error_required));
            isValid = false;
        }
        if (password.isEmpty()) {
            tilPassword.setError(getString(R.string.error_required));
            isValid = false;
        }

        if (isValid) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
