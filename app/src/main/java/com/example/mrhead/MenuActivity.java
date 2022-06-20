package com.example.mrhead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    TextView _txName;
    Button _btMrHead, _btAbout, _btRecycler,_btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        _txName = findViewById(R.id.txName);
        _btAbout = findViewById(R.id.btAbout);
        _btMrHead = findViewById(R.id.btMrHead);
        _btRecycler = findViewById(R.id.btRecycler);
        _btLogout = findViewById(R.id.btLogout);

        _btRecycler.setOnClickListener(this);
        _btMrHead.setOnClickListener(this);
        _btAbout.setOnClickListener(this);
        _btLogout.setOnClickListener(this);

        _txName.setText(getIntent().getStringExtra("name"));

        if (_txName==null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(getApplicationContext(),"sukses",Toast.LENGTH_LONG);
            _txName.setText(user.getDisplayName());
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==_btAbout.getId()){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        } else if (v.getId()==_btMrHead.getId()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId()==_btRecycler.getId()){
            Intent intent = new Intent(this, RecyclerActivity.class);
            startActivity(intent);
        } else if (v.getId()==_btLogout.getId()){
            FirebaseAuth.getInstance().signOut();
            this.finish();
        }
    }
}