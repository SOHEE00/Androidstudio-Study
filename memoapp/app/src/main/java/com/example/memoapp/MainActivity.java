package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText Text,TextBox;
    Button btnread, btnwrite, btndelete;
    String filename = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = findViewById(R.id.Text);
        TextBox = findViewById(R.id.TextBox);
        btnread = findViewById(R.id.btnread);
        btnwrite = findViewById(R.id.btnwrite);
        btndelete = findViewById(R.id.btndelete);

        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename = Text.getText().toString();
                try{
                    InputStream inf = openFileInput(filename);
                    byte[] txt = new byte[inf.available()];
                    inf.read(txt);
                    inf.close();
                    TextBox.setText(new String(txt));
                    Toast.makeText(getApplicationContext(),filename + "읽기 완료", Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(),filename + "읽기 실패", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename = Text.getText().toString();
                try{
                    FileOutputStream outf = openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = TextBox.getText().toString();
                    outf.write(str.getBytes());
                    outf.close();
                    Toast.makeText(getApplicationContext(), filename + "이 생성", Toast.LENGTH_LONG).show();
                }catch (IOException e) {
                    Toast.makeText(getApplicationContext(), filename + "생성 실패", Toast.LENGTH_LONG).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename = Text.getText().toString();

                if (filename.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "삭제할 파일명을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    String filename = Text.getText().toString();
                    deleteFile(filename);
                    Toast.makeText(getApplicationContext(), "파일 삭제 완료", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "파일 삭제 중 오류 발생", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}