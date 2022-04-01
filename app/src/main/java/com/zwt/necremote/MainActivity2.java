package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    ConsumerIrManagerApi consumerIrManagerApi;
    private static final String TAG = "MainActivity2";
    private TextView Name;
    private ListView listView;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button0,btn_mute;
    private Map<String, String> nemote;
    private RoundMenuView mRegionView;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        consumerIrManagerApi = ConsumerIrManagerApi.getInstance();
        consumerIrManagerApi.init(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vibrator = (Vibrator) MainActivity2.this.getSystemService(MainActivity2.this.VIBRATOR_SERVICE);
        listView=findViewById(R.id.IR_customlistView);
        Intent intent=getIntent();
        String[] IRCODE=intent.getStringArrayExtra("IRCODE");
        String[] IRKEY=intent.getStringArrayExtra("IRKEY");
        String RMNAME=intent.getStringExtra("RMNAME");
        Name=findViewById(R.id.rmnameTextView);
        Name.setText(RMNAME);
        boolean IRAlljudge=intent.getBooleanExtra("IRALL",false);
        if (IRAlljudge){
            nemote = IRallActivity.getNemoteMap();
        }else {
            nemote = CreateMemoteActivity.getNemoteMap();
        }
        initButton();
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.ac_item,IRKEY);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vibrator.vibrate(300);
                consumerIrManagerApi.transmit(38000,IRlevel(IRCODE[position]));
            }
        });
    }

    private void initButton(){
//        et_customer = (EditText)findViewById(R.id.et_customerId);

        button0=findViewById(R.id.key_0);button7=findViewById(R.id.key_7);button1=findViewById(R.id.key_1);button2=findViewById(R.id.key_2);button3=findViewById(R.id.key_3);
        button4=findViewById(R.id.key_4);button5=findViewById(R.id.key_5);button6=findViewById(R.id.key_6);button8=findViewById(R.id.key_8);button9=findViewById(R.id.key_9);
        button11=findViewById(R.id.key_11);button10=findViewById(R.id.key_10);btn_mute=findViewById(R.id.btn_mute);
        button0.setOnClickListener(this::onClick);button1.setOnClickListener(this::onClick);button2.setOnClickListener(this::onClick);button3.setOnClickListener(this::onClick);
        button4.setOnClickListener(this::onClick);button5.setOnClickListener(this::onClick);button6.setOnClickListener(this::onClick);button7.setOnClickListener(this::onClick);
        button8.setOnClickListener(this::onClick);button9.setOnClickListener(this::onClick);button10.setOnClickListener(this::onClick);button11.setOnClickListener(this::onClick);
        btn_mute.setOnClickListener(this::onClick);
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

        mRegionView = (RoundMenuView) findViewById(R.id.my_roundMenuView);
        mRegionView.setListener(new RoundMenuView.RegionViewClickListener() {

            @Override
            public void clickLeft() {
                if (nemote.containsKey("KEY_LEFT")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_LEFT")));
                }

            }

            @Override
            public void clickTop() {

                if (nemote.containsKey("KEY_UP")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_UP")));
                }
            }

            @Override
            public void clickRight() {
                if (nemote.containsKey("KEY_RIGHT")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_RIGHT")));
                }
            }

            @Override
            public void clickBottom() {
                if (nemote.containsKey("KEY_DOWN")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_DOWN")));
                }
            }

            @Override
            public void clickCenter() {
                if (nemote.containsKey("KEY_ENTER")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_ENTER")));
                }
            }

        });
            btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nemote.containsKey("KEY_BACK")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_BACK")));
                }


            }
        });
        btn_epg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_EPG")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_EPG")));
                }
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //nemote KEY_MENU
                if (nemote.containsKey("KEY_MENU")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_MENU")));
                }

            }
        });
        btn_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_INPUT")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_INPUT")));
                }

            }
        });
        btn_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_POWER")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_POWER")));
                }

            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_HOME")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_HOME")));
                }

            }
        });
        btn_chrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_BROWSER")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_BROWSER")));
                }

            }
        });
        btn_ch_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_CHANNELUP")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_CHANNELUP")));
                }

            }
        });
        btn_ch_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_CHANNELDOWN")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_CHANNELDOWN")));
                }

            }
        });
        btn_vol_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_VOLUMEUP")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_VOLUMEUP")));
                }
            }
        });
        btn_vol_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nemote.containsKey("KEY_VOLUMEDOWN")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_VOLUMEDOWN")));
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.key_0:
                if (nemote.containsKey("KEY_0")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_0")));
                }
                break;
            case R.id.key_1:
                if (nemote.containsKey("KEY_1")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_1")));
                }

                break;
            case R.id.key_2:
                if (nemote.containsKey("KEY_2")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_2")));
                }

                break;
            case R.id.key_3:
                if (nemote.containsKey("KEY_3")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_3")));
                }
                break;
            case R.id.key_4:
                if (nemote.containsKey("KEY_4")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_4")));
                }

                break;
            case R.id.key_5:
                if (nemote.containsKey("KEY_5")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_5")));
                }

                break;
            case R.id.key_6:
                if (nemote.containsKey("KEY_6")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_6")));
                }

                break;
            case R.id.key_7:
                if (nemote.containsKey("KEY_7")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_7")));
                }

                break;
            case R.id.key_8:
                if (nemote.containsKey("KEY_8")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_8")));
                }

                break;
            case R.id.key_9:
                if (nemote.containsKey("KEY_9")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_9")));
                }

                break;
            case R.id.key_11:
                if (nemote.containsKey("KEY_INFO")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_INFO")));
                }

                break;
            case R.id.key_10:
                if (nemote.containsKey("KEY_RED")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_RED")));
                }

                break;
            case R.id.btn_mute:
                if (nemote.containsKey("KEY_MUTE")){
                    vibrator.vibrate(300);
                    consumerIrManagerApi.transmit(38000, IRlevel(nemote.get("KEY_MUTE")));
                }

                break;
        }
    }
    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel(String IRCode) {
        Log.e(TAG, "IRlevel: "+IRCode );
        String IRCodeBinary = new StringBuilder(eraj(IRCode)).reverse().toString();
        int pattern[]=new int[IRCodeBinary.length()*2+4];
        pattern[0] = 9000;
        pattern[1] = 4500;
        int i = 2;
        for (char ir:IRCodeBinary.toCharArray()){
            if (ir == '1') {
                pattern[i] = 560;
                pattern[i+1] = 1680;
            }else {
                pattern[i] = 560;
                pattern[i+1] = 560;
            }
            i += 2;
        }
        pattern[i] = 560;
        pattern[i+1] = 20000;

        return pattern;
    }
    public static String eraj(String Hexstring) {
        Log.e(TAG, "eraj: "+Hexstring );
        String form = "%4s";
        StringBuilder resoultBinaryString = new StringBuilder();
        for (char ch : Hexstring.toCharArray()){
            resoultBinaryString.append(String.format(form, Integer.toBinaryString(Integer.parseInt(String.valueOf(ch), 16))).replace(' ', '0'));
        }
        return resoultBinaryString.toString();
    }
}