package com.nextgeni.task.view_models;

import android.app.Activity;
import android.app.ListActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nextgeni.task.database.DatabaseObject;
import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.GenericResponse;
import com.nextgeni.task.interfaces.SendData;
import com.nextgeni.task.repositories.ProductRepository;


import java.util.List;

import retrofit2.Response;


public class CategoryViewModel extends ViewModel {

    DatabaseObject db;
    private MutableLiveData<List<Categories>> categoriesMutableLiveData = new MutableLiveData<>();

    public CategoryViewModel() {

    }


    public void init(final Activity activity ) {
       ProductRepository.getInstance().getCategories(activity, new SendData<Response<GenericResponse<Categories>>>() {
           @Override
           public void sendData(Response<GenericResponse<Categories>> response) {
               categoriesMutableLiveData.setValue(response.body().getData());
               db = DatabaseObject.getInstance(activity);
               db.ProductsDao().insertCatgory(response.body().getData());
           }
       });
    }
    public void initOffline(Activity activity) {
        categoriesMutableLiveData.setValue(ProductRepository.getInstance().getCategoriesOffline(activity));
    }


    public LiveData<List<Categories>> getCategories() {
        return categoriesMutableLiveData;
    }
}
