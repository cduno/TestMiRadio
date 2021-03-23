package com.project.miradio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.project.miradio.adapters.AdapterArtistas;
import com.project.miradio.adapters.ArtistEventView;
import com.project.miradio.models.Artist;
import com.project.miradio.models.Data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ArtistEventView {

    private RecyclerView recyclerView;
    private Button btnFavoriteSong;
    private InputStream XmlFileInputStream;
    private Data datajson;
    private TextView et_name_user,txv_escuchando,txv_name_song_listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mi Radio");
        btnFavoriteSong= findViewById(R.id.btn_favorite_song);
        et_name_user= findViewById(R.id.et_name_user);
        txv_escuchando= findViewById(R.id.txv_escuchando);
        txv_name_song_listen= findViewById(R.id.txv_name_song_listen);

        btnFavoriteSong.setOnClickListener(this);
        recyclerView= findViewById(R.id.recy_artist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

        XmlFileInputStream = getResources().openRawResource(R.raw.data);
        getIntentData();
    }

    private void readJson(){
        String jsonString = readTextFile(XmlFileInputStream);
        Gson gson = new Gson();
        datajson=  gson.fromJson(jsonString, Data.class);
        loadDataList();
    }

    private void loadDataList(){
        List<Artist> items=datajson.getArtists();
        AdapterArtistas adapter= new AdapterArtistas(items,this);
        recyclerView.setAdapter(adapter);

        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation
                (recyclerView.getContext(),R.anim.animlt_up);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        et_name_user.setText(bundle.getString("name"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_favorite_song:
                readJson();
                btnFavoriteSong.setEnabled(false);
                break;
        }
    }

    @Override
    public void showAlbumsArtist(int artist) {
        Intent intent = new Intent(this,
                AlbumsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("artist", datajson.getArtists().get(artist));

        intent.putExtras(bundle);

        this.startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {

                case 2:
                   if(!data.getStringExtra("song").equals("")){
                       txv_escuchando.setVisibility(View.VISIBLE);
                       txv_name_song_listen.setVisibility(View.VISIBLE);
                     txv_name_song_listen.setText(data.getStringExtra("song"));
                   }
                break;
            }
        }
    }
}