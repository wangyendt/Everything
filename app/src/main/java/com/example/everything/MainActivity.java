package com.example.everything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.everything.recorder.RecorderFragment;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static Integer num_apps = 2;

    private ViewPager2 viewPager2;
    private LinearLayout llRecorder, llEcharts;
    private ImageView ivRecorder, ivEcharts, ivCurr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();
        initTabView();
    }

    private void initTabView() {
        llRecorder = findViewById(R.id.llRecorder);
        llRecorder.setOnClickListener(this);
        ivRecorder = findViewById(R.id.ivRecorder);
        llEcharts = findViewById(R.id.llEcharts);
        llEcharts.setOnClickListener(this);
        ivEcharts = findViewById(R.id.ivEcharts);

        ivCurr = ivRecorder;
        ivCurr.setSelected(true);
    }

    private void initViewPager() {
        viewPager2 = findViewById(R.id.viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(RecorderFragment.newInstance("", ""));
        fragments.add(RecorderFragment.newInstance("", ""));
        FragmentViewpagerAdapter fragmentAdapter = new FragmentViewpagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTabControl(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTabControl(int idx) {
        ivCurr.setSelected(false);
        switch (idx) {
            case R.id.llRecorder:
                viewPager2.setCurrentItem(0);
                break;
            case 0:
                ivCurr = ivRecorder;
                break;
            case R.id.llEcharts:
                viewPager2.setCurrentItem(1);
                break;
            case 1:
                ivCurr = ivEcharts;
                break;
            default:
                break;
        }
        ivCurr.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        changeTabControl(v.getId());
    }
}