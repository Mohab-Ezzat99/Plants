package mrandroid.app.activity.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import mrandroid.app.ViewModel;
import mrandroid.app.databinding.ActivityRatingBinding;
import mrandroid.app.model.RateModel;

public class RatingActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private ActivityRatingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding.btnSubmit.setOnClickListener(view -> {
            String comment = Objects.requireNonNull(binding.etComment.getText()).toString();
            RateModel rateModel = new RateModel(binding.ratingBar.getRating(), comment);
            viewModel.insertRate(rateModel);
            finish();
        });
    }
}