package com.nextgeni.task.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextgeni.task.R;
import com.nextgeni.task.adapters.CategoryAdapter;
import com.nextgeni.task.adapters.ProductAdapter;
import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.GenericResponse;
import com.nextgeni.task.entities.Products;
import com.nextgeni.task.utils.Utils;
import com.nextgeni.task.view_models.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private CategoryAdapter adapter;
    private CategoryViewModel viewModel;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(CategoryViewModel viewModelProviders) {

        CategoryFragment fragment = new CategoryFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        ButterKnife.bind(this, view);

        adapter = new CategoryAdapter(this);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


    private void configureViewModel() {
        viewModel.init(getActivity());
        viewModel.getCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(@Nullable List<Categories> categoriesGenericResponse) {
                updateUI(categoriesGenericResponse);
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        if(Utils.isOnline(getContext())){
            configureViewModel();
        }else{
            manageOffline();
        }
    }

    private void updateUI(List<Categories> response) {
        adapter.setList((ArrayList<Categories>) response);
    }

    private void manageOffline() {
        viewModel.initOffline(getActivity());
        viewModel.getCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(@Nullable List<Categories> productsGenericResponse) {
                updateUI(productsGenericResponse);
            }
        });


}}
