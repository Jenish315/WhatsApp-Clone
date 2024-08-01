package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.fragments.callFragment;
import com.example.myapplication.fragments.chatFragment;
import com.example.myapplication.fragments.statusFragment;

public class FragmetsAdapter extends FragmentPagerAdapter {
    public FragmetsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new chatFragment();
            case 1:return new statusFragment();
            case 2:return new callFragment();
            default:
                new chatFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      String title = null;
      if(position==0){
          title = "Chat";
      }
        if(position==1){
            title = "Status";
        }
        if(position==2){
            title = "Call";
        }

        return title;
    }
}
