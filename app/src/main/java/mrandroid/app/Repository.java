package mrandroid.app;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import mrandroid.app.database.AppDB;
import mrandroid.app.database.AppDao;
import mrandroid.app.model.CourseModel;

public class Repository {
    private final AppDao appDao;

    public Repository(Application application) {
        this.appDao = AppDB.getInstance(application).appDao();
    }

    public void insertCourse(CourseModel courseModel) {
        appDao.insertCourse(courseModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<CourseModel>> getAllCourses() {
        return appDao.getAllCourses();
    }

    public void deleteAllCourses() {
        appDao.deleteAllCourses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
