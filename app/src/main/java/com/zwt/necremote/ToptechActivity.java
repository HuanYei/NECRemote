package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zwt.necremote.utli.NECutli;

public class ToptechActivity extends AppCompatActivity {
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
        mRegionView.setListener(new RoundMenuView.RegionViewClickListener() {

            @Override
            public void clickLeft() {
            try {
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("0c"));
            }catch (Exception e){
                Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
            }
                Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
            }

            @Override
            public void clickTop() {
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
            try {
                consumerIrManagerApi.transmit(38000, NECutli.toRKTCODE("05"));
            }catch (Exception e){
                Toast.makeText(ToptechActivity.this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
            }
                Vibrator vibrator = (Vibrator)ToptechActivity.this.getSystemService(ToptechActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
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
        });
    }




    public void onClick(View v) {
        Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(300);
        try {


        switch (v.getId()){
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
        }catch (Exception e){
            Toast.makeText(this,"您的手机没有红外功能",Toast.LENGTH_SHORT).show();
        }
    }
}