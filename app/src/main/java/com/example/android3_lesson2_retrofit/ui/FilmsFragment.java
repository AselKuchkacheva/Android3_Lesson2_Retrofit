package com.example.android3_lesson2_retrofit.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.storage.GhibliStorage;
import com.example.android3_lesson2_retrofit.ui.adapters.FilmAdapter;

import java.util.List;

public class FilmsFragment extends Fragment implements FilmAdapter.TitleListener{

    public static final String KEY_FILM = "key";
    private RecyclerView recyclerView;
    private FilmAdapter adapter;
    private GhibliStorage ghibliStorage = new GhibliStorage();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_film_title);
        adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setTitleListener(this);
        ghibliStorage.getFilms(new GhibliStorage.GhibliCallback<Film>() {
            @Override
            public void onSuccess(Film film) {
            }

            @Override
            public void onSuccessList(List<Film> films) {
                adapter.setList(films);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_films, container, false);
    }

    @Override
    public void openDetails(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FILM,id);
        FilmDetailsFragment filmDetailsFragment = new FilmDetailsFragment();
        filmDetailsFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,filmDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}