package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ConsumerIrManagerApi consumerIrManagerApi;

    private RoundMenuView mRegionView;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button0;
    private EditText et_customer;
    private TextView tv_freqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();

        consumerIrManagerApi = ConsumerIrManagerApi.getInstance();
        consumerIrManagerApi.init(this);

        mRegionView = (RoundMenuView) findViewById(R.id.my_roundMenuView);
        mRegionView.setListener(new RoundMenuView.RegionViewClickListener() {

            @Override
            public void clickTop() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_up);
            }

            @Override
            public void clickRight() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_right);
            }

            @Override
            public void clickLeft() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_left);
            }

            @Override
            public void clickCenter() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_center);
            }

            @Override
            public void clicklongLeft() {

            }

            @Override
            public void clicklongTop() {

            }

            @Override
            public void clicklongRight() {

            }

            @Override
            public void clicklongBottom() {

            }

            @Override
            public void clicklongCenter() {

            }

            @Override
            public void clicklongbuton() {

            }

            @Override
            public void clickBottom() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_down);
            }
        });

    }

    private void initButton(){
//        et_customer = (EditText)findViewById(R.id.et_customerId);
        tv_freqs = (TextView)findViewById(R.id.freqs_text);
        button0=findViewById(R.id.key_0);button7=findViewById(R.id.key_7);button1=findViewById(R.id.key_1);button2=findViewById(R.id.key_2);button3=findViewById(R.id.key_3);
        button4=findViewById(R.id.key_4);button5=findViewById(R.id.key_5);button6=findViewById(R.id.key_6);button8=findViewById(R.id.key_8);button9=findViewById(R.id.key_9);
        button11=findViewById(R.id.key_11);button10=findViewById(R.id.key_10);
        button0.setOnClickListener(this::onClick);button1.setOnClickListener(this::onClick);button2.setOnClickListener(this::onClick);button3.setOnClickListener(this::onClick);
        button4.setOnClickListener(this::onClick);button5.setOnClickListener(this::onClick);button6.setOnClickListener(this::onClick);button7.setOnClickListener(this::onClick);
        button8.setOnClickListener(this::onClick);button9.setOnClickListener(this::onClick);button10.setOnClickListener(this::onClick);button11.setOnClickListener(this::onClick);
        Button btn_exit = (Button)findViewById(R.id.btn_exit);
        Button btn_epg = (Button)findViewById(R.id.btn_epg);
        Button btn_menu = (Button)findViewById(R.id.btn_menu);
        Button btn_source = (Button)findViewById(R.id.btn_source);

        Button btn_power = (Button)findViewById(R.id.btn_power);
        Button btn_home = (Button)findViewById(R.id.btn_home);
        Button btn_chrom = (Button)findViewById(R.id.btn_chrom);

        Button btn_ch_up = (Button)findViewById(R.id.btn_CH_up);
        Button btn_ch_down = (Button)findViewById(R.id.btn_CH_down);
        Button btn_vol_up = (Button)findViewById(R.id.btn_VOL_up);
        Button btn_vol_down = (Button)findViewById(R.id.btn_VOL_down);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_exit);
            }
        });
        btn_epg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_epg);
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_menu);
            }
        });
        btn_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_source);
            }
        });
        btn_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_power);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_home);
            }
        });
        btn_chrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_chrom);
            }
        });
        btn_ch_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_CH_up);
            }
        });
        btn_ch_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_CH_dowm);
            }
        });
        btn_vol_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_VOL_up);
            }
        });
        btn_vol_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_VOL_dowm);
            }
        });
    }
    public boolean isHexNumber(String s){
        String regex="^[A-Fa-f0-9]+$";
        if(s.matches(regex)) {
            return true;
        }
        return false;
    }
    // 计算遥控器码值十六进制
    private String cuclationKeyCode(String code){
        code = code.toUpperCase();
        int code_10 = Integer.parseInt(code, 16);
        int code_FF = Integer.parseInt("FF", 16);
        String inverseCode = Integer.toHexString(code_FF-code_10).toUpperCase();
        return inverseCode+code;
    }
    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel(String IRCODE) {
        String ma=IRCODE;
        char aj[]=ma.toCharArray();
        String ajer="";
        for (int i = 0; i < aj.length; i++) {
            ajer+=eraj(aj[i]+"");
        }
        System.out.println(ajer);
        List<Integer> list=new ArrayList<Integer>();
        list.add(9000);list.add(4500);
        for (int i =ajer.length()-1; i >=0; i--) {
            if (ajer.charAt(i)=='1') {
                list.add(560);list.add(1680);
            }else {
                list.add(560);list.add(560);
            }
        }
        list.add(560);list.add(20000);
        int pattern[]=new int[list.size()];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i]=list.get(i);
        }
        System.out.println(list.toString());
        return pattern;
    }
    public static String eraj(String string) {
        string=Integer.toBinaryString(Integer.parseInt(string,16))+"";
        if (string.length()<4) {
            int sum =4-string.length();
            for (int i = 0; i <sum; i++) {
                string="0"+string;
            }
        }
        return string;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.key_0:
                consumerIrManagerApi.transmit(38000, IRlevel("a956df20"));
                break;
            case R.id.key_1:
                consumerIrManagerApi.transmit(38000, IRlevel("bd42df20"));
                break;
            case R.id.key_2:
                consumerIrManagerApi.transmit(38000, IRlevel("bc43df20"));
                break;
            case R.id.key_3:
                consumerIrManagerApi.transmit(38000, IRlevel("f00fdf20"));
                break;
            case R.id.key_4:
                consumerIrManagerApi.transmit(38000, IRlevel("e11edf20"));
                break;
            case R.id.key_5:
                consumerIrManagerApi.transmit(38000, IRlevel("e21ddf20"));
                break;
            case R.id.key_6:
                consumerIrManagerApi.transmit(38000, IRlevel("e31cdf20"));
                break;
            case R.id.key_7:
                consumerIrManagerApi.transmit(38000, IRlevel("e718df20"));
                break;
            case R.id.key_8:
                consumerIrManagerApi.transmit(38000, IRlevel("ba45df20"));
                break;
            case R.id.key_9:
                consumerIrManagerApi.transmit(38000, IRlevel("b34cdf20"));
                break;
            case R.id.key_11:
                consumerIrManagerApi.transmit(38000, IRlevel("f609df20"));
                break;
            case R.id.key_10:
                consumerIrManagerApi.transmit(38000, IRlevel("fe01df20"));
                break;
        }
    }
}