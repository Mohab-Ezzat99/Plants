package mrandroid.app.activity.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mrandroid.app.databinding.ActivityPlantDetailsBinding;
import mrandroid.app.model.PlantModel;
import mrandroid.app.util.Constants;

public class PlantDetailsActivity extends AppCompatActivity {

    private ActivityPlantDetailsBinding binding;
    private int qty = 1;
    private int cartStatus = 0; // add=0 , update=1 , delete=2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PlantModel plantModel = (PlantModel) getIntent().getSerializableExtra(Constants.PLANT_MODEL);

        binding.ivImg.setImageResource(plantModel.getPlantImage());
        binding.tvPlantName.setText(plantModel.getPlantName());
        binding.tvColor.setText(plantModel.getColor());
        binding.tvNumber.setText(plantModel.getQty()+"");
        binding.tvDescription.setText(plantModel.getDescription());
        binding.tvPrice.setText(plantModel.getPrice() + " SAR");
        binding.ratingBar.setRating(plantModel.getRate());

        binding.ivPlus.setOnClickListener(view -> {
            binding.tvQty.setText((++qty) + "");
            binding.tvPrice.setText(plantModel.getPrice() * qty + " SAR");
            if (cartStatus == 2) {
                cartStatus = 1;
                binding.btnCart.setText("Update cart");
            }
        });

        binding.ivMinus.setOnClickListener(view -> {
            if (qty > 0) {
                binding.tvQty.setText((--qty) + "");
                binding.tvPrice.setText(plantModel.getPrice() * qty + " SAR");
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
                    cartStatus = 2;
                    Toast.makeText(this, "Added to cart successfully", Toast.LENGTH_SHORT).show();
                    binding.btnCart.setText("Delete from cart");
                    break;
                case 1:
                    // update
                    cartStatus = 2;
                    Toast.makeText(this, "Updated cart successfully", Toast.LENGTH_SHORT).show();
                    binding.btnCart.setText("Delete from cart");
                    break;
                case 2:
                    // update
                    cartStatus = 0;
                    Toast.makeText(this, "Deleted from cart successfully", Toast.LENGTH_SHORT).show();
                    binding.btnCart.setText("Add to cart");
                    break;
            }
        });

    }
}