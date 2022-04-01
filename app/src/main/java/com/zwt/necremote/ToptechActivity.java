package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class ToptechActivity extends AppCompatActivity implements View.OnClickListener {
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
//        //启动线程
//        clicklongthread.start();
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
                veiwid = 0;
                keycode = "0c";
                e按下();
            }

            @Override
            public void clickTop() {
                veiwid = 0;
                keycode = "17";
                e按下();
            }

            @Override
            public void clickRight() {
                veiwid = 0;
                keycode = "05";
                e按下();
            }

            @Override
            public void clickBottom() {
                veiwid = 0;
                keycode = "0d";
                e按下();
            }

            @Override
            public void clickCenter() {
                veiwid = 0;
                keycode = "02";
                e按下();
            }
        });
    }

    long mLastTime = 0;
    long mCurTime = 0;
    int veiwid = 0;
    String keycode = "";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.e("SSSS", "handleMessage: 短按事件");
                    if (veiwid == 0) {
                        e发送单按码值(keycode);
                    } else {
                        e发送单按码值(e获取键码值(veiwid));
                    }
                    break;
                case 2:
                    Log.e("SSSS", "handleMessage: 长按事件");
                    if (veiwid == 0) {
                        e发送长按码值(keycode);
                    } else {
                        e发送长按码值(e获取键码值(veiwid));
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        veiwid = v.getId();
        e按下();
    }

    public void e按下() {
        mLastTime = mCurTime;
        mCurTime = System.currentTimeMillis();
        if (mCurTime - mLastTime < 200) {//双击事件
            mCurTime = 0;
            mLastTime = 0;
            handler.removeMessages(1);
            handler.sendEmptyMessage(2);
        } else {//单击事件
            handler.sendEmptyMessageDelayed(1, 210);
        }
    }

    int[] viewdisplay = new int[2];
    double[] 初始坐标 = new double[2];
    double[] 移动中坐标 = new double[2];


    public void e发送长按码值(String keyCode) {
        try {
            consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE(keyCode));
            e长按线程(20);

        } catch (Exception e) {
            Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
        }

    }

    public void e长按线程(int j){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <j ; i++) {
                        try {
                            Thread.sleep(110);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        consumerIrManagerApi.transmit(38000, new int[]{9000, 2250, 560, 560});
//                        Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
//                        vibrator.vibrate(110);
                    }
                }
            }).start();

    }

    public void e发送单按码值(String keyCode) {
        try {
            consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE(keyCode));

            Vibrator vibrator = (Vibrator) ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
            vibrator.vibrate(300);
        } catch (Exception e) {
            Toast.makeText(ToptechActivity.this, "您的手机没有红外功能", Toast.LENGTH_SHORT).show();
        }

    }


    private String e获取键码值(int viewid) {
        switch (viewid) {
            case R.id.btn_power:
                return "0B";

            case R.id.btn_mute:
                return "14";

            case R.id.key_1:
                return "42";

            case R.id.key_2:
                return "43";

            case R.id.key_3:
                return "0F";

            case R.id.key_4:
                return "1E";

            case R.id.key_5:
                return "1D";

            case R.id.key_6:
                return "1C";

            case R.id.key_7:
                return "18";

            case R.id.key_8:
                return "45";

            case R.id.key_9:
                return "4C";

            case R.id.key_0:
                return "56";

            case R.id.key_缩放:
                return "51";

            case R.id.key_LIST:
                return "09";

            case R.id.btn_source:
                return "01";

            case R.id.btn_信息:
                return "50";

            case R.id.btn_menu:
                return "4e";

            case R.id.btn_exit:
                return "1b";

            case R.id.btn_CH_up:
                return "55";

            case R.id.btn_CH_down:
                return "5a";

            case R.id.btn_VOL_up:
                return "0a";

            case R.id.btn_VOL_down:
                return "40";

            case R.id.btn_home:
                return "06";

            case R.id.btn_喜爱:
                return "10";

            case R.id.key_老化:
                return "3c";

            case R.id.key_复位:
                return "3b";

            case R.id.key_频点:
                return "38";

            case R.id.key_版本:
                return "3a";

            case R.id.key_CC:
                return "44";

            case R.id.key_高清:
                return "48";

            case R.id.key_MAC:
                return "2c";

            case R.id.key_CIKEY:
                return "2d";

            case R.id.key_按键:
                return "2e";

            case R.id.key_测试:
                return "3d";

            case R.id.key_CIINF:
                return "e8";

            case R.id.key_HDK:
                return "e6";

            case R.id.key_LNB:
                return "e7";

            case R.id.key_网线:
                return "ec";

            case R.id.key_WIFI:
                return "ed";

            case R.id.key_屏参:
                return "3e";

            case R.id.key_MTS:
                return "11";

            case R.id.key_省电:
                return "10";

            case R.id.key_图像:
                return "4d";

            case R.id.key_声音:
                return "07";

            case R.id.key_播放暂停:
                return "16";

            case R.id.key_上一首:
                return "00";

            case R.id.key_下一首:
                return "08";

            case R.id.key_切换:
                return "39";

            case R.id.key_停止:
                return "12";

            case R.id.key_快退:
                return "1a";

            case R.id.key_快进:
                return "5f";

            case R.id.key_EPG:
                return "47";

            case R.id.key_刻录:
                return "57";

            case R.id.key_刻录列表:
                return "13";

            case R.id.key_时移:
                return "48";

            case R.id.key_浏览器:
                return "5b";

            case R.id.key_红色:
                return "46";

            case R.id.key_绿色:
                return "4a";

            case R.id.key_黄色:
                return "52";

            case R.id.key_蓝色:
                return "5e";

        }
        return "";
    }

    //控件绑定ID
    private void 控件绑定ID() {
        btn_power = findViewById(R.id.btn_power);
        btn_power.setOnClickListener(this);
        btn_mute = findViewById(R.id.btn_mute);
        btn_mute.setOnClickListener(this);
        key_0 = findViewById(R.id.key_0);
        key_0.setOnClickListener(this);
        key_1 = findViewById(R.id.key_1);
        key_1.setOnClickListener(this);
        key_2 = findViewById(R.id.key_2);
        key_2.setOnClickListener(this);
        key_3 = findViewById(R.id.key_3);
        key_3.setOnClickListener(this);
        key_4 = findViewById(R.id.key_4);
        key_4.setOnClickListener(this);
        key_5 = findViewById(R.id.key_5);
        key_5.setOnClickListener(this);
        key_6 = findViewById(R.id.key_6);
        key_6.setOnClickListener(this);
        key_7 = findViewById(R.id.key_7);
        key_7.setOnClickListener(this);
        key_8 = findViewById(R.id.key_8);
        key_8.setOnClickListener(this);
        key_9 = findViewById(R.id.key_9);
        key_9.setOnClickListener(this);
        key_缩放 = findViewById(R.id.key_缩放);
        key_缩放.setOnClickListener(this);
        key_LIST = findViewById(R.id.key_LIST);
        key_LIST.setOnClickListener(this);
        btn_source = findViewById(R.id.btn_source);
        btn_source.setOnClickListener(this);
        btn_信息 = findViewById(R.id.btn_信息);
        btn_信息.setOnClickListener(this);
        btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);
        btn_exit = findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        btn_CH_up = findViewById(R.id.btn_CH_up);
        btn_CH_up.setOnClickListener(this);
        btn_CH_down = findViewById(R.id.btn_CH_down);
        btn_CH_down.setOnClickListener(this);
        btn_VOL_up = findViewById(R.id.btn_VOL_up);
        btn_VOL_up.setOnClickListener(this);
        btn_VOL_down = findViewById(R.id.btn_VOL_down);
        btn_VOL_down.setOnClickListener(this);
        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_喜爱 = findViewById(R.id.btn_喜爱);
        btn_喜爱.setOnClickListener(this);
        key_老化 = findViewById(R.id.key_老化);
        key_老化.setOnClickListener(this);
        key_复位 = findViewById(R.id.key_复位);
        key_复位.setOnClickListener(this);
        key_频点 = findViewById(R.id.key_频点);
        key_频点.setOnClickListener(this);
        key_版本 = findViewById(R.id.key_版本);
        key_版本.setOnClickListener(this);
        key_CC = findViewById(R.id.key_CC);
        key_CC.setOnClickListener(this);
        key_高清 = findViewById(R.id.key_高清);
        key_高清.setOnClickListener(this);
        key_MAC = findViewById(R.id.key_MAC);
        key_MAC.setOnClickListener(this);
        key_CIKEY = findViewById(R.id.key_CIKEY);
        key_CIKEY.setOnClickListener(this);
        key_按键 = findViewById(R.id.key_按键);
        key_按键.setOnClickListener(this);
        key_测试 = findViewById(R.id.key_测试);
        key_测试.setOnClickListener(this);
        key_CIINF = findViewById(R.id.key_CIINF);
        key_CIINF.setOnClickListener(this);
        key_HDK = findViewById(R.id.key_HDK);
        key_HDK.setOnClickListener(this);
        key_LNB = findViewById(R.id.key_LNB);
        key_LNB.setOnClickListener(this);
        key_网线 = findViewById(R.id.key_网线);
        key_网线.setOnClickListener(this);
        key_WIFI = findViewById(R.id.key_WIFI);
        key_WIFI.setOnClickListener(this);
        key_屏参 = findViewById(R.id.key_屏参);
        key_屏参.setOnClickListener(this);
        key_MTS = findViewById(R.id.key_MTS);
        key_MTS.setOnClickListener(this);
        key_省电 = findViewById(R.id.key_省电);
        key_省电.setOnClickListener(this);
        key_图像 = findViewById(R.id.key_图像);
        key_图像.setOnClickListener(this);
        key_声音 = findViewById(R.id.key_声音);
        key_声音.setOnClickListener(this);
        key_播放暂停 = findViewById(R.id.key_播放暂停);
        key_播放暂停.setOnClickListener(this);
        key_上一首 = findViewById(R.id.key_上一首);
        key_上一首.setOnClickListener(this);
        key_下一首 = findViewById(R.id.key_下一首);
        key_下一首.setOnClickListener(this);
        key_切换 = findViewById(R.id.key_切换);
        key_切换.setOnClickListener(this);
        key_停止 = findViewById(R.id.key_停止);
        key_停止.setOnClickListener(this);
        key_快退 = findViewById(R.id.key_快退);
        key_快退.setOnClickListener(this);
        key_快进 = findViewById(R.id.key_快进);
        key_快进.setOnClickListener(this);
        key_EPG = findViewById(R.id.key_EPG);
        key_EPG.setOnClickListener(this);
        key_刻录 = findViewById(R.id.key_刻录);
        key_刻录.setOnClickListener(this);
        key_刻录列表 = findViewById(R.id.key_刻录列表);
        key_刻录列表.setOnClickListener(this);
        key_时移 = findViewById(R.id.key_时移);
        key_时移.setOnClickListener(this);
        key_浏览器 = findViewById(R.id.key_浏览器);
        key_浏览器.setOnClickListener(this);
        key_红色 = findViewById(R.id.key_红色);
        key_红色.setOnClickListener(this);
        key_绿色 = findViewById(R.id.key_绿色);
        key_绿色.setOnClickListener(this);
        key_黄色 = findViewById(R.id.key_黄色);
        key_黄色.setOnClickListener(this);
        key_蓝色 = findViewById(R.id.key_蓝色);
        key_蓝色.setOnClickListener(this);
    }


}