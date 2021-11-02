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
import com.example.moviedb.Model.Upcoming;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.CardViewHolder> {
    private Context context;
    private List<Upcoming.Results> listUpcoming;
    private List<Upcoming.Results> getListUpcoming(){
        return listUpcoming;
    }
    public UpcomingAdapter(Context context){
        this.context = context;
    }

    public void setListUpcoming(List<Upcoming.Results> listUpcoming){
        this.listUpcoming = listUpcoming;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);

        return new UpcomingAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        final Upcoming.Results results = getListUpcoming().get(position);
        holder.title_card_upcoming.setText(results.getTitle());
        holder.overview_card_upcoming.setText(results.getOverview());
        holder.release_card_upcoming.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_card_upcoming);

        holder.card_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movie_id", ""+results.getId());
                Navigation.findNavController(view).navigate(R.id.action_upcomingFragment_to_movieDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListUpcoming().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView img_card_upcoming;
        TextView title_card_upcoming, overview_card_upcoming, release_card_upcoming;
        CardView card_upcoming;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            img_card_upcoming = itemView.findViewById(R.id.img_card_now_playing);
            title_card_upcoming = itemView.findViewById(R.id.title_card_new_playing);
            overview_card_upcoming = itemView.findViewById(R.id.overview_card_now_playing);
            release_card_upcoming = itemView.findViewById(R.id.release_card_now_playing);
            card_upcoming = itemView.findViewById(R.id.card_nowplaying);


        }
    }
}
