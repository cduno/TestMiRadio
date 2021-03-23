package com.project.miradio.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.project.miradio.R;
import com.project.miradio.models.Song;

import java.util.List;

/**
 *
 */
public class AdapterSongs extends RecyclerView.Adapter<AdapterSongs.ViewHolderData> {

    List<Song> items;
    private SongEventView songEventView;

    public AdapterSongs(List<Song> items, SongEventView songEventView) {
        this.items= items;
        this.songEventView=songEventView;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView txv_name_song;
        ConstraintLayout item_song;
        public ViewHolderData(View itemView) {
            super(itemView);
            txv_name_song = itemView.findViewById(R.id.txv_name_song);

            item_song = itemView.findViewById(R.id.item_song);

        }
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album_song,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.txv_name_song.setText(items.get(position).getTitle());

        holder.item_song.setOnClickListener((view)->{
            songEventView.playSong(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
