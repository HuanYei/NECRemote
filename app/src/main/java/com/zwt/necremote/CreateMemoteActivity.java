package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CreateMemoteActivity extends AppCompatActivity implements View.OnClickListener {
    private static Map<String, String> nemote = new HashMap<>();
    private Button create,empty;
public static Map<String, String> getNemoteMap(){
    return nemote;
}
private EditText et_customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memote);
        create=findViewById(R.id.create);
        empty=findViewById(R.id.empty);
        et_customer=findViewById(R.id.et_customerId);
        create.setOnClickListener(this::onClick);
        empty.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create:
                String customerId = et_customer.getText().toString();
                String[] numberArray = customerId.split("\n");
                String a;
                int length;
                for (int i=0;i<numberArray.length;i++){
                    a=numberArray[i];
                    length=a.length();
                    numberArray[i]=a.substring(2,length);
                    Log.e("numberArray",a);
                }
                String IRCODE[]=new String[numberArray.length];
                String IRKEY[]=new String[numberArray.length];
                for (int i=0;i<numberArray.length;i++){

                    String ping[]=numberArray[i].split(",");
                    IRCODE[i]=ping[0].substring(2,ping[0].length());
                    IRKEY[i]=ping[1];
                    nemote.put(ping[1], ping[0].substring(2,ping[0].length()));
                    Log.e("IRKEY",IRKEY[i]);
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
}