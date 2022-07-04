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
import com.robot.asus.ZenboHelloWorld.HTTPServer;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.ServerSocket; 
import java.net.Socket;

import org.json.JSONObject;

public class MainActivity extends RobotActivity {
    public int state = 0;
    private MediaPlayer player;
    HTTPServer server;
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
        
        server = new HTTPServer();
        //Start to get signal
        try {
            HTTPServer.Get();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try{
            state = Integer.parseInt(server.state);
        }catch(Exception e){
            robotAPI.robot.setExpression(RobotFace.LAZY); // debug
            e.printStackTrace();
        }

        float theta = (float) 6.28;
        if (state == 1){
            robotAPI.robot.setExpression(RobotFace.SHOCKED);
            state = 0;
        }
        if(state == 2){
            robotAPI.motion.moveBody(0, 0, theta, MotionControl.SpeedLevel.Body.L2);
            state = 0;
        }
        if(state == 3){
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + "music0");
            try{
                player.reset();
                player = MediaPlayer.create(getApplicationContext(), uri);
                player.start();
            }catch(Exception e){
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
