package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zwt.necremote.utli.NECutli;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class ToptechActivity extends AppCompatActivity implements View.OnTouchListener {
    ConsumerIrManagerApi consumerIrManagerApi;
    private RoundMenuView mRegionView;
    private Button btn_power,btn_mute,key_1,key_2,key_3;
//    key_4.key_5.key_6.key_7.key_8.key_9.key_10.key_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toptech);
        consumerIrManagerApi = ConsumerIrManagerApi.getInstance();
        consumerIrManagerApi.init(this);
    }

    @Override
    protected void onStart() {
        btn_power=findViewById(R.id.btn_power);
        super.onStart();
        mRegionView = (RoundMenuView) findViewById(R.id.my_roundMenuView);
        clicklongthread.start();
        mRegionView.setListener(new RoundMenuView.RegionViewClickListener() {

            @Override
            public void clickLeft() {
                Log.e("TAG", "松开");
                isRunning=false;
            }

            @Override
            public void clickTop() {
                Log.e("TAG", "松开");
                try {
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("17"));
                }catch (Exception e){
                    Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
                }
                    Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                    vibrator.vibrate(300);
            }

            @Override
            public void clickRight() {
                Log.e("TAG", "松开");
                isRunning=false;
            }

            @Override
            public void clickBottom() {
            try {
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0d"));
            }catch (Exception e){
                Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
            }
                Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
            }

            @Override
            public void clickCenter() {
            try {
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("02"));
            }catch (Exception e){
                Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
            }
                Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
            }

            @Override
            public void clicklongLeft() {
                Log.e("TAG", "按下");
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0c"));
                }catch (Exception e){
                    Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
                }
                Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                isRunning=true;

            }

            @Override
            public void clicklongTop() {

            }

            @Override
            public void clicklongRight() {
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("05"));
                }catch (Exception e){
                    Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
                }
                Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                isRunning=true;
            }

            @Override
            public void clicklongBottom() {

            }

            @Override
            public void clicklongCenter() {

            }

            @Override
            public void clicklongbuton() {
                isRunning=false;
            }
        });
    }

    Thread clicklongthread =new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if(isRunning) {
                    try {
                        Thread.sleep(110);
                        Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                        vibrator.vibrate(110);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    consumerIrManagerApi.transmit(38000, new int[]{9000, 2250, 560, 560});
                }
            }
        }
    });
    boolean isRunning = false;

    public void onClick(View v) {


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
            vibrator.vibrate(300);
        }

        try {
            switch (v.getId()){
                case R.id.btn_power:
                    if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0B"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_mute:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("14"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_1:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("42"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_2:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("43"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_3:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0F"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_4:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1E"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_5:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1D"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_6:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1C"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_7:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("18"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_8:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("45"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_9:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4C"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_0:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("56"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_缩放:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("51"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_LIST:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("09"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_source:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("01"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_信息:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("50"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_menu:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4e"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_exit:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1b"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_CH_up:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("55"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_CH_down:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5a"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_VOL_up:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0a"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_VOL_down:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("40"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_home:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("06"));
                        isRunning=true;
                    }
                    break;
                case R.id.btn_喜爱:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("10"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_老化:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3c"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_复位:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3b"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_频点:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("38"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_版本:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3a"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_CC:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("44"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_高清:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("48"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_MAC:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("2c"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_CIKEY:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("2d"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_按键:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("2e"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_测试:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3d"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_CIINF:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("e8"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_HDK:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("e6"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_LNB:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("e7"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_网线:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("ec"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_WIFI:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("ed"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_屏参:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3e"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_MTS:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("11"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_省电:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("10"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_图像:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4d"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_声音:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("07"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_播放暂停:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("16"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_上一首:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("00"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_下一首:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("08"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_切换:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("39"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_停止:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("12"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_快退:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1a"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_快进:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5f"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_EPG:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("47"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_刻录:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("57"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_刻录列表:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("13"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_时移:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("48"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_浏览器:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5b"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_红色:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("46"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_绿色:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4a"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_黄色:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("52"));
                        isRunning=true;
                    }
                    break;
                case R.id.key_蓝色:
                     if (event.getAction() == MotionEvent.ACTION_UP){
                        isRunning=false;
                    }else if (event.getAction() == MotionEvent.ACTION_DOWN){
                        consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5e"));
                        isRunning=true;
                    }
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}