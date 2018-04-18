package com.example.byronsalgado.cashapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {

    private ZXingScannerView escanerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button QrScan = (Button)findViewById(R.id.Qrbtn);
        final Button Currency = (Button)findViewById(R.id.Currencybtn);
        final Button Generator = (Button)findViewById(R.id.QrGeneratorbtn);

        QrScan.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), QrActivity.class);
                startActivity(startIntent);
            }
        });

        Currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), CurrencyConverterActivity.class);
                startActivity(startIntent);
            }
        });

        Generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent starIntent = new Intent(getApplicationContext(), QrGenerator.class);
               startActivity(starIntent);
            }
        });

    }


}
