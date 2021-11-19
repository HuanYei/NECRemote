package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zwt.necremote.db.DButil;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
private Button pubic_remote,create_MTK_remote,create_RTK_remote,Storage_remote,custom_remote,code_remote;
private DButil dbutil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pubic_remote=findViewById(R.id.pubic_remote);
        create_MTK_remote=findViewById(R.id.create_MTK_remote);
        create_RTK_remote=findViewById(R.id.create_RTK_remote);
        Storage_remote=findViewById(R.id.Storage_remote);
        custom_remote=findViewById(R.id.custom_remote);
        code_remote=findViewById(R.id.code_remote);
        code_remote.setOnClickListener(this::onClick);
        pubic_remote.setOnClickListener(this::onClick);
        create_MTK_remote.setOnClickListener(this::onClick);
        create_RTK_remote.setOnClickListener(this::onClick);
        Storage_remote.setOnClickListener(this::onClick);
        custom_remote.setOnClickListener(this::onClick);
        dbutil=new DButil(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pubic_remote:
                Intent intent=new Intent(HomeActivity.this,ToptechActivity.class);
                startActivity(intent);
                break;
            case R.id.create_MTK_remote:
                Intent intent6=new Intent(HomeActivity.this,CreateMemoteActivity.class);
                intent6.putExtra("IRType","MTK");
                startActivity(intent6);
                break;
            case R.id.create_RTK_remote:
                Intent intent2=new Intent(HomeActivity.this,CreateMemoteActivity.class);
                intent2.putExtra("IRType","RTK");
                startActivity(intent2);

                break;
            case R.id.Storage_remote:
                if (dbutil.selectIRALL()==null){
                    Toast.makeText(HomeActivity.this, "您还没有创建遥控器！", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent3 = new Intent(HomeActivity.this, IRallActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.custom_remote:
                Intent intent8=new Intent(HomeActivity.this,NetworkRemoteActivity.class);
                startActivity(intent8);
                break;
            case R.id.code_remote:
                Intent intent9=new Intent(HomeActivity.this,SendCodeActivity.class);
                startActivity(intent9);
                break;
        }

    }
}