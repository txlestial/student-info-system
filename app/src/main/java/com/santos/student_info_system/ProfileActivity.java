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

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupBottomNavigation();
        setupHeaderActions();
    }

    private void setupHeaderActions() {
        ImageView ivNotification = findViewById(R.id.ivNotification);
        ImageView ivSettings = findViewById(R.id.ivSettings);

        if (ivNotification != null) {
            ivNotification.setOnClickListener(this::showNotificationPopup);
        }

        if (ivSettings != null) {
            ivSettings.setOnClickListener(v ->
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void setupBottomNavigation() {
        View navClassLists = findViewById(R.id.navClassLists);
        View navHome = findViewById(R.id.navHome);
        View navProfile = findViewById(R.id.navProfile);

        if (navClassLists != null) {
            navClassLists.setOnClickListener(v -> {
                startActivity(new Intent(this, ClassListActivity.class));
                finish();
            });
        }

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            });
        }

        if (navProfile != null) {
            // Already on Profile
        }
    }

    private void showNotificationPopup(android.view.View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenu().add(0, 1, 0, "🔔 No new notifications");
        popupMenu.getMenu().add(0, 2, 1, "📋 Profile verified");
        popupMenu.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        popupMenu.show();
    }
}
