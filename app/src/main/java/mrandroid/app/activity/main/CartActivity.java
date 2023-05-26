package mrandroid.app.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.ViewModel;
import mrandroid.app.activity.auth.LoginActivity;
import mrandroid.app.adapter.CartAdapter;
import mrandroid.app.databinding.ActivityCartBinding;
import mrandroid.app.model.CartModel;
import mrandroid.app.util.Constants;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityCartBinding binding;
    private CartAdapter cartAdapter = new CartAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        cartAdapter.setCanDelete(true);
        cartAdapter.setListener(this);
        binding.rvPlants.setAdapter(cartAdapter);

        binding.btnCheckout.setOnClickListener(view -> {
            if(Constants.IS_LOGIN){
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "You must login first", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        fetchData();
    }

    private void fetchData() {
        viewModel.getAllCart().observe(this, courseModels -> {
            if (courseModels.isEmpty()) {
                binding.tvEmpty.setVisibility(View.VISIBLE);
                binding.clDesign.setVisibility(View.GONE);
            } else {
                binding.tvEmpty.setVisibility(View.GONE);
                binding.clDesign.setVisibility(View.VISIBLE);
            }
            cartAdapter.setList(courseModels);
            cartAdapter.notifyDataSetChanged();
            binding.tvTotal.setText("Total: " + cartAdapter.getTotal() + " SAR");
        });
    }

    @Override
    public void onItemClick(CartModel cartModel) {
        Intent intent = new Intent(this, PlantDetailsActivity.class);
        intent.putExtra(Constants.PLANT_ID, cartModel.getId());
        startActivity(intent);
    }

    @Override
    public void onItemDelete(CartModel cartModel) {
        // delete
        viewModel.deletePlantFromCart(cartModel);
    }
}