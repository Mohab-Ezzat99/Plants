package mrandroid.app;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import mrandroid.app.model.CartModel;
import mrandroid.app.model.PlantModel;
import mrandroid.app.model.RateModel;

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

    public void updatePlant(PlantModel plantModel) {
        repository.updatePlant(plantModel);
    }

    public void deletePlant(PlantModel plantModel) {
        repository.deletePlant(plantModel);
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

    // Rate
    //=====================================================

    public void insertRate(RateModel rateModel) {
        repository.insertRate(rateModel);
    }

    public LiveData<List<RateModel>> getAllRates() {
        return repository.getAllRates();
    }

    public void deleteRate(RateModel rateModel) {
        repository.deleteRate(rateModel);
    }
}
