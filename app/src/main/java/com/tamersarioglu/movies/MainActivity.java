package com.tamersarioglu.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView_MainActivity;

    private ArrayList<Kategoriler> kategorilerArrayList;
    private KategoriAdapter adapter;

    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        veritabaniKopyala();

        init();

        vt = new Veritabani(this);

        toolbar.setTitle("Kategoriler");
        setSupportActionBar(toolbar);

        recyclerView_MainActivity.setHasFixedSize(true);
        recyclerView_MainActivity.setLayoutManager(new LinearLayoutManager(this));

        kategorilerArrayList = new KategoriDAO().tumKategoriler(vt);

        adapter = new KategoriAdapter(this, kategorilerArrayList);
        recyclerView_MainActivity.setAdapter(adapter);


    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView_MainActivity = findViewById(R.id.recyclerView_MainActivity);
    }

    public void veritabaniKopyala(){

        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }
}