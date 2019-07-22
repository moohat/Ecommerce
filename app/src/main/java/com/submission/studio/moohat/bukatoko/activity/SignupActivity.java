package com.submission.studio.moohat.bukatoko.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.submission.studio.moohat.bukatoko.R;
import com.submission.studio.moohat.bukatoko.adapter.TabAdapter;
import com.submission.studio.moohat.bukatoko.fragment.SigninFragment;
import com.submission.studio.moohat.bukatoko.fragment.SignupFragment;

public class SignupActivity extends AppCompatActivity {

    public  static TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        addTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);


        getSupportActionBar().setTitle("Pengguna Baru");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void addTab(ViewPager viewPager){
        TabAdapter  adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addfragment(new SignupFragment(), "Daftar");
        adapter.addfragment(new SigninFragment(), "Masuk");
        viewPager.setAdapter(adapter);
    }
}
