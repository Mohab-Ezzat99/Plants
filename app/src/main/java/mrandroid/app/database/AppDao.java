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

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlant(PlantModel plantModel);

    @Query("SELECT * FROM PlantModel")
    LiveData<List<PlantModel>> getAllPlants();

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

}
