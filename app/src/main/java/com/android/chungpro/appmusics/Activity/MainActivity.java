package com.android.chungpro.appmusics.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.chungpro.appmusics.Adapter.MainViewPagerAdapter;
import com.android.chungpro.appmusics.Fragment.Fragment_Tim_Kiem;
import com.android.chungpro.appmusics.Fragment.Fragment_Trang_Chu;
import com.android.chungpro.appmusics.R;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {
    TabLayout myTabLayout;
    ViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCenter.start(getApplication(), "10cf1d69-7f49-45cc-a4d2-edd54bedd938",
                Analytics.class, Crashes.class);
        anhxa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chu");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        myViewPager.setAdapter(mainViewPagerAdapter);
        myTabLayout.setupWithViewPager(myViewPager);
        myTabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        myTabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);

    }

    private void anhxa() {
        myTabLayout=findViewById(R.id.myTablayout_id);
        myViewPager=findViewById(R.id.myViewPager_id);

    }
}
