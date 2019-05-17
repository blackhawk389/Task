package com.nextgeni.task.view_models;

import android.app.Activity;
import android.app.ListActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nextgeni.task.database.DatabaseObject;
import com.nextgeni.task.entities.GenericResponse;
import com.nextgeni.task.entities.Products;
import com.nextgeni.task.interfaces.SendData;
import com.nextgeni.task.repositories.ProductRepository;

import java.util.List;

import retrofit2.Response;




public class ProductViewModel extends ViewModel {

    private MutableLiveData<List<Products>> categoriesMutableLiveData = new MutableLiveData<>();
    DatabaseObject db;


    public ProductViewModel() {

    }

    public void init(final Activity activity) {
        ProductRepository.getInstance().getProducts(activity, new SendData<Response<GenericResponse<Products>>>() {
            @Override
            public void sendData(Response<GenericResponse<Products>> response) {
                categoriesMutableLiveData.setValue(response.body().getData());
                db = DatabaseObject.getInstance(activity);
                db.ProductsDao().insertProducts(response.body().getData());
            }
        });
    }
    public void initOffline(final Activity activity) {
        categoriesMutableLiveData.setValue(ProductRepository.getInstance().getProductsOffline(activity));
    }
    public LiveData<List<Products>> getProducts() {
        return categoriesMutableLiveData;
    }
}
