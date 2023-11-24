package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.ListSong;

public class Choinhac extends AppCompatActivity {

    TextView tvTime, tvDuration, tvTacgia, tvNhac;
    SeekBar sbTime, sbVolume;
    ImageView imgPlay;
    ImageButton imgLui,imgTien;

    MediaPlayer musicPlayer;
    boolean isPlaying = false;
    Handler handler;

    List<ListSong> songList; // Danh sách các bài hát
    int currentSongIndex = 0; // Vị trí hiện tại trong danh sách

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_nhac);
        tvNhac = findViewById(R.id.tv_tenNhac);
        tvTacgia = findViewById(R.id.tv_tenTacgia);
        tvTime = findViewById(R.id.tv_time);
        tvDuration = findViewById(R.id.tv_duration);
        sbTime = findViewById(R.id.sb_time);
        sbVolume = findViewById(R.id.sb_volume);
        imgPlay = findViewById(R.id.img_play);
        imgLui = findViewById(R.id.imgLui);
        imgTien = findViewById(R.id.imgTien);

        // Khởi tạo songList nếu chưa được khởi tạo
        if (songList == null) {
            songList = new ArrayList<>();
            songList.add(new ListSong("Em đồng ý", "kkkkk", R.drawable.anh_1, R.raw.emdongy,1,1));
            songList.add(new ListSong("Gió", "ppppp", R.drawable.anh_2, R.raw.gio_lofi,1,1));
            songList.add(new ListSong("Là anh", "gggggg", R.drawable.anh_3, R.raw.la_anh,1,1));
            songList.add(new ListSong("Một người đánh mất một người", "sssss", R.drawable.anh_4, R.raw.mot_nguoi_danh_mat_mot_nguoi,1,1));
            songList.add(new ListSong("Từng quen", "ooooo", R.drawable.anh_5, R.raw.tung_quen,1,1));
        }
        imgLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null && !songList.isEmpty()) {
                    currentSongIndex = (currentSongIndex - 1 + songList.size()) % songList.size();
                    playSelectedSong();
                }
            }
        });

        imgTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null && !songList.isEmpty()) {
                    currentSongIndex = (currentSongIndex + 1) % songList.size();
                    playSelectedSong();
                }
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null) {
                    if (musicPlayer.isPlaying()) {
                        musicPlayer.pause();
                        isPlaying = false;
                    } else {
                        musicPlayer.start();
                        isPlaying = true;
                    }
                    updatePlayButton();
                }
            }
        });

        releaseMediaPlayer();

        startUpdatingSeekBar();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("nhac")) {
            ListSong selectedSong = (ListSong) intent.getSerializableExtra("nhac");

            tvNhac.setText(selectedSong.getSong());
            tvTacgia.setText(selectedSong.getSinger());

            createAndStartMediaPlayer(selectedSong.getFile());

            String duration = millisecondsToString(musicPlayer.getDuration());
            if (tvDuration != null) {
                tvDuration.setText(duration);
            }

            sbTime.setMax(musicPlayer.getDuration());
        }

        sbVolume.setProgress(50);
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayer != null && !musicPlayer.isPlaying() && isPlaying) {
            musicPlayer.start();
            updatePlayButton();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
        }
    }

    @Override
    public void onBackPressed() {
        releaseMediaPlayer();
        finish();
    }

    private void releaseMediaPlayer() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        }
    }

    private void createAndStartMediaPlayer(int songResource) {
        musicPlayer = MediaPlayer.create(this, songResource);
        musicPlayer.setLooping(true);
        musicPlayer.setVolume(0.5f, 0.5f);
        musicPlayer.start();
        isPlaying = true;
        updatePlayButton();
    }

    private void startUpdatingSeekBar() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateSeekBar() {
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            int current = musicPlayer.getCurrentPosition();
            sbTime.setProgress(current);
            tvTime.setText(millisecondsToString(current));
        }
    }

    public String millisecondsToString(int time) {
        String elapsedTime = "";
        int minutes = time/ 1000 / 60;
        int seconds = time / 1000 % 60;
        elapsedTime = minutes + ":";
        if (seconds < 10) {
            elapsedTime += "0";
        }
        elapsedTime += seconds;

        return elapsedTime;
    }

    private void updatePlayButton() {
        if (isPlaying) {
            imgPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
        } else {
            imgPlay.setImageResource(R.drawable.baseline_play_circle_outline_24);
        }
    }

    private void playSelectedSong() {
        if (!songList.isEmpty() && currentSongIndex >= 0 && currentSongIndex < songList.size()) {
            ListSong selectedSong = songList.get(currentSongIndex);
            tvNhac.setText(selectedSong.getSong());
            tvTacgia.setText(selectedSong.getSinger());

            releaseMediaPlayer();
            createAndStartMediaPlayer(selectedSong.getFile());

            String duration = millisecondsToString(musicPlayer.getDuration());
            if (tvDuration != null) {
                tvDuration.setText(duration);
            }

            sbTime.setMax(musicPlayer.getDuration());
        }
    }
}