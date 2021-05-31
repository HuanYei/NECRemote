package com.zwt.necremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zwt.necremote.db.DButil;

import java.util.HashMap;
import java.util.Map;

public class IRallActivity extends AppCompatActivity {

private static Map<String, String> nemote = new HashMap<>();
public static Map<String, String> getNemoteMap(){
        return nemote;
    }
private String TAG="IRallActivity";
private String IRall[];
private DButil dbutil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irall);
        ListView listView=findViewById(R.id.list_iritem);
        dbutil=new DButil(this);
        IRall=dbutil.selectIRALL();
        Log.e(TAG, "onCreate: "+IRall.toString() );
        ArrayAdapter adapter;
        if (IRall!=null) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, IRall);
        }else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,new String[]{"暂无遥控器，快去创建吧。"});
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(A());
    }

    public AdapterView.OnItemClickListener A(){
        return  new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor=dbutil.selectIR(IRall[position]);
                int IRKEYnumber=0;
                if (cursor!=null&&cursor.moveToFirst()){
                    Log.e(TAG, "selectIRALL: " );
                }else{
                    Log.e(TAG, "insertIR: null" );

                }
                do {
                    IRKEYnumber++;
                }while (cursor.moveToNext());
                Log.e(TAG, "selectIRALL: "+IRKEYnumber );
                String IRKEY[]=new String[IRKEYnumber];
                String IRCODE[]=new String[IRKEYnumber];
                cursor.moveToFirst();
                int i=0;
                do {
                    String key=cursor.getString(cursor.getColumnIndex("IRKEY"));
                    String code=cursor.getString(cursor.getColumnIndex("IRCODE"));
                    IRKEY[i]=key;
                    IRCODE[i]=code;
                    nemote.put(key,code);
                    i++;
                }while (cursor.moveToNext());
                Intent intent=new Intent(IRallActivity.this,MainActivity2.class);
                intent.putExtra("IRCODE",IRCODE);
                intent.putExtra("IRKEY",IRKEY);
                intent.putExtra("IRKEY",IRKEY);
                intent.putExtra("IRALL",true);
                startActivity(intent);
                finish();
            }
        };
    }
}