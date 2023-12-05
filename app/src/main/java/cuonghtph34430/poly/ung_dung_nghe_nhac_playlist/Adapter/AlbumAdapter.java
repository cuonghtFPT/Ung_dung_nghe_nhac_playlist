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

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    public interface OnAlbumGlideClickListener {
        void onAlbumGlideClick(int albumId,String albumTitle,int albumCover);
    }
    private final List<Album> albumUrls; // List of URLs of artist images
    private final Context context;
    private final OnAlbumGlideClickListener onAlbumGlideClickListener;

    public AlbumAdapter(Context context, List<Album> albumUrls,OnAlbumGlideClickListener onAlbumGlideClickListener) {
        this.context = context;
        this.albumUrls = albumUrls;
        this.onAlbumGlideClickListener = onAlbumGlideClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumUrls.get(position);
        Glide.with(holder.itemView.getContext())
                .load(album.getAnhAlbum())
                .placeholder(R.drawable.anh_nen)
                .into(holder.artistImage);
        holder.itemView.setOnClickListener(v -> {
            if (onAlbumGlideClickListener != null) {
                onAlbumGlideClickListener.onAlbumGlideClick(album.getIdAlbum(),album.getTenAlbum(),album.getAnhAlbum());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView artistImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistImage = itemView.findViewById(R.id.artistImage);
        }
    }
}
