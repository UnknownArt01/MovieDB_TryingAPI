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
import com.example.moviedb.R;
import com.example.moviedb.adapter.NewNowPlayingAdapter;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.viewmodel.MovieVM;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
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

    private RecyclerView rv_nowplaying;
    private MovieVM viewmodel;
    private int page = 1;
    public List<NowPlaying.Results> nowplayinglist = new ArrayList<>();//new
    private ProgressDialog progressBar;
    private boolean loading = false;
    private NewNowPlayingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        progressBar = ProgressDialog.show(getActivity(),"Now Loading", "Loading the data, Please wait....!", true);

        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rv_nowplaying = view.findViewById(R.id.rv_nowplaying_fragment);
        viewmodel = new ViewModelProvider(NowPlayingFragment.this).get(MovieVM.class);
        viewmodel.getNowPlaying(page);
        viewmodel.getResultGetNowPlaying().observe(getViewLifecycleOwner(), showNowPlaying);
        //dipindah biar gak buat adapter baru
        rv_nowplaying.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewNowPlayingAdapter(getActivity());
        adapter.setListPlaying(nowplayinglist);
        rv_nowplaying.setAdapter(adapter);
        //end
        //scoll view
        rv_nowplaying.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager scroll = (LinearLayoutManager) rv_nowplaying.getLayoutManager();
                if (loading == false){
                    if (scroll != null && scroll.findLastCompletelyVisibleItemPosition() == nowplayinglist.size() -1){
                        loading = true;
                        nowplayinglist.add(null);
                        nowplayinglist.remove(null);
                        page ++;
                        viewmodel.getNowPlaying(page);
                        viewmodel.getResultGetNowPlaying().observe(getViewLifecycleOwner(), showNowPlaying);
                        loading = false;
                    }
                }
            }
        });
        //end scrollview
        return view;

    }
    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            //new
            nowplayinglist.addAll(nowPlaying.getResults());
            adapter.notifyDataSetChanged();
            //end

            if (nowplayinglist != null){
                progressBar.dismiss();
            }
        }
    };

}