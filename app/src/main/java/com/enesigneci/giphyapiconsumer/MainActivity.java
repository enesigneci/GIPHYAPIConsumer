package com.enesigneci.giphyapiconsumer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.enesigneci.giphyapiconsumer.Giphy.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String API_KEY="YOUR API KEY";
        Button btnFetchGif;
        final EditText etGifName;
        btnFetchGif= (Button) findViewById(R.id.btn_fetch_gif);
        etGifName= (EditText) findViewById(R.id.et_gif_name);
        btnFetchGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.giphy.com/v1/gifs/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                IGiphyService service = retrofit.create(IGiphyService.class);
                service.getGifs(API_KEY,etGifName.getText().toString(),1).enqueue(new Callback<GiphyService>() {
                    @Override
                    public void onResponse(Call<GiphyService> call, Response<GiphyService> response) {
                        ImageView ivCurrent= (ImageView) findViewById(R.id.iv_current);
                        Datum currentDatum;
                        currentDatum=response.body().getData().get(0);
                        Glide.with(getApplicationContext()).load(currentDatum.getImages().getFixedHeight().getUrl()).asGif().into(ivCurrent);
                        Log.d("RetrofitResponse",currentDatum.getEmbedUrl());
                    }

                    @Override
                    public void onFailure(Call<GiphyService> call, Throwable t) {

                    }
                });
            }
        });
//        http://api.giphy.com/v1/gifs/search?q=ryan+gosling&api_key=YOUR_API_KEY&limit=5
    }
}
