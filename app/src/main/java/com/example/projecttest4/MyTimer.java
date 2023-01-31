package com.example.projecttest4;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    private Timer timer;
    private MyTimer.TimerRuning timerRuningListener;
    private int remainingSec, startSec;
    public boolean isRunning;


    private static final String TAG = "MyTimer";


    private static final MyTimer ourInstance = new MyTimer();

    public static MyTimer getInstance() {
        return ourInstance;
    }

    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            int[] timeArr = (int[]) msg.obj;

            if (timeArr[0] == 0) {
                MyTimer.this.stopTimer();
                if(timerRuningListener != null) {
                    timerRuningListener.onTimerStopped(createDateFormat(0), createDateFormat(0));
                }
            } else {
                if(timerRuningListener != null) {
                    timerRuningListener.onTimerChange(createDateFormat(timeArr[0]), createDateFormat(timeArr[1]));
                }
            }


        }
    };


    public void startTimer(final int seconds) {

        startSec = 0;

        remainingSec = seconds;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "Timer running......");
                isRunning = true;
                Message message = Message.obtain();
                int[] counters = new int[2];
                counters[0] = remainingSec;
                counters[1] = startSec;
                message.obj = counters;
                message.setTarget(mHandler);
                mHandler.sendMessage(message);
                startSec++;


            }
        }, 100, 1000);


    }


    /**
     * Setting the timer format
     *
     * @param seconds
     * @return
     */
    public String createDateFormat(int seconds) {

        return String.format("%02d:%02d", (seconds % 3600) / 60, (seconds % 60));

    }

    public void setTimerRuningListener(MyTimer.TimerRuning timerRuningListener) {
        this.timerRuningListener = timerRuningListener;
    }


    public interface TimerRuning {
        void onTimerChange(String remainSec, String startSec);

        void onTimerStopped(String remainSec, String startSec);
    }

    public void stopTimer() {
        if (timer != null) {
            timerRuningListener.onTimerStopped(createDateFormat(0), createDateFormat(0));
            isRunning = false;
            timer.cancel();
        }
    }
}
