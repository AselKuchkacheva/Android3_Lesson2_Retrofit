package com.example.android3_lesson2_retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.model.People;
import com.example.android3_lesson2_retrofit.data.storage.GhibliStorage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,new FilmsFragment());
        transaction.addToBackStack(null);
        transaction.commit();

//        ghibliStorage.getFilm("2baf70d1-42bb-4437-b551-e5fed5a87abe",
//                new GhibliStorage.GhibliCallback<Film>() {
//            @Override
//            public void onSuccess(Film film) {
//                Log.d("tag", film.toString());
//            }
//        });
//
//        ghibliStorage.getPeople("fe93adf2-2f3a-4ec4-9f68-5422f1b87c01",
//                new GhibliStorage.GhibliCallback<People>() {
//            @Override
//            public void onSuccess(People people) {
//                Log.d("tag", people.toString());
//            }
//        });
//
//
//        Log.d("tag", "Some code...");
    }
}