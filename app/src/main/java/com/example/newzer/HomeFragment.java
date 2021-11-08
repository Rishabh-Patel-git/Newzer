package com.example.newzer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<NewsClass> mNews;
    ProgressBar indicator;
    private final String url = "https://content.guardianapis.com/search?page=10&api-key=1a667425-24fc-4ef0-a8e8-4d85fec302ae";
    private static final String TAG = "MainActivity";
    String s = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        indicator = rootView.findViewById(R.id.loading_indicator);
        indicator.setVisibility(View.VISIBLE);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        VolleyExtractor.fetchData(getContext(), url, new VolleyExtractor.VolleyOnEventListener() {
            @Override
            public void onSuccess(String response) {
                Log.d("ris", response);

                mNews = VolleyExtractor.Utility(response);
                if (mNews != null) {
                   // mNews.add(new NewsClass("au","ti","des","url","date"));
                    mAdapter = new RecyclerViewAdapter(getContext(), mNews);
                    mAdapter.notifyDataSetChanged();
                    indicator.setVisibility(View.GONE);

                    mRecyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });



        return rootView;
    }
}
