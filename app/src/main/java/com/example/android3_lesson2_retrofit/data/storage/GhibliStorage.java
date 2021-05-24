package com.example.android3_lesson2_retrofit.data.storage;

import android.util.Log;

import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.model.People;
import com.example.android3_lesson2_retrofit.data.remote.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GhibliStorage {

    public void getFilm(String filmId, GhibliCallback<Film> callback) {
        RetrofitBuilder.getInstance().getFilm(filmId).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Request error...");
                }
            }
            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getFilms(GhibliCallback<Film> callback){
        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessList(response.body());

                } else {
                    callback.onFailure("Request error...");
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getPeople (String peopleId, GhibliCallback<People> callback){
        RetrofitBuilder.getInstance().getPeople(peopleId).enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Request error...");
                }
            }
            @Override
            public void onFailure(Call<People> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public interface GhibliCallback<Data> {
        void onSuccess(Data data);
        default void onSuccessList(List<Data> dataList){

        };
        default void onFailure(String errorMsg){
            Log.d("tag", errorMsg);
        };
    }
}
