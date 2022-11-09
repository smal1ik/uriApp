package com.example.uriapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {

        EditText et = findViewById(R.id.editText);
        String str = et.getText().toString();
        RadioGroup rg = findViewById(R.id.radioGroup);
        int check = rg.getCheckedRadioButtonId();

        if (check == -1) {
            check = getTypeString(str);
        }else if(str.isEmpty()) {
            check = -1;
        }else{
            switch (check) {
                case R.id.tel:
                    check = 1;
                    break;
                case R.id.geo:
                    check = 2;
                    break;
                case R.id.url:
                    check = 0;
                    break;
            }
        }

        Uri uri;
        switch (check){
            case 0:
                if (!str.contains("http://")){
                    str = "http://" + str;
                }
                uri = Uri.parse(str);
                Intent url = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(url);
                break;
            case 1:
                uri = Uri.parse("tel: " + str);
                Intent call = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(call);
                break;
            case 2:
                uri = Uri.parse("geo: " + str);
                Intent geo = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(geo);
                break;
        }


    }

    public Integer getTypeString(String line){

        for (int i = 0; i < line.length(); i++){
            if (Character.isLetter(line.charAt(i))){
                return 0;
            }
        }

        if (line.contains(",")||(line.contains(" "))){
            return 2;
        }
        return 1;
    }

}