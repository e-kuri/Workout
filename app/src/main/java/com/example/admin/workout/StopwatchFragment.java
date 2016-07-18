package com.example.admin.workout;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by admin on 7/12/2016.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {

    private int seconds;
    private boolean running;
    private boolean wasRunning;
    final Handler handler = new Handler();
    private Button startButton, stopButton, resetButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container);
        runTimer(layout);
        startButton = ((Button) layout.findViewById(R.id.start_button));
        startButton.setOnClickListener(this);
        stopButton = ((Button) layout.findViewById(R.id.stop_button));
        stopButton.setOnClickListener(this);
        resetButton = ((Button) layout.findViewById(R.id.reset_button));
        resetButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        //runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(wasRunning)
            running = true;
    }

    public void onClickStart(View view) {
        wasRunning = true;
        running = true;
    }

    public void onClickStop(View view) {
        wasRunning = false;
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer(final View layout){
        handler.post(new Runnable() {
            @Override
            public void run() {
                final TextView tv = ((TextView) layout.findViewById(R.id.time_view));
                int hours = seconds/3600;
                int minutes = (seconds % 3600)/60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                tv.setText(time);
                if(running)
                    seconds++;
                handler.postDelayed(this, 1000);
            }

        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_button:
                onClickStart(view);
                break;
            case R.id.stop_button:
                onClickStop(view);
                break;
            case R.id.reset_button:
                onClickReset(view);
                break;
        }
    }
}
