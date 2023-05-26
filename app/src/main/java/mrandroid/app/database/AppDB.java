package mrandroid.app.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import mrandroid.app.model.CartModel;
import mrandroid.app.model.PlantModel;
import mrandroid.app.model.RateModel;

@Database(
        entities = {PlantModel.class, CartModel.class, RateModel.class},
        version = 1,
        exportSchema = false
)
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
