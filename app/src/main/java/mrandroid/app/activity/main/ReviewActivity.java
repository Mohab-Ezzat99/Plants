package mrandroid.app.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.ViewModel;
import mrandroid.app.adapter.RateAdapter;
import mrandroid.app.databinding.ActivityReviewBinding;
import mrandroid.app.model.RateModel;
import mrandroid.app.util.Constants;

public class ReviewActivity extends AppCompatActivity implements RateAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityReviewBinding binding;
    private RateAdapter rateAdapter = new RateAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rateAdapter.setListener(this);
        binding.rvRates.setAdapter(rateAdapter);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        fetchAllRates();
    }

    private void fetchAllRates() {
        viewModel.getAllRates().observe(this, ratesModels -> {
            if (ratesModels.isEmpty()) {
                binding.tvEmpty.setVisibility(View.VISIBLE);
                binding.clDesign.setVisibility(View.GONE);
            } else {
                binding.tvEmpty.setVisibility(View.GONE);
                binding.clDesign.setVisibility(View.VISIBLE);
            }
            rateAdapter.setList(ratesModels);
            rateAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onItemClick(RateModel plantModel) {

    }

    @Override
    public void onItemDelete(RateModel rateModel) {
        // delete
        viewModel.deleteRate(rateModel);
    }
}