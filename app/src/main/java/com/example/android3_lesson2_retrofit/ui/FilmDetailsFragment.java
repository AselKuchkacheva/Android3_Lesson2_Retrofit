package com.example.android3_lesson2_retrofit.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.storage.GhibliStorage;

public class FilmDetailsFragment extends Fragment {
    private GhibliStorage ghibliStorage = new GhibliStorage();

    private TextView tvDescription;
    private TextView tvTitle;
    private TextView tvDirector;
    private TextView tvProducer;
    private TextView tvReleaseDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getData();
        }
    }

    private void getData() {
        ghibliStorage.getFilm(getArguments().getString("key"), new GhibliStorage.GhibliCallback<Film>() {
            @Override
            public void onSuccess(Film film) {
                tvTitle = getView().findViewById(R.id.tv_title);
                tvTitle.setText(film.getTitle());
                tvReleaseDate = getView().findViewById(R.id.tv_releaseDate);
                tvReleaseDate.setText(film.getReleaseDate());
                tvDirector = getView().findViewById(R.id.tv_director);
                tvDirector.setText(film.getDirector());
                tvProducer = getView().findViewById(R.id.tv_producer);
                tvProducer.setText(film.getProducer());
                tvDescription = getView().findViewById(R.id.tv_description);
                tvDescription.setText(film.getDescription());

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film_details, container, false);
    }
}