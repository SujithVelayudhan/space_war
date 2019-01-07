package com.example.sujith.space_war;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class page1 extends AppCompatActivity
{
    private flying_spacecraft gameview;
    private Handler handler=new Handler();
    long interval=50;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        gameview=new flying_spacecraft(this);
        setContentView(gameview);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        gameview.invalidate();

                    }
                });

            }
        },0,interval);

    }
}
