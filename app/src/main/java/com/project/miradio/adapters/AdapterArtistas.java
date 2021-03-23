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
import com.project.miradio.models.Artist;


import java.util.List;

/**
 *
 */
public class AdapterArtistas extends RecyclerView.Adapter<AdapterArtistas.ViewHolderData> {

    private List<Artist> items;
    private ArtistEventView artistEventView;

    public AdapterArtistas(List<Artist> items,ArtistEventView artistEventView) {
        this.items= items;
        this.artistEventView=artistEventView;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView textNameArtist,textAlbumsCount;
        ImageView iv_notify_type;
        ConstraintLayout item_artist;
        public ViewHolderData(View itemView) {
            super(itemView);
            textNameArtist = itemView.findViewById(R.id.txv_name_song);
            textAlbumsCount = itemView.findViewById(R.id.txv_albums_count);
            item_artist = itemView.findViewById(R.id.item_song);

        }
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_artist,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.textNameArtist.setText(items.get(position).getArtist());
        holder.textAlbumsCount.setText(""+items.get(position).albumSize());
        holder.item_artist.setOnClickListener((view)->{
            artistEventView.showAlbumsArtist(position);
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
