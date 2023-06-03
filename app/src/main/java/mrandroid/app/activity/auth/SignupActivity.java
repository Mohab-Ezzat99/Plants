package mrandroid.app.activity.auth;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import mrandroid.app.databinding.ActivitySignupBinding;
import mrandroid.app.util.LoadingDialog;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = new LoadingDialog(this);

        binding.btnRegister.setOnClickListener(view -> {
            boolean isValid = checkValidation();
            if (isValid) registerUser();
        });

        binding.tvLogin.setOnClickListener(view -> {
            finish();
        });
    }

    private boolean checkValidation() {
        String email = binding.etEmail.getText().toString().trim();
//        String phone = binding.etPhone.getText().toString().trim();
//        String address = binding.etAddress.getText().toString().trim();
//        String house = binding.etHouse.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        String confirmPassword = binding.etConfPassword.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "email is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

//        if (phone.isEmpty()) {
//            Toast.makeText(this, "phone is required!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (address.isEmpty()) {
//            Toast.makeText(this, "address is required!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (house.isEmpty()) {
//            Toast.makeText(this, "house number is required!", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if (password.isEmpty()) {
            Toast.makeText(this, "password is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "confirm password is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password not matches!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // All is fine
        return true;
    }

    private void registerUser() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        loadingDialog.display();
        auth.fetchSignInMethodsForEmail(email) // check if exist (success)
                .addOnSuccessListener(signInMethodQueryResult -> createNewAccount(email, password))
                .addOnFailureListener(e -> {
                    loadingDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Error! " + e.getCause(), Toast.LENGTH_SHORT).show();
                });
    }

    private void createNewAccount(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        loadingDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Signup successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        loadingDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Error! " + task1.getException(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}