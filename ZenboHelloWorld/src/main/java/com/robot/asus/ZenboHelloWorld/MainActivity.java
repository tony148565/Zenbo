package com.robot.asus.ZenboHelloWorld;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.asus.robotframework.API.MotionControl;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.robot.asus.robotactivity.RobotActivity;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

public class MainActivity extends RobotActivity {
    public int state = 1;
    private MediaPlayer player;
    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
        }

        @Override
        public void initComplete() {
            super.initComplete();

        }
    };

    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {

        }

        @Override
        public void onResult(JSONObject jsonObject) {

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = new MediaPlayer();
        //setContentView(R.layout.activity_main);
    }

    public MainActivity() {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Hello world");
        robotAPI.robot.setExpression(RobotFace.DEFAULT);
        float theta = (float) 6.28;
        if (state == 1){
            robotAPI.robot.setExpression(RobotFace.SHOCKED);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = 0;
        }else if(state == 2){
            robotAPI.motion.moveBody(0, 0, theta, MotionControl.SpeedLevel.Body.L2);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = 0;
        }else if(state == 3){
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + "music0");
            try{
                player.reset();
                player = MediaPlayer.create(getApplicationContext(), uri);
                player.start();
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = 0;
        }
        /*
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + "music0");
        try{
            player.reset();
            player = MediaPlayer.create(getApplicationContext(), uri);
            player.start();
        }catch(Exception e){
            e.printStackTrace();
        }
        */
        //robotAPI.motion.moveBody(0, 0, theta, MotionControl.SpeedLevel.Body.L2);
    }
}
