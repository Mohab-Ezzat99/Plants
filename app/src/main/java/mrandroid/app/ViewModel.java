package mrandroid.app;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import mrandroid.app.model.PlantModel;

public class ViewModel extends AndroidViewModel {

    private final Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertCourse(PlantModel plantModel) {
        repository.insertCourse(plantModel);
    }

    public LiveData<List<PlantModel>> getAllCourses() {
        return repository.getAllCourses();
    }

    public void deleteAllCourses() {
        repository.deleteAllCourses();
    }

}
