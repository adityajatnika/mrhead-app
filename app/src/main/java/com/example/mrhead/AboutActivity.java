package com.example.mrhead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    TextView _txNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        _txNumber = findViewById(R.id.txNumber);
        _txNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String url = "https://api.whatsapp.com/send?phone=085860708361";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}