package com.jrumpe.surveyfirebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jrumpe.surveyfirebase.model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;

    private Button btnSignin, btnRegister;

    RelativeLayout rootLayout;
    DatabaseReference table_users;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Before setContentView */

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        btnSignin = (Button) findViewById(R.id.btn_signin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        table_users = db.getReference("users");

        AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_register, null);

        dialog.setTitle("REGISTER");
        dialog.setMessage("Use your Email to Register");

        final MaterialEditText edtEmail = (MaterialEditText) mView.findViewById(R.id.edtEmail);
        final MaterialEditText edtPass = (MaterialEditText) mView.findViewById(R.id.edtPass);
        final MaterialEditText edtPass2 = (MaterialEditText) mView.findViewById(R.id.edtPass2);
        final MaterialEditText edtName = (MaterialEditText) mView.findViewById(R.id.edtName);
        final MaterialEditText edtPhone = (MaterialEditText) mView.findViewById(R.id.edtPhone);


        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
                progressDialog.setMessage("Registering ... Please wait");
                progressDialog.show();

                final String email, pass, pass2, name, phone;

                email = edtEmail.getText().toString();
                pass = edtPass.getText().toString();
                pass2 = edtPass2.getText().toString();
                name = edtName.getText().toString();
                phone = edtPhone.getText().toString();

                if (TextUtils.isEmpty(email)) {

                    edtEmail.setError("Enter Email");
                    edtEmail.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(pass)) {

                    edtPass.setError("Enter Password");
                    edtPass.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(pass2)) {

                    edtPass2.setError("Confirm Password");
                    edtPass2.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(name)) {

                    edtName.setError("Enter Name");
                    edtName.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(phone)) {

                    edtPhone.setError("Enter Phone");
                    edtPhone.requestFocus();
                    return;

                }
                if (pass.length() < 5) {
                    edtPass.setError("Password too short");
                    edtPass.requestFocus();
                    return;

                }
                if (pass2.length() < 5) {
                    edtPass2.setError("Password too short");
                    edtPass2.requestFocus();
                    return;

                }
                if (!edtPass.getText().toString().equalsIgnoreCase(edtPass2.getText().toString())) {
                    edtPass2.setError("Password Dont match");
                    edtPass2.requestFocus();
                    return;
                } else {
                    // Create user

                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User user = new User(name, email, phone);
                                    table_users.child(mAuth.getUid()).setValue(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(HomeActivity.this, "User Successfully registered", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(HomeActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(HomeActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }

            }
        });


        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setView(mView);
        dialog.show();
    }

    private void loginUser() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_signin, null);

        dialog.setTitle("SIGN IN");
        dialog.setMessage("Use your Email to Sign In");


        final MaterialEditText edtEmail = (MaterialEditText) mView.findViewById(R.id.edtEmail);
        final MaterialEditText edtPass = (MaterialEditText) mView.findViewById(R.id.edtPass);

        dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
                progressDialog.setMessage("Signing in ...Please wait");
                progressDialog.show();

                final String email, pass;

                email = edtEmail.getText().toString();
                pass = edtPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(HomeActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(HomeActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    progressDialog.dismiss();
                    //login user
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(HomeActivity.this, SurveysHome.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(HomeActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }

            }
        });


        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setView(mView);
        dialog.show();
    }

}
