package mrandroid.app.activity.admin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Objects;

import mrandroid.app.R;
import mrandroid.app.ViewModel;
import mrandroid.app.adapter.SelectImagesAdapter;
import mrandroid.app.databinding.ActivityAddPlantBinding;
import mrandroid.app.model.PlantModel;
import mrandroid.app.util.Constants;

public class AddPlantActivity extends AppCompatActivity implements SelectImagesAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityAddPlantBinding binding;
    private SelectImagesAdapter selectImagesAdapter = new SelectImagesAdapter();
    private int selectedImage = -1;
    private PlantModel plantModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        plantModel = (PlantModel) getIntent().getSerializableExtra(Constants.PLANT_MODEL);
        if (plantModel != null) {
            selectedImage =plantModel.getPlantImage();
            selectImagesAdapter.setSelected(plantModel.getPlantImage());
            binding.etPlantName.setText(plantModel.getPlantName());
            binding.etColor.setText(plantModel.getColor());
            binding.etQty.setText(plantModel.getQty() + "");
            binding.etPrice.setText(plantModel.getPrice() + "");
            binding.etRate.setText(plantModel.getRate() + "");
            binding.etDescription.setText(plantModel.getDescription());
        }

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

        if (this.plantModel != null) {
            this.plantModel.setPlantName(plantName);
            this.plantModel.setPlantImage(selectedImage);
            this.plantModel.setColor(color);
            this.plantModel.setDescription(description);
            this.plantModel.setPrice(Float.parseFloat(price));
            this.plantModel.setRate(Float.parseFloat(rate));
            this.plantModel.setQty(Integer.parseInt(qty));
            viewModel.updatePlant(this.plantModel);
            Toast.makeText(this, "Plant updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            PlantModel plantModel = new PlantModel(
                    plantName,
                    selectedImage,
                    color,
                    description,
                    Float.parseFloat(price),
                    Float.parseFloat(rate),
                    Integer.parseInt(qty)
            );
            viewModel.insertPlant(plantModel);
            Toast.makeText(this, "Plant inserted successfully", Toast.LENGTH_SHORT).show();
        }
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
        selectedImage=item;
    }
}