package com.tamersarioglu.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class FilmlerActivity extends AppCompatActivity {

    private Toolbar toolbar_FilmlerActivity;
    private RecyclerView recyclerView_FilmlerActivity;

    private ArrayList<Filmler> filmlerArrayList;
    private FilmlerAdapter filmlerAdapter;

    private Kategoriler kategori;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);
        init();

        toolbar_FilmlerActivity.setTitle(getIntent().getStringExtra("kategori_adi"));
        setSupportActionBar(toolbar_FilmlerActivity);
        vt = new Veritabani(this);

        kategori = (Kategoriler) getIntent().getSerializableExtra("kategori_nesne");

        filmlerArrayList = new FilmlerDAO().tumFilmlerByKategoriId(vt,kategori.getKategori_id());

        recyclerView_FilmlerActivity.setHasFixedSize(true);
        recyclerView_FilmlerActivity.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        filmlerAdapter = new FilmlerAdapter(this,filmlerArrayList);
        recyclerView_FilmlerActivity.setAdapter(filmlerAdapter);
    }

    private void init() {
        toolbar_FilmlerActivity = findViewById(R.id.toolbar_FilmlerActivity);
        recyclerView_FilmlerActivity = findViewById(R.id.recyclerView_FilmlerActivity);
    }
}