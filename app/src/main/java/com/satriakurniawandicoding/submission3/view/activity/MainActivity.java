package com.satriakurniawandicoding.submission3.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.satriakurniawandicoding.submission3.R;
import com.satriakurniawandicoding.submission3.view.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    String language;
    ViewPager viewPager;
    SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MovieCatalogue");

        if(getSupportActionBar()!= null) {
            getSupportActionBar().setElevation(0);
        }

        FloatingActionButton fab = findViewById(R.id.favorite_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.idn:
                language = "id-ID";
                viewPager.setAdapter(sectionsPagerAdapter);
                return true;
            case R.id.eng:
                language = "en-US";
                viewPager.setAdapter(sectionsPagerAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String sendData() {
        if(language != null){
            if(language.equals("id-ID")){
                return "id-ID";
            }else {
                return "en-US";
            }
        }
        else {
            return "id-ID";
        }
    }
}
