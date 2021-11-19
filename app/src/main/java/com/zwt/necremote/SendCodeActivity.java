package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zwt.necremote.utli.NECutli;

public class SendCodeActivity extends AppCompatActivity {
    private EditText cusCode,keyCode;
    private ConsumerIrManagerApi consumerIrManagerApi;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);
        init();
    }

    private void init(){
        vibrator = (Vibrator) SendCodeActivity.this.getSystemService(SendCodeActivity.this.VIBRATOR_SERVICE);
        consumerIrManagerApi = ConsumerIrManagerApi.getInstance();
        consumerIrManagerApi.init(this);
        cusCode=findViewById(R.id.Customer_code);
        keyCode=findViewById(R.id.KEY_code);
    }

    public void Click(View view) {

        String cusCodeString=cusCode.getText().toString();
        String keyCodeString=keyCode.getText().toString();
        if (cusCodeString.equals("")||keyCodeString.equals("")||cusCodeString==null||keyCodeString==null){
            Toast.makeText(SendCodeActivity.this, "请输入内容！", Toast.LENGTH_SHORT).show();
        }else if (keyCodeString.length()!=2||cusCodeString.length()!=2){
            Toast.makeText(SendCodeActivity.this, "输入不规范，请重新检查后输入！", Toast.LENGTH_SHORT).show();
        } else {
            vibrator.vibrate(300);
            consumerIrManagerApi.transmit(38000, NECutli.toPublicRTKCODE(keyCodeString,cusCodeString));
        }

    }
}