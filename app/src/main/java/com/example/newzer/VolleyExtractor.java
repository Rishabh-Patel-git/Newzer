package com.example.newzer;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class VolleyExtractor {
    private static ArrayList<NewsClass> mainList = new ArrayList<>();

    private VolleyExtractor() {

    }

    public interface VolleyOnEventListener {
        public void onSuccess(String response);

        public void onFailure(Exception e);
    }

    public static void fetchData(Context context, String url, final VolleyOnEventListener mCallBack) {
        RequestQueue myRequest = Volley.newRequestQueue(context);


        StringRequest JsonRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                mCallBack.onSuccess(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ris", "error " + error.toString() + " " + error.networkResponse.statusCode);
            }
        });

        myRequest.add(JsonRequest);
    }

    public static ArrayList<NewsClass> Utility(String s) {
        ArrayList<NewsClass> utilityList = new ArrayList<>();


        try {
            JSONObject obj1 = new JSONObject(s);
            JSONObject obj = obj1.getJSONObject("response");
            JSONArray mArticles = obj.getJSONArray("results");
            for (int i = 0; i < mArticles.length(); i++) {
                JSONObject currentNews = mArticles.getJSONObject(i);
                String mAuthor = currentNews.getString("pillarName");
                String mTitle = currentNews.getString("webTitle");
                String mDescription = currentNews.getString("pillarId");
                String mUrl = currentNews.getString("webUrl");
                // String mImageUrl = currentNews.getString("urlToImage");
                String mDate = currentNews.getString("webPublicationDate");
                NewsClass news = new NewsClass(mAuthor, mTitle, mDescription, mUrl, mDate);

                utilityList.add(news);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return utilityList;
    }

}
