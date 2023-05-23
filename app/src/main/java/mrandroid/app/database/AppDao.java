package mrandroid.app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import mrandroid.app.model.CourseModel;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCourse(CourseModel courseModel);

    @Query("SELECT * FROM CourseModel")
    LiveData<List<CourseModel>> getAllCourses();

    @Query("DELETE FROM CourseModel")
    Completable deleteAllCourses();

}
