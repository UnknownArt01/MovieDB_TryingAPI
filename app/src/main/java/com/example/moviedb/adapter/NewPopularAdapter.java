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
import com.example.moviedb.Model.Popular;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;

import java.util.List;

public class NewPopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Popular.Results> listPopular;
    private List<Popular.Results> getListPopular(){
        return listPopular;
    }
    public NewPopularAdapter(Context context){
        this.context = context;
    }
    private final int item = 1, loading = 0;

    public void setListPopular(List<Popular.Results> listPopular){
        this.listPopular = listPopular;
    }

    @Override
    public int getItemViewType(int position) {
        return listPopular.get(position) == null ? loading : item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == item){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
            return new holderCard3(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading, parent, false);
            return new loading(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof holderCard3){
            final Popular.Results results = getListPopular().get(position);
            ((holderCard3) holder).title_card_new_playing.setText(results.getTitle());
            ((holderCard3) holder).overview_card_now_playing.setText(results.getOverview());
            ((holderCard3) holder).release_card_now_playing.setText(results.getRelease_date());
            Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(((holderCard3) holder).img_card_now_playing);

            ((holderCard3) holder).card_nowplaying.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movie_id", ""+results.getId());
                    Navigation.findNavController(view).navigate(R.id.action_PopularFragment_to_movieDetailFragment, bundle);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return getListPopular().size();
    }
    public class holderCard3 extends RecyclerView.ViewHolder{

        ImageView img_card_now_playing;
        TextView title_card_new_playing, overview_card_now_playing, release_card_now_playing;
        CardView card_nowplaying;

        public holderCard3(@NonNull View itemView) {
            super(itemView);
            img_card_now_playing = itemView.findViewById(R.id.img_card_now_playing);
            title_card_new_playing = itemView.findViewById(R.id.title_card_new_playing);
            overview_card_now_playing = itemView.findViewById(R.id.overview_card_now_playing);
            release_card_now_playing = itemView.findViewById(R.id.release_card_now_playing);
            card_nowplaying = itemView.findViewById(R.id.card_nowplaying);
        }
    }
    public class loading extends RecyclerView.ViewHolder{
        public loading(@NonNull View itemView) {
            super(itemView);
        }
    }
}
