package com.nextgeni.task.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.Products;

import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface ApplicationDao {


    @Insert()
    void insertCatgory(List<Categories> category);

    @Insert
    void insertProducts(List<Products> products);

    @Query("SELECT * FROM CategoryData")
    List<Categories> getAllCategories();

    @Query("SELECT * FROM ProductData")
    List<Products> getAllProducts();


}