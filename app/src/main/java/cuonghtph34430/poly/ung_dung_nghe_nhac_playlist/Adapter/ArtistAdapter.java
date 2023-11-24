package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    public interface OnArtistGlideClickListener {
        void onArtistGlideClick(int artistId,String artistTitle,int artistCover);
    }
    private List<CaSi> artistUrls; // List of URLs of artist images
    private Context context;
    private OnArtistGlideClickListener onArtistGlideClickListener;

    public ArtistAdapter(List<CaSi> artistUrls, Context context) {
        this.artistUrls = artistUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CaSi caSi = artistUrls.get(position);
        // Load album image using Glide or any image loading library
        Glide.with(holder.itemView.getContext())
                .load(caSi.getAnhCaSi())
                .placeholder(R.drawable.anh_nen) // Placeholder image while loading
                .into(holder.artistImage);
        holder.itemView.setOnClickListener(v -> {
            if (onArtistGlideClickListener != null) {
                onArtistGlideClickListener.onArtistGlideClick(caSi.getIdCaSi(),caSi.getTenCaSi(),caSi.getAnhCaSi());
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView artistImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistImage = itemView.findViewById(R.id.artistImage);
        }
    }
}
