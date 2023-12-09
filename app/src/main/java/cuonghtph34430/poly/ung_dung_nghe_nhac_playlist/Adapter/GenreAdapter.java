package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{
    private List<BaiHat> list;
    private Context context;

    public GenreAdapter(List<BaiHat> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = list.get(position);
        Glide.with(holder.itemView.getContext())
                .load(baiHat.getAnhBaiHat())
                .placeholder(R.drawable.anh_nen)
                .into(holder.item_rounded);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_rounded;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_rounded = itemView.findViewById(R.id.item_rounded);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                BaiHat clickedBaiHat = list.get(position);
                Intent openMusicPlayer = new Intent(context, Choinhac.class);
                openMusicPlayer.putExtra("nhac", clickedBaiHat);
                context.startActivity(openMusicPlayer);
            }
        }
    }
}
