package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
private Button pubic_remote,create_MTK_remote,create_RTK_remote,Storage_remote,custom_remote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pubic_remote=findViewById(R.id.pubic_remote);
        create_MTK_remote=findViewById(R.id.create_MTK_remote);
        create_RTK_remote=findViewById(R.id.create_RTK_remote);
        Storage_remote=findViewById(R.id.Storage_remote);
        custom_remote=findViewById(R.id.custom_remote);
        pubic_remote.setOnClickListener(this::onClick);
        create_MTK_remote.setOnClickListener(this::onClick);
        create_RTK_remote.setOnClickListener(this::onClick);
        Storage_remote.setOnClickListener(this::onClick);
        custom_remote.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pubic_remote:
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.create_MTK_remote:

                break;
            case R.id.create_RTK_remote:
                Intent intent2=new Intent(HomeActivity.this,CreateMemoteActivity.class);
                startActivity(intent2);
                break;
            case R.id.Storage_remote:
                break;
            case R.id.custom_remote:
                break;
        }

    }
}