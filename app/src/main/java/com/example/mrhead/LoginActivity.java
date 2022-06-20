package com.example.mrhead;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button _btLogin;
    TextView _txPasswd, _txEmail, _txReg, _txPesan;
    String _tempEmail, _tempPasswd, _tempName, _tempPesan;
    int reqCode = 99;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _btLogin = findViewById(R.id.btLogin);
        _txReg = findViewById(R.id.txReg);
        _txEmail = findViewById(R.id.name);
        _txPasswd = findViewById(R.id.passwd);
        _txPesan = findViewById(R.id.id_txPesan);

        _txPesan.setText("");

        _btLogin.setOnClickListener(this);
        _txReg.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            if (currentUser.isEmailVerified()) {
                Intent home = new Intent(this, MenuActivity.class);
                home.putExtra("email", currentUser.getEmail());
                startActivity(home);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if(requestCode == reqCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                _tempEmail = data.getStringExtra("email");
                _tempPasswd = data.getStringExtra("passwd");
                _tempName = data.getStringExtra("name");
                _tempPesan = data.getStringExtra("pesan");
                _txPesan.setText(_tempPesan);
                _txEmail.setText(_tempEmail);
                _txPasswd.setText(_tempPasswd);
                // do something with resulting data
            }
        }
    }

    @Override
    public void onClick(View v) {
        //------------------Login dengan Firebase-----------
        if(v.getId()==R.id.btLogin)  // login
        {
            mAuth.signInWithEmailAndPassword(_txEmail.getText().toString(),_txPasswd.getText().toString())
                    .addOnCompleteListener(this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user!=null)
                                        {
                                            if (user.isEmailVerified()){
                                                Intent home = new Intent(LoginActivity.this,MenuActivity.class);
                                                home.putExtra("name",_tempName);
                                                startActivity(home);
                                            }

                                            else
                                            {
                                                Toast.makeText(LoginActivity.this, "Not verified",Toast.LENGTH_LONG).show();

                                            }
                                        }

                                    }
                                    else
                                    {
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }

                    );

        } else if (v.getId()==R.id.txReg){
            Intent intentB = new Intent(this, RegisterActivity.class);
           // intentB.putExtra("nama", "Adit");
            this.startActivityForResult(intentB, reqCode);
        }



        //------------------Login memakai intent-------------
//        if(v.getId()==_btLogin.getId()){
//            if(_tempEmail != null && _tempPasswd != null){
//                if(_tempEmail.equals(_txEmail.getText().toString()) && _tempPasswd.equals(_txPasswd.getText().toString())){
//                    Intent intent = new Intent (this, MenuActivity.class);
//                    intent.putExtra("name", _tempName);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "email dan password salah ", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "email dan password belum terdaftar ", Toast.LENGTH_SHORT).show();
//            }
//        } else if(v.getId()==_txReg.getId()) {
//            Intent intentB = new Intent(this, RegisterActivity.class);
//           // intentB.putExtra("nama", "Adit");
//            this.startActivityForResult(intentB, reqCode);
//        }

    }
}