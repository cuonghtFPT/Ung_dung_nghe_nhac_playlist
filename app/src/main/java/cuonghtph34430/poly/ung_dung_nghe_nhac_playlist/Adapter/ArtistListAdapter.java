package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ViewHolder>{
    public interface OnArtistClickListener {
        void onArtistClick(int artistId,String artistTitle,int artistCover);
    }
    private final List<CaSi> artisStrings;
    private final Context context;
    private final OnArtistClickListener onArtistClickListener;

    public ArtistListAdapter(List<CaSi> artisStrings, Context context, OnArtistClickListener onArtistClickListener) {
        this.artisStrings = artisStrings;
        this.context = context;
        this.onArtistClickListener = onArtistClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_casi, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CaSi caSi = artisStrings.get(position);
        holder.txtArtist.setText(caSi.getTenCaSi());
        holder.imgArtist.setImageResource(caSi.getAnhCaSi());
        holder.itemView.setOnClickListener(v -> {
            if (onArtistClickListener != null) {
                onArtistClickListener.onArtistClick(caSi.getIdCaSi(),caSi.getTenCaSi(),caSi.getAnhCaSi());
            }
        });
    }

    @Override
    public int getItemCount() {
        return artisStrings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtArtist;
        ImageView imgArtist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArtist = itemView.findViewById(R.id.imgArtist);
            txtArtist = itemView.findViewById(R.id.txtArtist);

        }
    }
}
