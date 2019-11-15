package com.example.catfacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class CatDetailActivity extends AppCompatActivity {
    private TextView catBreed;
    private ImageView catImage;
    private TextView catDescription;
    private TextView catOrigin;
    private TextView catTemperament;
    private TextView catLifeSpan;
    private TextView catWikiUrl;
    private TextView catWeight;
    private TextView catDogFriendly;
    public ImageView favoriteIcon;
    public boolean isFavourited = false;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    CatImage image = new CatImage();
    String urlToUse = "null";
    String imageUrl = "https://api.thecatapi.com/v1/images/search?breed_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_cat_detail);

        Intent intent = getIntent();

        final String name = intent.getExtras().getString("name");
        final String idForCat = intent.getExtras().getString("id");
        urlToUse = imageUrl + idForCat;
        final String image = intent.getExtras().getString("cfa_url");
        String description = intent.getExtras().getString("description");
        String origin = intent.getExtras().getString("origin");
        String temperament = intent.getExtras().getString("temperament");
        String lifeSpan = intent.getExtras().getString("life_span");
        String weight = intent.getExtras().getString("weight");
        final int dogFriendly = intent.getExtras().getInt("dog_friendly");
        String wikiUrl = intent.getExtras().getString("wiki_url");

        catBreed = findViewById(R.id.cat_breed);
        catImage = findViewById(R.id.cat_image);
        catDescription = findViewById(R.id.cat_description);
        catWeight = findViewById(R.id.cat_weight);
        catOrigin = findViewById(R.id.cat_origin);
        catLifeSpan = findViewById(R.id.cat_life_span);
        catWikiUrl = findViewById(R.id.cat_wiki_url);
        catDogFriendly = findViewById(R.id.cat_dog_friendly);
        catTemperament = findViewById(R.id.cat_temperament);
        favoriteIcon = findViewById(R.id.save_to_favorites);
        catBreed.setText(name);
        jsonrequest();

        catDescription.setText(description);
        catWeight.setText(weight);
        catDogFriendly.setText(String.valueOf(dogFriendly));
        catLifeSpan.setText(lifeSpan);
        catOrigin.setText(origin);
        catTemperament.setText(temperament);
        catWikiUrl.setText(wikiUrl);
        //TODO: trim weight

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavourited) {
                    favoriteIcon.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favoriteIcon.setImageResource(R.drawable.ic_favorite_black_24dp);


                    Cat.favouritesList.add(new Cat(idForCat, catBreed.getText().toString(), image, catDescription.getText().toString(),
                            catWeight.getText().toString(), catTemperament.getText().toString(), catOrigin.getText().toString(),
                            catLifeSpan.getText().toString(), catWikiUrl.getText().toString(), dogFriendly));

                }
                isFavourited = !isFavourited;
            }


            //TODO: stop doubling up items in favourtie list

        });


    }

    private void jsonrequest() {
        request = new JsonArrayRequest(urlToUse, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        image.setImageId(jsonObject.getString("id"));
                        image.setImageUrl(jsonObject.getString("url"));
                        String something = image.getImageUrl();
                        Glide.with(CatDetailActivity.this).load(image.getImageUrl()).into(catImage);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}
