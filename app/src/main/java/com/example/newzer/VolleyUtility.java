package com.example.newzer;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyUtility {
    private static ArrayList<NewsClass> mainList = new ArrayList<>();

    private VolleyUtility() {

    }

    public interface VolleyOnEventListener {
        public void onSuccess(String response);

        public void onFailure(Exception e);
    }

    public static void fetchData(Context context, String url, final VolleyUtility.VolleyOnEventListener mCallBack) {
        RequestQueue myRequest = Volley.newRequestQueue(context);


        JsonObjectRequest JsonRequest =  new  JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               mCallBack.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ris","error");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put( "User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        myRequest.add(JsonRequest);
    }

    public static ArrayList<NewsClass> jsonExtractor(String s) {
        ArrayList<NewsClass> utilityList = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray mArticles = jsonObject.getJSONArray("articles");
            for (int i = 0; i < mArticles.length(); i++) {
                JSONObject currentNews = mArticles.getJSONObject(i);
                String mAuthor = currentNews.getString("author");
                String mTitle = currentNews.getString("title");
                String mDescription = currentNews.getString("description");
                String mUrl = currentNews.getString("url");
                 String mImageUrl = currentNews.getString("urlToImage");
                String mDate = currentNews.getString("publishedAt");
                NewsClass news = new NewsClass(mAuthor, mTitle, mDescription, mUrl, mDate,mImageUrl);

                utilityList.add(news);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return utilityList;
    }
}
