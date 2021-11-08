package com.example.newzer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


        String title[] = new String[]{"Home","Entertainment","Sports","Health","Education","Technology"};

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new HomeFragment();
            case 1 :
                return new EntertainmentFragment();
            case 2 :
                return new SportsFragment();
            case 3:
                return new HealthFragment();
            case 4 :
                return new EducationFragment();
            default:
                return new TechnologyFragment();

        }
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}
