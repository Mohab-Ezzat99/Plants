package mrandroid.app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import mrandroid.app.model.PlantModel;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCourse(PlantModel plantModel);

    @Query("SELECT * FROM PlantModel")
    LiveData<List<PlantModel>> getAllCourses();

    @Query("DELETE FROM PlantModel")
    Completable deleteAllCourses();

}
