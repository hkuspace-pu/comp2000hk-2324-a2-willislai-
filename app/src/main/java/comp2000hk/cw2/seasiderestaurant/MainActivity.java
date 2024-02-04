package comp2000hk.cw2.seasiderestaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import comp2000hk.cw2.seasiderestaurant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setBackgroundColor(getResources().getColor(R.color.red_sharp));
        setSupportActionBar(binding.toolbar);

        FirebaseApp.initializeApp(this);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent further execution
        } else {
            String username = mAuth.getCurrentUser().getDisplayName();

            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_booking, R.id.navigation_addBooking, R.id.navigation_favourite, R.id.navigation_preference)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);

            // Set initial toolbar title
            setToolbarTitle(username);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drop_down_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setInfo) {
            return true;
        } else if (id == R.id.action_changePassword) {
            return true;
        } else if (id == R.id.action_feedback) {
            return true;
        } else if (id == R.id.action_setNotification) {
            return true;
        } else if (id == R.id.action_logout) {
            showLogoutConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to show logout confirmation dialog
    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed logout, perform logout logic
                        performLogout();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User canceled logout, dismiss the dialog
                        dialog.dismiss();
                    }
                })
                .show();
    }

    // Method to perform logout
    private void performLogout() {
        FirebaseAuth.getInstance().signOut();

        // Navigate to the login screen (Modify the Intent based on your actual situation)
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent user from going back to MainActivity
    }

    @Override
    public boolean onSupportNavigateUp() {
        String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Method to set the toolbar title dynamically
    private void setToolbarTitle(String username) {
        if (getSupportActionBar() != null) {
            if (username != null) {
                String welcomeMsg = "Hi, " + username + "!";
                getSupportActionBar().setTitle(welcomeMsg);
            } else {
                getSupportActionBar().setTitle("Hi!");
            }
        }
    }
    // When have the user's information, call this method to update the toolbar title
    public boolean updateToolbarWithUserName(String userName) {
        setToolbarTitle(userName);
        return false;
    }
}
