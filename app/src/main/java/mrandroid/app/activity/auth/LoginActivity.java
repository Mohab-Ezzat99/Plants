package mrandroid.app.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import mrandroid.app.activity.main.HomeActivity;
import mrandroid.app.databinding.ActivityLoginBinding;
import mrandroid.app.util.Constants;
import mrandroid.app.util.LoadingDialog;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = new LoadingDialog(this);

        binding.btnLogin.setOnClickListener(view -> {
            boolean isValid = checkValidation();
            if (isValid) loginToApp();
        });

        binding.tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(getBaseContext(), SignupActivity.class));
        });

        binding.tvBrowseApp.setOnClickListener(view -> {
            Constants.IS_ADMIN = false;
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        });
    }

    private boolean checkValidation() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "email is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "password is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // All is fine
        return true;
    }

    private void loginToApp() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (email.equals("admin@admin.com") && password.equals("admin")) {
            Toast.makeText(getBaseContext(), "Welcome! Login Successfully", Toast.LENGTH_LONG).show();

            Constants.IS_LOGIN = true;
            Constants.IS_ADMIN = true;
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
            return;
        }

        loadingDialog.display();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loadingDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Welcome! Login Successfully", Toast.LENGTH_LONG).show();

                        Constants.IS_LOGIN = true;
                        Constants.IS_ADMIN = false;
                        startActivity(new Intent(getBaseContext(), HomeActivity.class));
                        finish();
                    } else {
                        loadingDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Failed to Login", Toast.LENGTH_LONG).show();
                    }
                });
    }
}