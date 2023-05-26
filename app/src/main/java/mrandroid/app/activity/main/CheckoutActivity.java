package mrandroid.app.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.ViewModel;
import mrandroid.app.adapter.CartAdapter;
import mrandroid.app.databinding.ActivityCheckoutBinding;
import mrandroid.app.model.CartModel;
import mrandroid.app.util.Constants;

public class CheckoutActivity extends AppCompatActivity implements CartAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityCheckoutBinding binding;
    private CartAdapter cartAdapter = new CartAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        cartAdapter.setListener(this);
        binding.rvPlants.setAdapter(cartAdapter);

        binding.tvDelivery.setText("Delivery: 25 SAR");

        binding.btnContact.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:01203334444"));
            startActivity(intent);
        });

        binding.btnPay.setOnClickListener(view -> {
            viewModel.deleteAllCart();
            Toast.makeText(this, "Payment successfully", Toast.LENGTH_SHORT).show();
            finish();
        });

        fetchAllCourses();
    }

    private void fetchAllCourses() {
        viewModel.getAllCart().observe(this, courseModels -> {
            cartAdapter.setList(courseModels);
            cartAdapter.notifyDataSetChanged();
            binding.tvTotal.setText("Total: " + (cartAdapter.getTotal() + 25) + " SAR");
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
    }
}