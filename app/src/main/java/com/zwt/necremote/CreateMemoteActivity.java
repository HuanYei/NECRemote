package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.zwt.necremote.db.DButil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CreateMemoteActivity extends AppCompatActivity implements View.OnClickListener {
    private static Map<String, String> nemote = new HashMap<>();
    private Button create,empty;
    public static Map<String, String> getNemoteMap(){
    return nemote;
}
    private EditText et_customer,IRnameEditText,IRHeaderCode;
    DButil dButil=new DButil(this);
    private String IRname="";
    private boolean RTKIRjudge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memote);
        create=findViewById(R.id.create);
        empty=findViewById(R.id.empty);
        IRnameEditText=findViewById(R.id.IRname);
        et_customer=findViewById(R.id.et_customerId);
        IRHeaderCode=findViewById(R.id.IRHeaderCode);
        RTKIRjudge=getIntent().getStringExtra("IRType").equals("RTK");
        if (!RTKIRjudge){
            IRHeaderCode.setVisibility(View.VISIBLE);
            et_customer.setHint(R.string.MTK_item_keycode);
        }
        create.setOnClickListener(this::onClick);
        empty.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create:
                String customerId = et_customer.getText().toString();
                customerId=customerId.replace(" ","");
                String[] numberArray = customerId.split("\n");
                String a;
                int length;
                if (RTKIRjudge){
                    for (int i=0;i<numberArray.length;i++){
                        a=numberArray[i];
                        length=a.length();
                        numberArray[i]=a.substring(2,length);
                        Log.e("numberArray",a);
                    }
                }else {
                    for (int i=0;i<numberArray.length;i++){
                        numberArray[i]=toRKTCODE(numberArray[i]);
                        Log.e("numberArray",numberArray[i]);
                    }
                }

                String IRCODE[]=new String[numberArray.length];
                String IRKEY[]=new String[numberArray.length];
                IRname=IRnameEditText.getText().toString();
                for (int i=0;i<numberArray.length;i++){

                    String ping[]=numberArray[i].split(",");
                    IRCODE[i]=ping[0].substring(2,ping[0].length());
                    IRKEY[i]=ping[1];
                    nemote.put(ping[1], ping[0].substring(2,ping[0].length()));
                    Log.e("IRKEY",IRKEY[i]);
                    dButil.insertIR(IRname,ping[1],ping[0].substring(2,ping[0].length()));
                    Log.e("SSSSSS", "onClick: " );
                }
                Intent intent=new Intent(CreateMemoteActivity.this,MainActivity2.class);
                intent.putExtra("IRCODE",IRCODE);
                intent.putExtra("IRKEY",IRKEY);
                intent.putExtra("IRKEY",IRKEY);
                startActivity(intent);
                finish();
                break;
            case R.id.empty:
                et_customer.setText("");
                break;
        }
    }
    public  String toRKTCODE(String IRCODEKEY){
        String ping[]=IRCODEKEY.split("=");
        String code=ping[1].replace("0x","");
        code=codeFF(code);
        code="0x"+code;
        String Headcode="0x20df";
        Headcode=Headcode.replace("0x","");
        System.out.println(Headcode);
        String a=Headcode.substring(0,2);
        String b=Headcode.substring(2,4);
        code=code+b+a;
        IRCODEKEY=code+","+ping[0];
        return IRCODEKEY;
    }

    private  String codeFF(String code) {
        System.out.println(code);
        Integer a=Integer.parseInt(code,16);
        Integer c=255-a;
        return Integer.toHexString(c)+code;
    }
}