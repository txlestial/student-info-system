package com.santos.student_info_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class ClassStudentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_class_students);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String className = getIntent().getStringExtra("CLASS_NAME");
        if (className != null) {
            TextView tvClassNameTitle = findViewById(R.id.tvClassNameTitle);
            if (tvClassNameTitle != null) {
                tvClassNameTitle.setText(className);
            }
        }

        setupActions();
        setupBottomNavigation();
    }

    private void setupActions() {
        ImageView ivBack = findViewById(R.id.ivBack);
        ImageView ivNotification = findViewById(R.id.ivNotification);
        MaterialButton btnAddStudent = findViewById(R.id.btnAddStudent);
        MaterialButton btnRemoveStudent1 = findViewById(R.id.btnRemoveStudent1);
        MaterialButton btnRemoveStudent2 = findViewById(R.id.btnRemoveStudent2);

        if (ivBack != null) {
            ivBack.setOnClickListener(v -> finish());
        }

        if (ivNotification != null) {
            ivNotification.setOnClickListener(this::showNotificationPopup);
        }

        if (btnAddStudent != null) {
            btnAddStudent.setOnClickListener(v -> {
                Intent intent = new Intent(this, StudentInfoActivity.class);
                startActivity(intent);
            });
        }

        if (btnRemoveStudent1 != null) {
            btnRemoveStudent1.setOnClickListener(v ->
                    Toast.makeText(this, "Student removed from class", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnRemoveStudent2 != null) {
            btnRemoveStudent2.setOnClickListener(v ->
                    Toast.makeText(this, "Student removed from class", Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void showNotificationPopup(android.view.View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenu().add(0, 1, 0, "🔔 No new notifications");
        popupMenu.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        popupMenu.show();
    }

    private void setupBottomNavigation() {
        View navClassLists = findViewById(R.id.navClassLists);
        View navHome = findViewById(R.id.navHome);
        View navProfile = findViewById(R.id.navProfile);

        if (navClassLists != null) {
            // Already on Class Lists
        }

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            });
        }

        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
            });
        }
    }
}
