package mrandroid.app.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import mrandroid.app.ViewModel;
import mrandroid.app.activity.admin.AddPlantActivity;
import mrandroid.app.adapter.PlantsAdapter;
import mrandroid.app.databinding.ActivityHomeBinding;
import mrandroid.app.model.PlantModel;
import mrandroid.app.util.Constants;

public class HomeActivity extends AppCompatActivity implements PlantsAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityHomeBinding binding;
    private PlantsAdapter plantsAdapter = new PlantsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        plantsAdapter.setListener(this);
        binding.rvCourses.setAdapter(plantsAdapter);

        if(Constants.IS_TEACHER) binding.fabAdd.setVisibility(View.VISIBLE);

        binding.fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AddPlantActivity.class);
            startActivity(intent);
        });

        fetchAllCourses();
    }

    private void fetchAllCourses() {
        viewModel.getAllCourses().observe(this, courseModels -> {
            plantsAdapter.setList(courseModels);
            plantsAdapter.notifyDataSetChanged();
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

    }
}