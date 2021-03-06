package com.example.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.Model.Upcoming;
import com.example.moviedb.R;
import com.example.moviedb.adapter.NewUpcomingAdapter;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.adapter.UpcomingAdapter;
import com.example.moviedb.viewmodel.MovieVM;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView rv_upcoming;
    private MovieVM viewmodel;
    private int page = 1;
    public List<Upcoming.Results> upcominglist = new ArrayList<>();//new
    private ProgressDialog progressBar;
    private NewUpcomingAdapter adapter;
    private boolean loading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        progressBar = ProgressDialog.show(getActivity(),"Now Loading", "Loading the data, Please wait....!", true);

        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        rv_upcoming = view.findViewById(R.id.rv_upcoming_fragment);
        viewmodel = new ViewModelProvider(UpcomingFragment.this).get(MovieVM.class);
        viewmodel.getUpcoming(page);
        viewmodel.getResultGetUpcoming().observe(getViewLifecycleOwner(), showUpcoming);
        // dipindah
        rv_upcoming.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewUpcomingAdapter(getActivity());
        adapter.setListUpcoming(upcominglist);
        rv_upcoming.setAdapter(adapter);
        //end
        //scroll view
        rv_upcoming.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager scroll = (LinearLayoutManager) rv_upcoming.getLayoutManager();

                if (loading == false){
                    if (scroll != null && scroll.findLastCompletelyVisibleItemPosition() == upcominglist.size() -1){
                        loading = true;
                        upcominglist.add(null);
                        upcominglist.remove(null);
                        page ++;
                        viewmodel.getUpcoming(page);
                        viewmodel.getResultGetUpcoming().observe(getViewLifecycleOwner(), showUpcoming);
                        loading = false;
                    }
                }
            }
        });
        //end scroll view

        return view;
    }
    private Observer<Upcoming> showUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {

            //new
            upcominglist.addAll(upcoming.getResults());
            adapter.notifyDataSetChanged();
            //end of new

            if (upcoming != null){
                progressBar.dismiss();
            }
        }

    };
}