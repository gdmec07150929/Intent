package com.gourd.adien.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.url);
        et2 = (EditText) findViewById(R.id.phone);
        tv1 = (TextView) findViewById(R.id.textview1);
    }

    public void componentname(View v){
        ComponentName componentName = new ComponentName(this, IntentDemo2.class);
        Intent i1 = new Intent();
        i1.setComponent(componentName);

        startActivity(i1);
    }

    public void intentfilter(View v) {
        String action = "cn.edu.gdmec.kissme";
        Intent i2 = new Intent();
        i2.setAction(action);
        startActivity(i2);
    }

    public void view(View v) {
        Intent i3 = new Intent();
        // i3.setAction(Intent.ACTION_DIAL);
        i3.setAction(Intent.ACTION_VIEW); // 打开网页是ACTION_VIEW
        Uri uri = Uri.parse(et1.getText().toString());
        i3.setData(uri);
        startActivity(i3);
    }

    public void dial(View v) {
        Intent i3 = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:"+et2.getText().toString());
        i3.setData(uri);
        startActivity(i3);
    }

    public void startactivityforresult(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("value",et1.getText().toString());
        Intent intent = new Intent(MainActivity.this,IntentDemo2.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 最好加上一句判断 是不是RESULT_OK
        if (resultCode == RESULT_OK){

            switch (requestCode){
                case 10:
//                    Bundle bundle = data.getExtras();
//                    tv1.setText(bundle.getString("result"));
                    // 直接取值即可
                    tv1.setText(data.getStringExtra("result"));
                    break; // 缺少break;
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
