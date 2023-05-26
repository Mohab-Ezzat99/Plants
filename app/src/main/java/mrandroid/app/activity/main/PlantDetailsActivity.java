package mrandroid.app.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.R;
import mrandroid.app.ViewModel;
import mrandroid.app.activity.admin.AddPlantActivity;
import mrandroid.app.databinding.ActivityPlantDetailsBinding;
import mrandroid.app.model.PlantModel;
import mrandroid.app.util.Constants;

public class PlantDetailsActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private int plantId;
    private PlantModel plantModel;
    private ActivityPlantDetailsBinding binding;
    private int qty=1;
    private int cartStatus = 0; // add=0 , update=1 , delete=2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        plantId = getIntent().getIntExtra(Constants.PLANT_ID, -1);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getPlantById(plantId).observe(this, plantModel -> {
            if(plantModel==null) return;
            this.plantModel = plantModel;

            binding.ivImg.setImageResource(plantModel.getPlantImage());
            binding.tvPlantName.setText(plantModel.getPlantName());
            binding.tvColor.setText("Color: " + plantModel.getColor());
            binding.tvNumber.setText("Quantity: " + plantModel.getQty());
            binding.tvDescription.setText(plantModel.getDescription());
            binding.tvPrice.setText("Price: " + plantModel.getPrice() + " SAR");
            binding.ratingBar.setRating(plantModel.getRate());

            fetchCartItem();
        });

        binding.ivPlus.setOnClickListener(view -> {
            if(qty < plantModel.getQty()) {
                binding.tvQty.setText((++qty) + "");
                binding.tvPrice.setText("Price: " + plantModel.getPrice() * qty + " SAR");
                if (cartStatus == 2) {
                    cartStatus = 1;
                    binding.btnCart.setText("Update cart");
                }
            }
        });

        binding.ivMinus.setOnClickListener(view -> {
            if (qty > 1) {
                binding.tvQty.setText((--qty) + "");
                binding.tvPrice.setText("Price: " + plantModel.getPrice() * qty + " SAR");
                if (cartStatus == 2) {
                    cartStatus = 1;
                    binding.btnCart.setText("Update cart");
                }
            }
        });

        binding.btnCart.setOnClickListener(view -> {
            switch (cartStatus) {
                case 0:
                    // add
                    viewModel.insertPlantToCart(plantModel.toCartModel(qty));
                    cartStatus = 2;
                    Toast.makeText(this, "Added to cart successfully", Toast.LENGTH_SHORT).show();
                    binding.btnCart.setText("Delete from cart");
                    break;
                case 1:
                    // update
                    viewModel.deletePlantFromCart(plantModel.toCartModel(qty));
                    viewModel.insertPlantToCart(plantModel.toCartModel(qty));
                    cartStatus = 2;
                    Toast.makeText(this, "Updated cart successfully", Toast.LENGTH_SHORT).show();
                    binding.btnCart.setText("Delete from cart");
                    break;
                case 2:
                    // delete
                    viewModel.deletePlantFromCart(plantModel.toCartModel(qty));
                    cartStatus = 0;
                    Toast.makeText(this, "Deleted from cart successfully", Toast.LENGTH_SHORT).show();
                    binding.btnCart.setText("Add to cart");
                    break;
            }
        });
    }

    private void fetchCartItem() {
        viewModel.getCartById(plantModel.getId()).observe(this, cartModel -> {
            if (cartModel == null) {
                cartStatus = 0;
                binding.btnCart.setText("Add to cart");
            } else {
                cartStatus = 2;
                binding.btnCart.setText("Delete from cart");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plant, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setVisible(Constants.IS_ADMIN);
        menu.getItem(1).setVisible(Constants.IS_ADMIN);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            Intent intent = new Intent(this, AddPlantActivity.class);
            intent.putExtra(Constants.PLANT_MODEL, plantModel);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.menu_delete) {
            Toast.makeText(this, "Plant deleted successfully", Toast.LENGTH_SHORT).show();
            finish();
            viewModel.deletePlant(plantModel);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}