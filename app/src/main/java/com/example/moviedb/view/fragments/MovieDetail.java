package com.example.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviedb.Model.Movies;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.viewmodel.MovieVM;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetail newInstance(String param1, String param2) {
        MovieDetail fragment = new MovieDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = ProgressDialog.show(getActivity(),"Now Loading", "Loading the data, Please wait....!", true);

    }

    private TextView txt_details_title, txt_details_rating, txt_relase, txt_overview, txt_tagline, tvt_avg_vote, txt_popularity;
    private ImageView img_details_poster, img_backdrop;

    private MovieVM viewdetails;
    private ProgressDialog progressBar;
    private ConstraintLayout cons_movieDetails;
    private ChipGroup chip_genres;
    private LinearLayout llhsv_companies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        txt_details_title = view.findViewById(R.id.txt_details_title);
        txt_details_rating = view.findViewById(R.id.txt_details_rating);
        img_details_poster = view.findViewById(R.id.img_details_poster);
        txt_relase = view.findViewById(R.id.txt_relase);
        txt_overview = view.findViewById(R.id.txt_overview);
        cons_movieDetails = view.findViewById(R.id.cons_movieDetails);
        chip_genres = view.findViewById(R.id.chip_genres);
        llhsv_companies = view.findViewById(R.id.llhsv_companies);
        img_backdrop = view.findViewById(R.id.imageView_backdrop);
        txt_tagline = view.findViewById(R.id.txt_tagline);
        tvt_avg_vote = view.findViewById(R.id.tvt_avg_vote);
        txt_popularity = view.findViewById(R.id.txt_popularity);

        String id = getArguments().getString("movie_id").toString();
        viewdetails = new ViewModelProvider(getActivity()).get(MovieVM.class);
        viewdetails.getMovieById(id);
        viewdetails.getResultGetMovieByID().observe(getActivity(), showDetail);
        cons_movieDetails.setVisibility(View.GONE);

        return view;
    }

    private Observer<Movies> showDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            //poster
            String poster = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(getActivity()).load(poster).into(img_details_poster);
            //poster
            //backdrop
            String backdrop = Const.IMG_URL + movies.getBackdrop_path();
            Glide.with(getActivity()).load(backdrop).into(img_backdrop); // gak boleh coing
            //backdrop
            txt_details_title.setText(movies.getTitle());
            txt_details_rating.setText(String.valueOf(movies.getVote_average()));
            txt_relase.setText(movies.getRelease_date());
            txt_overview.setText(movies.getOverview());
            txt_tagline.setText(movies.getTagline());
            tvt_avg_vote.setText("( " +String.valueOf(movies.getVote_count()) + " )");
            txt_popularity.setText(String.valueOf(movies.getPopularity()));

//            for (int con = 0; con < movies.getGenres().size() ; con++){
//                if (con == movies.getGenres().size() - 1){
//                    genres += movies.getGenres().get(con).getName();
//
//                }else{
//                    genres += movies.getGenres().get(con).getName()+" ,";
//                }
//            }
//            txt_genres.setText(genres);

            //new code dot genres and companies
            Chip chipnew;
            for (int con = 0; con < movies.getGenres().size(); con++){
                chipnew = new Chip(chip_genres.getContext());
                chipnew.setChipEndPadding(3);
                chipnew.setChipStartPadding(3);
                chipnew.setText(movies.getGenres().get(con).getName());
                chip_genres.addView(chipnew);
            }
            for (int pict = 0; pict < movies.getProduction_companies().size(); pict++){
                ImageView imageView = new ImageView(llhsv_companies.getContext());
                String logo = Const.IMG_URL + movies.getProduction_companies().get(pict).getLogo_path();
                String name = movies.getProduction_companies().get(pict).getName();
                if (movies.getProduction_companies().get(pict).getLogo_path() == null){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.default_pict, getActivity().getTheme()));
                } else if (logo != "https://image.tmdb.org/t/p/w500/null") {
                    Glide.with(getActivity()).load(logo).into(imageView);
                }
                LinearLayout.LayoutParams sizelinear = new LinearLayout.LayoutParams(250,250);
                sizelinear.setMargins(30,30,30,30);
                imageView.setLayoutParams(sizelinear);
                llhsv_companies.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snack = Snackbar.make(view, name, Snackbar.LENGTH_SHORT);
                        snack.setAnchorView(R.id.botNav_mainMenu);
                        snack.show();
                    }
                });
            }
            //end of new code dot genres and companies



            if (movies!= null){
                progressBar.dismiss();
                cons_movieDetails.setVisibility(View.VISIBLE);
            }
        }
    };
}