package com.example.myapplication10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication10.R;

public class MainActivity extends AppCompatActivity {
    boolean isWork;
    int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                EditText editText = (EditText) findViewById(R.id.number);
                editText.setText(String.valueOf(msg.what));
            }
        };
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWork = true;
                EditText editText = (EditText) findViewById(R.id.number);
                editText.setEnabled(false);
                if(editText.getText().length()<1)
                {
                    number = Integer.parseInt(String.valueOf(editText.getHint()));
                }else {
                    //如果文本框被编辑过
                    number = Integer.parseInt(String.valueOf(editText.getText()));
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = number - 1; i >= 0; i--) {
                            if(isWork) {
                                try {
                                    Thread.sleep(1000);
                                    Message message = new Message();
                                    message.what = i;
                                    handler.sendMessage(message);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWork=false;
            }
        });
    }
}


