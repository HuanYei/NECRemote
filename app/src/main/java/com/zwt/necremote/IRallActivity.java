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
import android.widget.Toast;

import com.zwt.necremote.db.DButil;
import com.zwt.necremote.utli.MyDialog;

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
private ArrayAdapter adapter;
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irall);
        listView=findViewById(R.id.list_iritem);
        dbutil=new DButil(this);
        try {
            IRall=dbutil.selectIRALL();
        }catch (Exception e){
            IRall=null;
        }



        if (IRall!=null) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, IRall);
            Log.e(TAG, "onCreate: "+IRall.toString() );
        }else {

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,new String[]{"暂无遥控器，快去创建吧。"});
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(A());
        listView.setOnItemLongClickListener(LongONclick());
    }

    private AdapterView.OnItemLongClickListener LongONclick() {
            return new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e(TAG, "onItemLongClick: "+position );
                    MyDialog myDialog=new MyDialog(IRallActivity.this,R.style.MyDialog);
                    myDialog.setTitle("提示!!!");
                    myDialog.setMessage("确定要删除吗？");
                    myDialog.setYesOnclickListener("确定",new MyDialog.onYesOnclickListener(){
                        @Override
                        public void onYesOnclick() {
                            if(dbutil.DELETEIR(IRall[position])) {
                                Toast.makeText(IRallActivity.this, "成功删除", Toast.LENGTH_SHORT).show();
                                IRall = dbutil.selectIRALL();
                                adapter.notifyDataSetChanged();
                                adapter = new ArrayAdapter<String>(IRallActivity.this, android.R.layout.simple_expandable_list_item_1, IRall);
                                listView.setAdapter(adapter);
                                myDialog.dismiss();
                            }
                            else {
                                Toast.makeText(IRallActivity.this, "删除失败，请确保是否存在", Toast.LENGTH_SHORT).show();
                                myDialog.dismiss();
                            }
                        }
                    });
                    myDialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            myDialog.dismiss();
                        }
                    });
                    myDialog.show();
                    return true;

                }
            };
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
                intent.putExtra("RMNAME",IRall[position]);
                intent.putExtra("IRALL",true);
                startActivity(intent);
                finish();
            }
        };
    }

}