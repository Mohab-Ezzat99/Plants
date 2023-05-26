package mrandroid.app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import mrandroid.app.model.CartModel;
import mrandroid.app.model.PlantModel;
import mrandroid.app.model.RateModel;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlant(PlantModel plantModel);

    @Query("SELECT * FROM PlantModel")
    LiveData<List<PlantModel>> getAllPlants();

    @Query("SELECT * FROM PlantModel WHERE id=:id")
    LiveData<PlantModel> getPlantById(int id);

    @Delete
    Completable deletePlant(PlantModel plantModel);

    @Update
    Completable updatePlant(PlantModel plantModel);

    @Query("DELETE FROM PlantModel")
    Completable deleteAllPlants();

    // Cart
    //=====================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlantToCart(CartModel cartModel);

    @Query("SELECT * FROM CartModel")
    LiveData<List<CartModel>> getAllCart();

    @Query("SELECT * FROM CartModel WHERE id=:id")
    LiveData<CartModel> getCartById(int id);

    @Delete
    Completable deletePlantFromCart(CartModel cartModel);

    @Query("DELETE FROM CartModel")
    Completable deleteAllCart();

    // Rate
    //=====================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRate(RateModel rateModel);

    @Query("SELECT * FROM RateModel")
    LiveData<List<RateModel>> getAllRates();

    @Delete
    Completable deleteRate(RateModel rateModel);

}
