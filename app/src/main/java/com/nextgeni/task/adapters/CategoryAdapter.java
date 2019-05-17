package com.nextgeni.task.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.fragment.ProductFragment;

import java.util.ArrayList;


public class CategoryAdapter extends FragmentStatePagerAdapter {


    private ArrayList<Categories> list;

    public CategoryAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
    }


    @Override
    public int getCount() {

        return list != null ?  list.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {

                Fragment fragment;
                fragment = new ProductFragment();
                return fragment;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


    }

    @Override
    public CharSequence getPageTitle(int position) {
            return list.get(position).getCategoryName();

    }

    public void setList(ArrayList<Categories> list ) {
        this.list = list;
        notifyDataSetChanged();
    }


}
