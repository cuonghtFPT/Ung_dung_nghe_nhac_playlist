package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.AlbumAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ArtistAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.MusicListAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.AudioModel;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.ListSong;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;


public class BlankFragment extends Fragment implements ArtistAdapter.OnArtistGlideClickListener, AlbumAdapter.OnAlbumGlideClickListener{
    private ViewPager2 viewPager;
    private int[] images = {R.drawable.bgc, R.drawable.pl2, R.drawable.sinhto};
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 2000;
    private final long PERIOD_MS = 3000;
    MusicListAdapter musicListAdapter;
    ArrayList<AudioModel> songList;
    RecyclerView recycle1,recycle2;
    Context context;
    ArtistAdapter artistAdapter;
    AlbumAdapter albumAdapter;
    ListSongAdapter nhacAdapter;
    ListSong nhac;
    ArrayList<ListSong> listN;
    ListView lv_nhac;
    View view;
    TextView btnXemAlbum,btnXemNgheSi;
    FragmentContainer fragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(images);
        viewPager.setAdapter(adapter);
        btnXemAlbum = view.findViewById(R.id.btnXemAlbum);
        btnXemNgheSi = view.findViewById(R.id.btnXemNgheSi);
        recycle1 = view.findViewById(R.id.recycle1);
        recycle2 = view.findViewById(R.id.recycle2);
        btnXemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentB = new BlankFragment5();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment1, fragmentB);
                transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                transaction.commit();
            }
        });
        btnXemNgheSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentB = new BlankFragment4();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment1, fragmentB);
                transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                transaction.commit();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle1.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle2.setLayoutManager(linearLayoutManager);
        List<CaSi> artistUrls = new ArrayList<>();
        artistUrls.add(new CaSi(1,"Ca Sĩ 1",R.drawable.anh_4));
        artistUrls.add(new CaSi(2,"Ca Sĩ 2",R.drawable.anh_2));
        artistUrls.add(new CaSi(3,"Ca Sĩ 3",R.drawable.anh_3));
        artistUrls.add(new CaSi(4,"Ca Sĩ 4",R.drawable.anh_1));
        artistUrls.add(new CaSi(5,"Ca Sĩ 5",R.drawable.anh_3));
        artistAdapter = new ArtistAdapter(artistUrls,getContext());
        recycle2.setAdapter(artistAdapter);
        List<Album> albumUrls = new ArrayList<>();
        albumUrls.add(new Album(1,"Album 1",R.drawable.anh_3));
        albumUrls.add(new Album(2,"Album 2",R.drawable.anh_1));
        albumUrls.add(new Album(3,"Album 3",R.drawable.anh_2));
        albumUrls.add(new Album(4,"Album 4",R.drawable.anh_4));
        albumUrls.add(new Album(5,"Album 5",R.drawable.anh_3));
        albumAdapter = new AlbumAdapter(getContext(), albumUrls);
        recycle1.setAdapter(albumAdapter);

        lv_nhac = view.findViewById(R.id.listView);

        context = getContext(); // hoặc getActivity()
        listN = new ArrayList<>();
        listN.add(new ListSong("Em đồng ý","kkkkk",R.drawable.anh_1,R.raw.emdongy,1,1));
        listN.add(new ListSong("Gió","ppppp",R.drawable.anh_2,R.raw.gio_lofi,2,2));
        listN.add(new ListSong("Là anh","gggggg",R.drawable.anh_3,R.raw.la_anh,3,3));
        listN.add(new ListSong("Một người đánh mất một người","sssss",R.drawable.anh_4,R.raw.mot_nguoi_danh_mat_mot_nguoi,4,4));
        listN.add(new ListSong("Từng quen","ooooo",R.drawable.anh_5,R.raw.tung_quen,5,5));

        nhacAdapter = new ListSongAdapter(context,listN);
        lv_nhac.setAdapter(nhacAdapter);

        lv_nhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListSong nhac = listN.get(position);
                Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
                openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng ListSong thay vì chỉ truyền file nhạc
                startActivity(openMusicPlayer);
            }
        });

        if (!checkPermission()) {
            requestPermission();
        }

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        // Auto-scrolling task
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (currentPage == images.length) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // Timer to schedule auto-scrolling task
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onAlbumGlideClick(int albumId, String albumTitle, int albumCover) {
        BlankFragment3 fragment3 = new BlankFragment3();
        Bundle args = new Bundle();
        args.putInt("albumId", albumId);
        args.putString("albumTitle", albumTitle);
        args.putInt("albumCover", albumCover);
        fragment3.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment3);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onArtistGlideClick(int artistId, String artistTitle, int artistCover) {
        BlankFragment3 fragment3 = new BlankFragment3();
        Bundle args = new Bundle();
        args.putInt("artistId", artistId);
        args.putString("artistTitle", artistTitle);
        args.putInt("artistCover", artistCover);
        fragment3.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment3);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private int[] images;

        ImageAdapter(int[] images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.imageView.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            ImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
    boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "READ PERMISSION IS REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT).show();
        } else
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(recyclerView!=null){
//            recyclerView.setAdapter(new MusicListAdapter(getContext(), songList));
//        }
//    }
}