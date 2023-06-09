package mrandroid.app.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.R;
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
        binding.rvPlants.setAdapter(plantsAdapter);

        if(Constants.IS_ADMIN) binding.fabAdd.setVisibility(View.VISIBLE);

        binding.fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AddPlantActivity.class);
            startActivity(intent);
        });

        fetchData();
    }

    private void fetchData() {
        viewModel.getAllPlants().observe(this, courseModels -> {
            plantsAdapter.setList(courseModels);
            plantsAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setVisible(Constants.IS_ADMIN);
        menu.getItem(1).setVisible(!Constants.IS_ADMIN);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_rate) {
            Intent intent = new Intent(this, ReviewActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.menu_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.menu_contact) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+966550317128"));
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.menu_about) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(PlantModel plantModel) {
        Intent intent = new Intent(this, PlantDetailsActivity.class);
        intent.putExtra(Constants.PLANT_ID, plantModel.getId());
        startActivity(intent);
    }

    @Override
    public void onItemDelete(PlantModel plantModel) {

    }
}