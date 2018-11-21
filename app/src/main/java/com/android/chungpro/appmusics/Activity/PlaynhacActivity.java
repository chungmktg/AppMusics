package com.android.chungpro.appmusics.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chungpro.appmusics.Adapter.ViewpagerPlaylistnhac;
import com.android.chungpro.appmusics.Fragment.Fragment_Dia_nhac;
import com.android.chungpro.appmusics.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.android.chungpro.appmusics.Model.Baihat;
import com.android.chungpro.appmusics.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaynhacActivity extends AppCompatActivity {
    Baihat baihat;
    Toolbar toolbarplaynhac;
    TextView timesong, totaltimesong;
    SeekBar seekBarSong;
    ImageButton imgPlay, imgpre, imgnext, imgrandom, imgrepeat;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewpagerPlaylistnhac adapternhac;
    Fragment_Dia_nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    MediaPlayer mediaPlayer;


    int positon = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        setContentView(R.layout.activity_playnhac);

        Getdataintent();
        init();
        eventclick();

    }

    private void eventclick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (mangbaihat.size() > 0) {
                        fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat = true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom=true;
                } else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;

                    }
                    if (positon < mangbaihat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        positon++;
                        if (repeat == true) {
                            if (positon == 0) {
                                positon = mangbaihat.size();

                            }
                            positon -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == positon) {
                                positon = index - 1;

                            }
                            positon = index;
                        }
                        if (positon > (mangbaihat.size() - 1)) {
                            positon = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(positon).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(positon).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(positon).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);

            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (positon < mangbaihat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        positon--;
                        if (positon < 0) {
                            positon = mangbaihat.size() - 1;
                        }
                        if (repeat == true) {
                            positon += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == positon) {
                                positon = index - 1;

                            }
                            positon = index;
                        }

                        new PlayMp3().execute(mangbaihat.get(positon).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(positon).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(positon).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);

            }
        });
    }

    private void Getdataintent() {
        mangbaihat.clear();
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);

            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> mangbaihatarray = intent.getParcelableArrayListExtra("cacbaihat");
                for (int i = 0; i < mangbaihatarray.size(); i++) {
                    mangbaihat = mangbaihatarray;

                }
            }
        }

    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarPlaynhac);
        timesong = findViewById(R.id.txt_timesongPlaynhac);
        totaltimesong = findViewById(R.id.txt_timesongTotalPlaynhac);
        seekBarSong = findViewById(R.id.seekbarSongPlaynhac);
        imgPlay = findViewById(R.id.imgButtonplay);
        imgpre = findViewById(R.id.imgButtonrepre);
        imgnext = findViewById(R.id.imgButtonnext);
        imgrandom = findViewById(R.id.imgButtonSuffle);
        imgrepeat = findViewById(R.id.imgButtonrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpager_playnhac);


        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });

        fragment_dia_nhac = new Fragment_Dia_nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewpagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);

        fragment_dia_nhac = (Fragment_Dia_nhac) adapternhac.getItem(1);
        if (mangbaihat.size() > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgPlay.setImageResource(R.drawable.iconpause);


        }
    }

    public class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
                mediaPlayer.start();
                TimeSong();
                UpdateTime();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        totaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime() {
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    timesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler3.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        }, 300);
        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (mangbaihat.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (positon < mangbaihat.size()) {
                            imgPlay.setImageResource(R.drawable.iconpause);
                            positon++;
                            if (repeat == true) {
                                if (positon == 0) {
                                    positon = mangbaihat.size();

                                }
                                positon -= 1;
                            }
                            if (checkrandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(mangbaihat.size());
                                if (index == positon) {
                                    positon = index - 1;

                                }
                                positon = index;
                            }
                            if (positon > (mangbaihat.size() - 1)) {
                                positon = 0;
                            }
                            new PlayMp3().execute(mangbaihat.get(positon).getLinkbaihat());
                            fragment_dia_nhac.Playnhac(mangbaihat.get(positon).getHinhbaihat());
                            getSupportActionBar().setTitle(mangbaihat.get(positon).getTenbaihat());
                        }
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler5 = new Handler();
                    handler5.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler5.removeCallbacks(this);
                } else {
                    handler4.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
    }
