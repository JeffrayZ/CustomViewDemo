package com.example.nestedscrollviewdemo;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nestedscrollviewdemo.fragment.ItemFragment;
import com.example.nestedscrollviewdemo.fragment.MyItemRecyclerViewAdapter;
import com.example.nestedscrollviewdemo.fragment.dummy.DummyContent;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



//        setContentView(R.layout.activity_main_demo3);
//        RecyclerView recyclerView = findViewById(R.id.demo3_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));



//        setContentView(R.layout.activity_main_demo2);


//        initDemo4_1();
//        initDemo4_2();
//        initBehavior();
//        initBehavior1();
        initJk541();
//        initDemo5();

    }

    private void initDemo5() {
        setContentView(R.layout.activity_main_demo5);
    }

    private void initJk541() {
        setContentView(R.layout.activity_main_jk541);
        final ViewPager2 viewPager2 = findViewById(R.id.jk541_view_pager);
        final TabLayout tabLayout = findViewById(R.id.jk541_tab_layout);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return ItemFragment.newInstance(1);
            }

            @Override
            public int getItemCount() {
                return 4;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initBehavior() {
        setContentView(R.layout.activity_main_behavior);
    }

    private void initBehavior1() {
        setContentView(R.layout.activity_main_behavior_1);

        TextView textView = findViewById(R.id.behavior1_tv_top);
        RecyclerView recyclerView = findViewById(R.id.behavior1_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    private void initDemo4_2() {
        setContentView(R.layout.activity_main_demo4_2);
        final ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        ImageView imageView = findViewById(R.id.iv_head_image);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return ItemFragment.newInstance(1);
            }

            @Override
            public int getItemCount() {
                return 4;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initDemo4_1() {
        setContentView(R.layout.activity_main_demo4_1);
    }
}
