package com.nextgeni.task.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nextgeni.task.R;
import com.nextgeni.task.adapters.ProductAdapter;
import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.GenericResponse;
import com.nextgeni.task.entities.Products;
import com.nextgeni.task.utils.Utils;
import com.nextgeni.task.view_models.CategoryViewModel;
import com.nextgeni.task.view_models.ProductViewModel;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    @BindView(R.id.product_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private ProductViewModel viewModel;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product, container, false);
        viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        ButterKnife.bind(this, view);
        if(Utils.isOnline(getContext())){
            configureViewModel();
        }else{
            manageOffline();
        }

        return view;

    }

    private void configureViewModel() {
        viewModel.init(getActivity());
        viewModel.getProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(@Nullable List<Products> productsGenericResponse) {
                recyclerView.setAdapter(new ProductAdapter(productsGenericResponse, getActivity()));
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void manageOffline() {
        viewModel.initOffline(getActivity());
        viewModel.getProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(@Nullable List<Products> productsGenericResponse) {
                recyclerView.setAdapter(new ProductAdapter(productsGenericResponse, getActivity()));
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                progressBar.setVisibility(View.GONE);
            }
        });

    }








}
