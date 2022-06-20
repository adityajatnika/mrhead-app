package com.example.mrhead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox _ckKumis;
    CheckBox _ckRambut;
    CheckBox _ckAlis;
    CheckBox _ckJanggut;

    ImageView _gbKumis;
    ImageView _gbRambut;
    ImageView _gbAlis;
    ImageView _gbJanggut;

    Button _btAbout, _btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find View
        _gbAlis = findViewById(R.id.gbAlis);
        _gbKumis = findViewById(R.id.gbKumis);
        _gbJanggut = findViewById(R.id.gbJanggut);
        _gbRambut = findViewById(R.id.gbRambut);
        _ckAlis = findViewById(R.id.ckALis);
        _ckKumis = findViewById(R.id.ckKumis);
        _ckJanggut = findViewById(R.id.ckJanggut);
        _ckRambut = findViewById(R.id.ckRambut);
        _btBack = findViewById(R.id.btBack);
        _btAbout = findViewById(R.id.btAbout);

        // Set Listener untuk checkbox & button
        _ckAlis.setOnClickListener(this);
        _ckKumis.setOnClickListener(this);
        _ckJanggut.setOnClickListener(this);
        _ckRambut.setOnClickListener(this);
        _btAbout.setOnClickListener(this);
        _btBack.setOnClickListener(this);

        //Pastikan semua dalam keadaan invisible
        _gbAlis.setVisibility(View.INVISIBLE);
        _gbKumis.setVisibility(View.INVISIBLE);
        _gbJanggut.setVisibility(View.INVISIBLE);
        _gbRambut.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {
        // gambar Alis
        if(v.getId()==_ckAlis.getId()){
            if(_gbAlis.getVisibility()==View.INVISIBLE){
                _gbAlis.setVisibility(View.VISIBLE);
            }else{
                _gbAlis.setVisibility(View.INVISIBLE);
            }
        }

        // gambar Kumis
        if(v.getId()==_ckKumis.getId()){
            if(_gbKumis.getVisibility()==View.INVISIBLE){
                _gbKumis.setVisibility(View.VISIBLE);
            }else{
                _gbKumis.setVisibility(View.INVISIBLE);
            }
        }

        // gambar Janggut
        if(v.getId()==_ckJanggut.getId()){
            if(_gbJanggut.getVisibility()==View.INVISIBLE){
                _gbJanggut.setVisibility(View.VISIBLE);
            }else{
                _gbJanggut.setVisibility(View.INVISIBLE);
            }
        }

        // gambar Rambut
        if(v.getId()==_ckRambut.getId()){
            if(_gbRambut.getVisibility()==View.INVISIBLE){
                _gbRambut.setVisibility(View.VISIBLE);
            }else{
                _gbRambut.setVisibility(View.INVISIBLE);
            }
        }

        //tombol back
        if(v.getId()==_btBack.getId()){
            finish();
        }

        //tombol about
        if(v.getId()==_btAbout.getId()){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

    }
}