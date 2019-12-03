package com.example.carsapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;


public class VideoFragment extends Fragment {

    public View v;
    private RecyclerView recyclerView;
    private static Vector<Youtube> videos = new Vector<Youtube>();
    private static int videoShow = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));

        if(videoShow == 0) {
            videos.add(new Youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d9yFY4GE9Qk\" frameborder=\"0\" allowfullscreen></iframe>"));
            videoShow = 1;
        }
        VideoAdapter videoAdapter = new VideoAdapter(videos);
        recyclerView.setAdapter(videoAdapter);

        return v;
    }
}