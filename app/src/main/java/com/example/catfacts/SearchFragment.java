package com.example.catfacts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private final String url = "https://api.thecatapi.com/v1/breeds?api_key=3d83a199-cc1b-4624-a0d2-9e724fcd73a1";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private EditText editText;

    public List<Cat> catList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CatRowItemAdapter adapter = new CatRowItemAdapter(catList, getContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        editText = view.findViewById(R.id.edit_text);
        jsonrequest();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;

    }

    private void filter(String text) {
        ArrayList<Cat> filteredList = new ArrayList<>();
        for (Cat catItem : catList) {
            if (catItem.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(catItem);

            }
        }
        setUpRecyclerView(filteredList);
        adapter.filterList(filteredList);
    }

    private void jsonrequest() {
        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        Cat cat = new Cat();
                        cat.setName(jsonObject.getString("name"));
                        cat.setCatId(jsonObject.getString("id"));
                        cat.setImage(jsonObject.getString("cfa_url"));
                        cat.setDescription(jsonObject.getString("description"));
                        cat.setDog_friendly(jsonObject.getInt("dog_friendly"));
                        cat.setLife_span(jsonObject.getString("life_span"));
                        cat.setOrigin(jsonObject.getString("origin"));
                        cat.setWeight(jsonObject.getString("weight"));
                        cat.setTemperament(jsonObject.getString("temperament"));
                        cat.setWikipedia_url(jsonObject.getString("wikipedia_url"));
                        catList.add(cat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(catList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }


    public void setUpRecyclerView(List<Cat> catList) {
        CatRowItemAdapter myAdapter = new CatRowItemAdapter(catList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

    }

}


