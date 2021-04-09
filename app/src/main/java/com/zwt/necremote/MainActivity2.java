package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ConsumerIrManagerApi consumerIrManagerApi;
    private ListView listView;
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
        listView=findViewById(R.id.IR_customlistView);
        Intent intent=getIntent();
        String[] IRCODE=intent.getStringArrayExtra("IRCODE");
        String[] IRKEY=intent.getStringArrayExtra("IRKEY");
        ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,IRKEY);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consumerIrManagerApi.transmit(38000,IRlevel(IRCODE[position]));
            }
        });
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
}