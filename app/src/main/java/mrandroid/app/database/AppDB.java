package mrandroid.app.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import mrandroid.app.model.CourseModel;
import mrandroid.app.model.QuestionModel;

@Database(
        entities = {CourseModel.class},
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class AppDB extends RoomDatabase {
    private static AppDB instance;

    public abstract AppDao appDao();

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDB.class, "App_DB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
