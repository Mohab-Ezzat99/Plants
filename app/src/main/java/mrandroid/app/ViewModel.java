package mrandroid.app;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import mrandroid.app.model.CartModel;
import mrandroid.app.model.PlantModel;

public class ViewModel extends AndroidViewModel {

    private final Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertPlant(PlantModel plantModel) {
        repository.insertPlant(plantModel);
    }

    public LiveData<List<PlantModel>> getAllPlants() {
        return repository.getAllPlants();
    }

    public LiveData<PlantModel> getPlantById(int id) {
        return repository.getPlantById(id);
    }

    public void deleteAllPlants() {
        repository.deleteAllPlants();
    }

    // Cart
    //=====================================================

    public void insertPlantToCart(CartModel cartModel) {
        repository.insertPlantToCart(cartModel);
    }

    public LiveData<List<CartModel>> getAllCart() {
        return repository.getAllCart();
    }

    public LiveData<CartModel> getCartById(int id) {
        return repository.getCartById(id);
    }

    public void deletePlantFromCart(CartModel cartModel) {
        repository.deletePlantFromCart(cartModel);
    }

    public void deleteAllCart() {
        repository.deleteAllCart();
    }

}
