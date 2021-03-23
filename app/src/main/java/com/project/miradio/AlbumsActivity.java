package com.project.miradio;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.project.miradio.adapters.AdapterArtistas;
import com.project.miradio.adapters.AdapterSongs;
import com.project.miradio.adapters.SongEventView;
import com.project.miradio.models.Artist;
import com.project.miradio.models.Song;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlbumsActivity extends AppCompatActivity implements SongEventView {
    private RecyclerView recyclerView;
    private Spinner spinnerAlbums;
    private RecyclerView recy_songs;
    private Artist artist;
    private int poslistsong, posSong;
    private TextView txv_name_artist_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Album/Canciones");
        spinnerAlbums= findViewById(R.id.spinner_albums);
        txv_name_artist_selected= findViewById(R.id.txv_name_artist_selected);
        spinnerAlbums.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                loadDataList(i);
                //String text = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        recyclerView= findViewById(R.id.recy_songs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        getIntentData();
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        artist=(Artist) bundle.getSerializable("artist");
       // artist.getArtist()
        txv_name_artist_selected.setText( artist.getArtist());
        ArrayList<String> items=new ArrayList<String>();
        for(int i=0;i<artist.albumSize();i++){
            items.add(artist.getAlbums().get(i).getName()+" "+artist.getAlbums().get(i).getYear());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        spinnerAlbums.setAdapter(adapter);
    }

    private void loadDataList(int pos){
        poslistsong=pos;
        List<Song> items=artist.getAlbums().get(pos).getSongs();
        AdapterSongs adapter= new AdapterSongs(items,this);
        recyclerView.setAdapter(adapter);

        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation
                (recyclerView.getContext(),R.anim.animlt_up);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void playSong(int song) {

        Intent intent = new Intent();
        intent.putExtra("song",  artist.getAlbums().get(poslistsong).getSongs().get(song).getTitle());
        setResult(RESULT_OK, intent);
        finish();
    }
}