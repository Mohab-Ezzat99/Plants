package mrandroid.app.activity.admin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

import mrandroid.app.R;
import mrandroid.app.adapter.SelectImagesAdapter;
import mrandroid.app.databinding.ActivityAddPlantBinding;
import mrandroid.app.model.PlantModel;

public class AddPlantActivity extends AppCompatActivity implements SelectImagesAdapter.OnItemClickListener {

    private ActivityAddPlantBinding binding;
    private SelectImagesAdapter selectImagesAdapter = new SelectImagesAdapter();
    private int selectedImage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectImagesAdapter.setList(getPlanetImages());
        selectImagesAdapter.setListener(this);
        binding.rvImgs.setAdapter(selectImagesAdapter);

        binding.btnSubmit.setOnClickListener(view -> {
            validateAndSubmit();
        });
    }

    private void validateAndSubmit() {
        String plantName = Objects.requireNonNull(binding.etPlantName.getText()).toString();
        String color = Objects.requireNonNull(binding.etColor.getText()).toString();
        String qty = Objects.requireNonNull(binding.etQty.getText()).toString();
        String price = Objects.requireNonNull(binding.etPrice.getText()).toString();
        String rate = Objects.requireNonNull(binding.etRate.getText()).toString();
        String description = Objects.requireNonNull(binding.etDescription.getText()).toString();

        boolean isEmpty = selectedImage == -1 || plantName.isEmpty() || color.isEmpty() ||
                qty.isEmpty() || price.isEmpty() || rate.isEmpty() || description.isEmpty();

        if (isEmpty) {
            Toast.makeText(this, "fields are required", Toast.LENGTH_SHORT).show();
            return;
        }


        PlantModel plantModel = new PlantModel(
                plantName,
                selectedImage,
                color,
                description,
                Float.parseFloat(price),
                Float.parseFloat(rate),
                Integer.parseInt(qty)
        );

        //insert
        finish();
    }

    public ArrayList<Integer> getPlanetImages() {
        ArrayList<Integer> plantImages = new ArrayList<>();
        plantImages.add(R.drawable.pic_p1);
        plantImages.add(R.drawable.pic_p2);
        plantImages.add(R.drawable.pic_p3);
        plantImages.add(R.drawable.pic_p4);
        return plantImages;
    }

    @Override
    public void onItemClick(Integer item) {

    }
}