package com.nextgeni.task.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.nextgeni.task.Dao.ApplicationDao;
import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.DatabaseTypeConverter;
import com.nextgeni.task.entities.Products;


@Database(entities = {Products.class, Categories.class}, version = 1)
public abstract class DatabaseObject extends RoomDatabase {

    public static DatabaseObject instance;
    public abstract ApplicationDao ProductsDao();

    public static DatabaseObject getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, DatabaseObject.class, "application_database").allowMainThreadQueries().build();
        }
        return instance;
    }


}
