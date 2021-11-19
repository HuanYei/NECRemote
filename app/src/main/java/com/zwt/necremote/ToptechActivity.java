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
    private Button btn_power, btn_mute, key_0, key_1, key_2, key_3, key_4, key_5, key_6, key_7, key_8, key_9,
            key_缩放, key_LIST, btn_source, btn_信息, btn_menu, btn_exit, btn_CH_up, btn_CH_down, btn_VOL_up,
            btn_VOL_down, btn_home, btn_喜爱, key_老化, key_复位, key_频点, key_版本, key_CC, key_高清, key_MAC,
            key_CIKEY, key_按键, key_测试, key_CIINF, key_HDK, key_LNB, key_网线, key_WIFI, key_屏参, key_MTS, key_省电,
            key_图像, key_声音, key_播放暂停, key_上一首, key_下一首, key_切换, key_停止, key_快退, key_快进, key_EPG, key_刻录,
            key_刻录列表, key_时移, key_浏览器, key_红色, key_绿色, key_黄色, key_蓝色;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toptech);
        consumerIrManagerApi = ConsumerIrManagerApi.getInstance();
        consumerIrManagerApi.init(this);
        //启动线程
        clicklongthread.start();
    }

    @Override
    protected void onStart() {
        控件绑定ID();
        btn_power = findViewById(R.id.btn_power);
        super.onStart();
        mRegionView = (RoundMenuView) findViewById(R.id.my_roundMenuView);

        //自定义方向键的绑定
        mRegionView.setListener(new RoundMenuView.RegionViewClickListener() {

            @Override
            public void clickLeft() {
                isRunning = false;
            }

            @Override
            public void clickTop() {
                isRunning = false;
            }

            @Override
            public void clickRight() {
                isRunning = false;
            }

            @Override
            public void clickBottom() {
                isRunning = false;
            }

            @Override
            public void clickCenter() {
                isRunning = false;
            }

            @Override
            public void clicklongLeft() {
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0c"));
                    keyCode = "0c";
                    Thsleep = true;
                } catch (Exception e) {
                    Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
                }
                Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                isRunning = true;

            }

            @Override
            public void clicklongTop() {
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("17"));
                    keyCode = "17";
                    Thsleep = true;
                } catch (Exception e) {
                    Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
                }
                Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                isRunning = true;
            }

            @Override
            public void clicklongRight() {
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("05"));
                    keyCode = "05";
                    Thsleep = true;
                } catch (Exception e) {
                    Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
                }
                Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                isRunning = true;
            }

            @Override
            public void clicklongBottom() {
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0d"));
                    keyCode = "0d";
                    Thsleep = true;
                } catch (Exception e) {
                    Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
                }
                Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                isRunning = true;
            }

            @Override
            public void clicklongCenter() {
                try {
                    consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("02"));
                    keyCode = "02";
                    Thsleep = true;
                    Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                    vibrator.vibrate(300);
                    isRunning = true;
                } catch (Exception e) {
                    Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void clicklongbuton() {
                isRunning = false;
            }

        });
    }


    //发送长按码值线程。
    Thread clicklongthread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (isRunning) {
                    try {
                        if (Thsleep) {
//                            Log.e("TAG", "run: "+"Thsleepyes");
                            Thread.sleep(700);
                            if (isRunning)
                                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE(keyCode));
                        } else {
//                            Log.e("TAG", "run: "+"Thsleepno");
                        }
                        if (isRunning) {
//                            Log.e("TAG", "run: "+"isRunningyes");
                            Thsleep = false;
                            Thread.sleep(110);
                            Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                            vibrator.vibrate(110);
                        } else {
//                            Log.e("TAG", "run: "+"isRunningno");
                        }
//                        Log.e("TAG", "-----------------------------------");
//                        Log.e("TAG", "-----------------------------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    consumerIrManagerApi.transmit(38000, new int[]{9000, 2250, 560, 560});
                }
            }
        }
    });
    //用以区分是否要休眠1s
    boolean Thsleep = true;
    //用以是否发送长按码
    boolean isRunning = false;
    String keyCode = "";
    //触摸监听
    int[] viewdisplay = new int[2] ;
    double[] 初始坐标 = new double[2] ;
    double[] 移动中坐标 = new double[2] ;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isRunning = false;
        try {
            发送按键码值(v.getId());
        } catch (Exception e) {
            Toast.makeText(this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
        }
        //按下时触发
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            初始坐标[0]=event.getRawX();
            初始坐标[1]=event.getRawY();
            keyCode = NECutli.getKeyCode();
            Thsleep = true;
            isRunning=true;
            Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
            vibrator.vibrate(300);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            移动中坐标[0]=event.getRawX();
            移动中坐标[1]=event.getRawY();
            v.getLocationOnScreen(viewdisplay);
            Log.e("TAG", "onTouch: "+"初始坐标x:"+初始坐标[0]+"初始坐标y"+初始坐标[1]+"\n  终止坐标x:"+移动中坐标[0]+" 终止坐标y"
                    +移动中坐标[1]+"\nview坐标x:"+viewdisplay[0]+"  view坐标y"+viewdisplay[1]+"\nw:"+v.getWidth()+"  h"+v.getHeight());
            int max坐标x=viewdisplay[0]+v.getWidth();
            int max坐标y=viewdisplay[1]+v.getHeight();
            int x= (int) 移动中坐标[0];
            int y= (int)移动中坐标[1];
//            Log.e("TAG", "onTouch: x:"+max坐标x+" y:"+max坐标y);
            if (x>viewdisplay[0]&&y>viewdisplay[1]&&x<max坐标x&&y<max坐标y){
                Log.e("TAG", "onTouch: yes" );
            }else {
                isRunning = false;
            }



        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            isRunning = false;
        }
        return false;
    }


    private void 发送按键码值(int viewid){
        switch (viewid) {
            case R.id.btn_power:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0B"));
                break;
            case R.id.btn_mute:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("14"));
                break;
            case R.id.key_1:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("42"));
                break;
            case R.id.key_2:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("43"));
                break;
            case R.id.key_3:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0F"));
                break;
            case R.id.key_4:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1E"));
                break;
            case R.id.key_5:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1D"));
                break;
            case R.id.key_6:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1C"));
                break;
            case R.id.key_7:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("18"));
                break;
            case R.id.key_8:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("45"));
                break;
            case R.id.key_9:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4C"));
                break;
            case R.id.key_0:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("56"));
                break;
            case R.id.key_缩放:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("51"));
                break;
            case R.id.key_LIST:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("09"));
                break;
            case R.id.btn_source:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("01"));
                break;
            case R.id.btn_信息:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("50"));
                break;
            case R.id.btn_menu:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4e"));
                break;
            case R.id.btn_exit:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1b"));
                break;
            case R.id.btn_CH_up:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("55"));
                break;
            case R.id.btn_CH_down:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5a"));
                break;
            case R.id.btn_VOL_up:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0a"));
                break;
            case R.id.btn_VOL_down:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("40"));
                break;
            case R.id.btn_home:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("06"));
                break;
            case R.id.btn_喜爱:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("10"));
                break;
            case R.id.key_老化:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3c"));
                break;
            case R.id.key_复位:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3b"));
                break;
            case R.id.key_频点:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("38"));
                break;
            case R.id.key_版本:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3a"));
                break;
            case R.id.key_CC:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("44"));
                break;
            case R.id.key_高清:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("48"));
                break;
            case R.id.key_MAC:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("2c"));
                break;
            case R.id.key_CIKEY:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("2d"));
                break;
            case R.id.key_按键:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("2e"));
                break;
            case R.id.key_测试:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3d"));
                break;
            case R.id.key_CIINF:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("e8"));
                break;
            case R.id.key_HDK:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("e6"));
                break;
            case R.id.key_LNB:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("e7"));
                break;
            case R.id.key_网线:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("ec"));
                break;
            case R.id.key_WIFI:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("ed"));
                break;
            case R.id.key_屏参:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("3e"));
                break;
            case R.id.key_MTS:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("11"));
                break;
            case R.id.key_省电:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("10"));
                break;
            case R.id.key_图像:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4d"));
                break;
            case R.id.key_声音:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("07"));
                break;
            case R.id.key_播放暂停:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("16"));
                break;
            case R.id.key_上一首:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("00"));
                break;
            case R.id.key_下一首:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("08"));
                break;
            case R.id.key_切换:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("39"));
                break;
            case R.id.key_停止:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("12"));
                break;
            case R.id.key_快退:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("1a"));
                break;
            case R.id.key_快进:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5f"));
                break;
            case R.id.key_EPG:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("47"));
                break;
            case R.id.key_刻录:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("57"));
                break;
            case R.id.key_刻录列表:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("13"));
                break;
            case R.id.key_时移:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("48"));
                break;
            case R.id.key_浏览器:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5b"));
                break;
            case R.id.key_红色:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("46"));
                break;
            case R.id.key_绿色:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("4a"));
                break;
            case R.id.key_黄色:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("52"));
                break;
            case R.id.key_蓝色:
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("5e"));
                break;
        }
    }
    //控件绑定ID
    private void 控件绑定ID() {
        btn_power = findViewById(R.id.btn_power);
        btn_power.setOnTouchListener(this);
        btn_mute = findViewById(R.id.btn_mute);
        btn_mute.setOnTouchListener(this);
        key_0 = findViewById(R.id.key_0);
        key_0.setOnTouchListener(this);
        key_1 = findViewById(R.id.key_1);
        key_1.setOnTouchListener(this);
        key_2 = findViewById(R.id.key_2);
        key_2.setOnTouchListener(this);
        key_3 = findViewById(R.id.key_3);
        key_3.setOnTouchListener(this);
        key_4 = findViewById(R.id.key_4);
        key_4.setOnTouchListener(this);
        key_5 = findViewById(R.id.key_5);
        key_5.setOnTouchListener(this);
        key_6 = findViewById(R.id.key_6);
        key_6.setOnTouchListener(this);
        key_7 = findViewById(R.id.key_7);
        key_7.setOnTouchListener(this);
        key_8 = findViewById(R.id.key_8);
        key_8.setOnTouchListener(this);
        key_9 = findViewById(R.id.key_9);
        key_9.setOnTouchListener(this);
        key_缩放 = findViewById(R.id.key_缩放);
        key_缩放.setOnTouchListener(this);
        key_LIST = findViewById(R.id.key_LIST);
        key_LIST.setOnTouchListener(this);
        btn_source = findViewById(R.id.btn_source);
        btn_source.setOnTouchListener(this);
        btn_信息 = findViewById(R.id.btn_信息);
        btn_信息.setOnTouchListener(this);
        btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnTouchListener(this);
        btn_exit = findViewById(R.id.btn_exit);
        btn_exit.setOnTouchListener(this);
        btn_CH_up = findViewById(R.id.btn_CH_up);
        btn_CH_up.setOnTouchListener(this);
        btn_CH_down = findViewById(R.id.btn_CH_down);
        btn_CH_down.setOnTouchListener(this);
        btn_VOL_up = findViewById(R.id.btn_VOL_up);
        btn_VOL_up.setOnTouchListener(this);
        btn_VOL_down = findViewById(R.id.btn_VOL_down);
        btn_VOL_down.setOnTouchListener(this);
        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnTouchListener(this);
        btn_喜爱 = findViewById(R.id.btn_喜爱);
        btn_喜爱.setOnTouchListener(this);
        key_老化 = findViewById(R.id.key_老化);
        key_老化.setOnTouchListener(this);
        key_复位 = findViewById(R.id.key_复位);
        key_复位.setOnTouchListener(this);
        key_频点 = findViewById(R.id.key_频点);
        key_频点.setOnTouchListener(this);
        key_版本 = findViewById(R.id.key_版本);
        key_版本.setOnTouchListener(this);
        key_CC = findViewById(R.id.key_CC);
        key_CC.setOnTouchListener(this);
        key_高清 = findViewById(R.id.key_高清);
        key_高清.setOnTouchListener(this);
        key_MAC = findViewById(R.id.key_MAC);
        key_MAC.setOnTouchListener(this);
        key_CIKEY = findViewById(R.id.key_CIKEY);
        key_CIKEY.setOnTouchListener(this);
        key_按键 = findViewById(R.id.key_按键);
        key_按键.setOnTouchListener(this);
        key_测试 = findViewById(R.id.key_测试);
        key_测试.setOnTouchListener(this);
        key_CIINF = findViewById(R.id.key_CIINF);
        key_CIINF.setOnTouchListener(this);
        key_HDK = findViewById(R.id.key_HDK);
        key_HDK.setOnTouchListener(this);
        key_LNB = findViewById(R.id.key_LNB);
        key_LNB.setOnTouchListener(this);
        key_网线 = findViewById(R.id.key_网线);
        key_网线.setOnTouchListener(this);
        key_WIFI = findViewById(R.id.key_WIFI);
        key_WIFI.setOnTouchListener(this);
        key_屏参 = findViewById(R.id.key_屏参);
        key_屏参.setOnTouchListener(this);
        key_MTS = findViewById(R.id.key_MTS);
        key_MTS.setOnTouchListener(this);
        key_省电 = findViewById(R.id.key_省电);
        key_省电.setOnTouchListener(this);
        key_图像 = findViewById(R.id.key_图像);
        key_图像.setOnTouchListener(this);
        key_声音 = findViewById(R.id.key_声音);
        key_声音.setOnTouchListener(this);
        key_播放暂停 = findViewById(R.id.key_播放暂停);
        key_播放暂停.setOnTouchListener(this);
        key_上一首 = findViewById(R.id.key_上一首);
        key_上一首.setOnTouchListener(this);
        key_下一首 = findViewById(R.id.key_下一首);
        key_下一首.setOnTouchListener(this);
        key_切换 = findViewById(R.id.key_切换);
        key_切换.setOnTouchListener(this);
        key_停止 = findViewById(R.id.key_停止);
        key_停止.setOnTouchListener(this);
        key_快退 = findViewById(R.id.key_快退);
        key_快退.setOnTouchListener(this);
        key_快进 = findViewById(R.id.key_快进);
        key_快进.setOnTouchListener(this);
        key_EPG = findViewById(R.id.key_EPG);
        key_EPG.setOnTouchListener(this);
        key_刻录 = findViewById(R.id.key_刻录);
        key_刻录.setOnTouchListener(this);
        key_刻录列表 = findViewById(R.id.key_刻录列表);
        key_刻录列表.setOnTouchListener(this);
        key_时移 = findViewById(R.id.key_时移);
        key_时移.setOnTouchListener(this);
        key_浏览器 = findViewById(R.id.key_浏览器);
        key_浏览器.setOnTouchListener(this);
        key_红色 = findViewById(R.id.key_红色);
        key_红色.setOnTouchListener(this);
        key_绿色 = findViewById(R.id.key_绿色);
        key_绿色.setOnTouchListener(this);
        key_黄色 = findViewById(R.id.key_黄色);
        key_黄色.setOnTouchListener(this);
        key_蓝色 = findViewById(R.id.key_蓝色);
        key_蓝色.setOnTouchListener(this);
    }


}