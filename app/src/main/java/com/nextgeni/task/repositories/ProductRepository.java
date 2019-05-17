package com.nextgeni.task.repositories;

import android.app.Activity;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.widget.Toast;


import com.nextgeni.task.database.DatabaseObject;
import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.GenericResponse;
import com.nextgeni.task.entities.Products;
import com.nextgeni.task.interfaces.SendData;
import com.nextgeni.task.network.ServerRequests;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductRepository {

    private static String ACCESS_TOKEN = "c7e319eb908abd246226d5240eb3a310";
    private static String language = "en";
    private static ProductRepository instance;
    DatabaseObject db;


    public static ProductRepository getInstance(){
        if(instance == null){
            instance = new ProductRepository();
        }
        return instance;
    }

    public void getCategories(final Activity activity, SendData<Response<GenericResponse<Categories>>> responseServerResponse) {
        Call<GenericResponse<Categories>> getCategory = ServerRequests.getInstance(activity).getCategories(ACCESS_TOKEN, language);
        getData(activity, getCategory, responseServerResponse);
    }

    public void getProducts(final Activity activity, final SendData<Response<GenericResponse<Products>>> responseServerResponse) {
        Call<GenericResponse<Products>> getCategory = ServerRequests.getInstance(activity).getProduct(ACCESS_TOKEN, "2");
        getData(activity, getCategory, responseServerResponse);
    }

    public List<Products> getProductsOffline(Context context) {
        db = DatabaseObject.getInstance(context);
                return db.ProductsDao().getAllProducts();
    }

    public List<Categories> getCategoriesOffline(Context context) {
        db = DatabaseObject.getInstance(context);
        return db.ProductsDao().getAllCategories();
    }



    private void getData(final Context activity, Call call, final SendData responseServerResponse) {

        call.enqueue(new Callback<GenericResponse>() {
                         @Override
                         public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {

                                 try {
                                     responseServerResponse.sendData(response);

                                 } catch (Exception ex) {
                                     ex.printStackTrace();
                                 }

                         }

                         @Override
                         public void onFailure(Call<GenericResponse> call, Throwable t) {
                             try {
                                 Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                 responseServerResponse.sendData(null);
                             } catch (Exception e) {
                                 t.printStackTrace();
                             }
                         }


                     }
        );
    }


}
