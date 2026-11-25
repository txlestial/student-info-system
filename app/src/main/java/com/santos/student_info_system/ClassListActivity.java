package com.santos.student_info_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class ClassListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_class_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupActions();
        setupBottomNavigation();
    }

    private void setupActions() {
        MaterialCardView cardClass1 = findViewById(R.id.cardClass1);
        MaterialCardView cardClass2 = findViewById(R.id.cardClass2);
        MaterialButton btnRemoveClass1 = findViewById(R.id.btnRemoveClass1);
        MaterialButton btnRemoveClass2 = findViewById(R.id.btnRemoveClass2);
        MaterialButton btnAddClass = findViewById(R.id.btnAddClass);
        ImageView ivNotification = findViewById(R.id.ivNotification);

        if (cardClass1 != null) {
            cardClass1.setOnClickListener(v -> {
                Intent intent = new Intent(this, ClassStudentsActivity.class);
                intent.putExtra("CLASS_NAME", "CTAPDEVL - App Dev Lecture");
                startActivity(intent);
            });
        }

        if (cardClass2 != null) {
            cardClass2.setOnClickListener(v -> {
                Intent intent = new Intent(this, ClassStudentsActivity.class);
                intent.putExtra("CLASS_NAME", "CTAPDEVL - App Dev Laboratory");
                startActivity(intent);
            });
        }

        if (btnRemoveClass1 != null) {
            btnRemoveClass1.setOnClickListener(v ->
                    Toast.makeText(this, "Class removed", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnRemoveClass2 != null) {
            btnRemoveClass2.setOnClickListener(v ->
                    Toast.makeText(this, "Class removed", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnAddClass != null) {
            btnAddClass.setOnClickListener(v ->
                    Toast.makeText(this, "Add class dialog/activity", Toast.LENGTH_SHORT).show()
            );
        }

        if (ivNotification != null) {
            ivNotification.setOnClickListener(this::showNotificationPopup);
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
