package mrandroid.app.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.ViewModel;
import mrandroid.app.adapter.PlantsAdapter;
import mrandroid.app.databinding.ActivityCartBinding;
import mrandroid.app.databinding.ActivityCheckoutBinding;
import mrandroid.app.model.PlantModel;
import mrandroid.app.util.Constants;

public class CheckoutActivity extends AppCompatActivity implements PlantsAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityCheckoutBinding binding;
    private PlantsAdapter plantsAdapter = new PlantsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        plantsAdapter.setCanDelete(true);
        plantsAdapter.setListener(this);
        binding.rvPlants.setAdapter(plantsAdapter);

        binding.btnContact.setOnClickListener(view -> {

        });

        binding.btnPay.setOnClickListener(view -> {

        });

        fetchAllCourses();
    }

    private void fetchAllCourses() {
        viewModel.getAllCourses().observe(this, courseModels -> {
            plantsAdapter.setList(courseModels);
            plantsAdapter.notifyDataSetChanged();
            binding.tvTotal.setText("Total: " + plantsAdapter.getTotal() + " SAR");
        });
    }

    @Override
    public void onItemClick(PlantModel plantModel) {
        Intent intent = new Intent(this, PlantDetailsActivity.class);
        intent.putExtra(Constants.PLANT_MODEL, plantModel);
        startActivity(intent);
    }

    @Override
    public void onItemDelete(PlantModel plantModel) {
        // delete
    }
}