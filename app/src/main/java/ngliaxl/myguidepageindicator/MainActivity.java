package ngliaxl.myguidepageindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Fragment> fragments;
    private MyIndicator indicator;

    private int[] indRes = {R.drawable.indicator_1, R.drawable.indicator_2, R
            .drawable.indicator_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        indicator = findViewById(R.id.myindicator);
        indicator.setIndicatorRes(indRes).setViewPager(viewPager);


        fragments = new ArrayList<>();
        fragments.add(MyFragment.newInstance(android.R.color.holo_red_dark));
        fragments.add(MyFragment.newInstance(android.R.color.holo_green_dark));
        fragments.add(MyFragment.newInstance(android.R.color.holo_purple));
        adapter.setData(fragments);

    }


}
