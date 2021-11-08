package com.example.newzer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class TechnologyFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private SwipeRefreshLayout mSwipe;
    private ArrayList<NewsClass> mNews;
    private ProgressBar indicator;
    private final String TECHNOLOGY_URL = " https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=98d961fbf71e4da4a4d72cc957d9cea0";
    private String s = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_layout,container,false);
        mSwipe = rootView.findViewById(R.id.swipe_refresh);
        indicator = rootView.findViewById(R.id.loading_indicator);
        indicator.setVisibility(View.VISIBLE);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        VolleyUtility.fetchData(getContext(), TECHNOLOGY_URL, new VolleyUtility.VolleyOnEventListener() {
            @Override
            public void onSuccess(String response) {

                mNews = VolleyUtility.jsonExtractor(response);
                if (mNews != null) {
                    mAdapter = new RecyclerViewAdapter(getContext(), mNews);
                    mAdapter.notifyDataSetChanged();
                    indicator.setVisibility(View.GONE);
                    mRecyclerView.setAdapter(mAdapter);
                }

                mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mSwipe.setRefreshing(true);
                        mNews = VolleyUtility.jsonExtractor(response);

                        if (mNews != null) {
                            mAdapter = new RecyclerViewAdapter(getContext(), mNews);
                            mAdapter.notifyDataSetChanged();
                            indicator.setVisibility(View.GONE);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        mSwipe.setRefreshing(false);
                    }
                });


            }

            @Override
            public void onFailure(Exception e) {

            }
        });
            return rootView;
    }
}
