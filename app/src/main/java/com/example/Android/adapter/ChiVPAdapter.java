package com.example.Android.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Android.fragment.KhoanChiFragment;
import com.example.Android.fragment.LoaiChiFragment;

public class ChiVPAdapter extends FragmentStatePagerAdapter {
    public ChiVPAdapter(FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                KhoanChiFragment f0 = new KhoanChiFragment();
                return f0;
            case 1:
                LoaiChiFragment f1 = new LoaiChiFragment();
                return f1;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

}
