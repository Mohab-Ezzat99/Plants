package mrandroid.app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import mrandroid.app.database.AppDB;
import mrandroid.app.database.AppDao;
import mrandroid.app.model.CartModel;
import mrandroid.app.model.PlantModel;
import mrandroid.app.model.RateModel;

public class Repository {
    private final AppDao appDao;

    public Repository(Application application) {
        this.appDao = AppDB.getInstance(application).appDao();
    }

    public void insertPlant(PlantModel plantModel) {
        appDao.insertPlant(plantModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<PlantModel>> getAllPlants() {
        return appDao.getAllPlants();
    }

    public LiveData<PlantModel> getPlantById(int id) {
        return appDao.getPlantById(id);
    }

    public void updatePlant(PlantModel plantModel) {
        appDao.updatePlant(plantModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deletePlant(PlantModel plantModel) {
        appDao.deletePlant(plantModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteAllPlants() {
        appDao.deleteAllPlants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    // Cart
    //=====================================================

    public void insertPlantToCart(CartModel cartModel) {
        appDao.insertPlantToCart(cartModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<CartModel>> getAllCart() {
        return appDao.getAllCart();
    }

    public LiveData<CartModel> getCartById(int id) {
        return appDao.getCartById(id);
    }

    public void deletePlantFromCart(CartModel cartModel) {
        appDao.deletePlantFromCart(cartModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteAllCart() {
        appDao.deleteAllCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    // Rate
    //=====================================================

    public void insertRate(RateModel rateModel) {
        appDao.insertRate(rateModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<RateModel>> getAllRates() {
        return appDao.getAllRates();
    }

    public void deleteRate(RateModel rateModel) {
        appDao.deleteRate(rateModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
