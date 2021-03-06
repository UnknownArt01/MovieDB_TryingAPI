package com.example.moviedb.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {

    private Context context;
    private List<NowPlaying.Results> listPlaying;
    private List<NowPlaying.Results> getListPlaying(){
        return listPlaying;
    }
    public NowPlayingAdapter(Context context){
        this.context = context;
    }

    public void setListPlaying(List<NowPlaying.Results> listPlaying){
        this.listPlaying = listPlaying;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);

        return new NowPlayingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final NowPlaying.Results results = getListPlaying().get(position);
        holder.title_card_new_playing.setText(results.getTitle());
        holder.overview_card_now_playing.setText(results.getOverview());
        holder.release_card_now_playing.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_card_now_playing);

        holder.card_nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movie_id", ""+results.getId());
                Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_movieDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListPlaying().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_card_now_playing;
        TextView title_card_new_playing, overview_card_now_playing, release_card_now_playing;
        CardView card_nowplaying;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            img_card_now_playing = itemView.findViewById(R.id.img_card_now_playing);
            title_card_new_playing = itemView.findViewById(R.id.title_card_new_playing);
            overview_card_now_playing = itemView.findViewById(R.id.overview_card_now_playing);
            release_card_now_playing = itemView.findViewById(R.id.release_card_now_playing);
            card_nowplaying = itemView.findViewById(R.id.card_nowplaying);

        }
    }
}
