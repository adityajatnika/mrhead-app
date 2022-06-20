package com.example.mrhead;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button _btReg;
    EditText _email, _name, _passwd;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _btReg = findViewById(R.id.btLogin);
        _email = findViewById(R.id.email);
        _passwd = findViewById(R.id.passwd);
        _name = findViewById(R.id.name);

        _btReg.setOnClickListener(this);

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
    public void onClick(View v) {

        //--------------------regis menggunakan intent--------
//        // create result data as Intent
//        Intent resultIntent = new Intent(); //atau getIntent()
//        resultIntent.putExtra("email", _email.getText().toString());
//        resultIntent.putExtra("passwd", _passwd.getText().toString());
//        resultIntent.putExtra("name", _name.getText().toString());
//
//        // set result status
//        setResult(RESULT_OK, resultIntent);
//
//        // end current Activity
//        // and return to calling Activity
//        finish();

        //------------------------regis menggunakan firebase
        mAuth.createUserWithEmailAndPassword(_email.getText().toString(),_passwd.getText().toString())
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if(user!=null)
                                    {

                                        //Kasih nama, jika menggunakan auth google tidak perlu krena sdah ada nama di googlenya
                                        //nama bisa diberikan textfield sendiri agar user ketika registrasi masukan nama user tersebut
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()

                                                .setDisplayName(_name.getText().toString()) //nama user
                                                .build();


                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("gagal update profil", "User profile updated.");
                                                        }
                                                    }
                                                });
                                        /////////////////////////////////////////

                                        if (user.isEmailVerified()){   //cek sudah di verifikasi di emailnya apa belum
                                            Intent home = new Intent(RegisterActivity.this,MenuActivity.class);
                                            home.putExtra("email",_email.getText().toString());
                                            startActivity(home);
                                        }
                                        else {

                                            final String email = user.getEmail();

                                            user.sendEmailVerification().addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(RegisterActivity.this,"Email verifikasi telah dikirim ke " + email,Toast.LENGTH_SHORT).show();
                                                        Intent resultIntent = new Intent(); //atau getIntent()

                                                        // Mengirim intent ke halaman login
                                                        resultIntent.putExtra("name", _name.getText().toString());
                                                        resultIntent.putExtra("email", _email.getText().toString());
                                                        resultIntent.putExtra("passwd", _passwd.getText().toString());
                                                        resultIntent.putExtra("pesan", "Silahkan cek email untuk verifikasi, lalu login disini");

                                                        // set result status
                                                        setResult(RESULT_OK, resultIntent);

                                                        // end current Activity
                                                        // and return to calling Activity
                                                        finish();
                                                    }
                                                    else {
                                                        Log.e("Error di verifikasi", "sendEmailVerification", task.getException());
                                                        Toast.makeText(RegisterActivity.this,"Gagal mengirimkan email verifikasi.", Toast.LENGTH_SHORT).show();
                                                    }
                                                    // [END_EXCLUDE]
                                                }
                                            });

                                        }
                                    }
                                }

                                else
                                {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",Toast.LENGTH_LONG).show();
                                }

                            }
                        }

                );
    }
}