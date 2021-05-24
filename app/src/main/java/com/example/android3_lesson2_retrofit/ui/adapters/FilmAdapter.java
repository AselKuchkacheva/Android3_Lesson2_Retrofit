package com.example.android3_lesson2_retrofit.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private final List<Film> list = new ArrayList<>();
    private TitleListener titleListener;

    @NonNull
    @Override
    public FilmAdapter.FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.FilmViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Film> films) {
        list.addAll(films);
        notifyDataSetChanged();
    }

    public void setTitleListener(TitleListener titleListener){
        this.titleListener = titleListener;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvNumber;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_film_title);
            tvNumber = itemView.findViewById(R.id.tv_pos_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleListener.openDetails(list.get(getAdapterPosition()).getId());
                }
            });

        }

        public void onBind(Film film) {
            tvTitle.setText(film.getTitle());
            tvNumber.setText((1 + getAdapterPosition()) + "");
        }
    }

    public interface TitleListener{
        void openDetails(String id);
    }

}
