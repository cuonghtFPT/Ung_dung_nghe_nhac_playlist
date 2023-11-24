package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder>  {
    public interface OnAlbumClickListener {
        void onAlbumClick(int albumId,String albumTitle,int albumCover);
    }
    private List<Album> albumList;
    private Context context;
    private OnAlbumClickListener onAlbumClickListener;

    public AlbumListAdapter(List<Album> albumList, Context context, OnAlbumClickListener onAlbumClickListener) {
        this.albumList = albumList;
        this.context = context;
        this.onAlbumClickListener = onAlbumClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumImage.setImageResource(album.getAnhAlbum());

        holder.itemView.setOnClickListener(v -> {
            if (onAlbumClickListener != null) {
                onAlbumClickListener.onAlbumClick(album.getIdAlbum(), album.getTenAlbum(), album.getAnhAlbum());
            }
        });


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView albumImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.albumImage);
        }
    }
}
