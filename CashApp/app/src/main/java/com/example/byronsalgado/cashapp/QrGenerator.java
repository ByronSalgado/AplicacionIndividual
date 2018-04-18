package com.example.byronsalgado.cashapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QrGenerator extends AppCompatActivity {

    private EditText generatortxt;
    private Button generatebtn;
    private ImageView imageView;
    private String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        generatortxt = (EditText)findViewById(R.id.Quantitytxt);
        generatebtn = (Button)findViewById(R.id.Qrgeneratorbtn);
        imageView = (ImageView)findViewById(R.id.image);

        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2Qr = generatortxt.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder =  new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);

                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
