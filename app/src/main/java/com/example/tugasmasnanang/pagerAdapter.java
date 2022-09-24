package com.example.tugasmasnanang;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class pagerAdapter extends FragmentStateAdapter {

    public pagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            fragmentMasuk fragmentMasuk = new fragmentMasuk();
            return fragmentMasuk;
        }
        else{
            fragmentDaftar fragmentDaftar = new fragmentDaftar();
            return fragmentDaftar;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
