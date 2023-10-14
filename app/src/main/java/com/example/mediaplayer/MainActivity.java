package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnplay,btnbackward,btnforward;
    private MediaPlayer player;
    private SeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnplay=findViewById(R.id.play);
        btnbackward=findViewById(R.id.btnbackward);
        btnforward=findViewById(R.id.btnforward);
        seekbar=findViewById(R.id.seekbar);
        btnplay.setOnClickListener(this);
        btnbackward.setOnClickListener(this);
        btnforward.setOnClickListener(this);
        player=MediaPlayer.create(this,R.raw.music);

        seekbar.setMax(player.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekbar.setProgress(player.getCurrentPosition());
            }
        },0,900);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                player.seekTo(i);
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
    public void onClick(View view) {
        if(view.getId()== R.id.play) {
            if (player.isPlaying()) {
                player.pause();
                btnplay.setText(">");
            } else {
                player.start();
                btnplay.setText("||");
            }
        }
        if(view.getId()== R.id.btnbackward) {
            player.seekTo(player.getCurrentPosition() - 5000);
        }
        if(view.getId()== R.id.btnforward) {
            player.seekTo(player.getCurrentPosition() + 5000);
        }
    }
}