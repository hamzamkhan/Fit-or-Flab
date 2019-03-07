package com.example.hp.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.hp.project.LoginActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


import static com.example.hp.project.Downloader.TAG;

public class SignUPActivity extends Activity
{
    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextEmail,editTextAge;

    public RadioGroup radioGroup;
    public RadioButton radioButton;
    Button btnCreateAccount;

    public FirebaseAuth mAuth;
    private FirebaseFirestore dbFirestore = FirebaseFirestore.getInstance();
    private CollectionReference userDataRef = dbFirestore.collection("User Data");

    databasehelper_ db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

// get Instance of Database Adapter
        db = new databasehelper_(this);

        mAuth = FirebaseAuth.getInstance();

// Get Refferences of Views
        editTextEmail=(EditText) findViewById(R.id.txt_email);
        editTextUserName=(EditText)findViewById(R.id.UserName);
        editTextPassword=(EditText)findViewById(R.id.Password);
        editTextConfirmPassword=(EditText)findViewById(R.id.ConfirmPassword);
        editTextAge = (EditText) findViewById(R.id.UserAge) ;
        btnCreateAccount=(Button)findViewById(R.id.btn_signup);

        radioGroup = (RadioGroup) findViewById(R.id.genderRadio);

    }



    public void signUp(View v) {


        final String email = editTextEmail.getText().toString();
        final String userName=editTextUserName.getText().toString();
        final String password=editTextPassword.getText().toString();
        final String confirmPassword=editTextConfirmPassword.getText().toString();
        final int age = Integer.parseInt(editTextAge.getText().toString());


        int selectedID = radioGroup.getCheckedRadioButtonId();

        radioButton = (RadioButton) findViewById(selectedID);

        final String gender = radioButton.getText().toString();


        if(userName.equals("") || password.equals("") || confirmPassword.equals("") || email.equals(""))
        {
            Toast.makeText(this, "Field Vacant", Toast.LENGTH_SHORT).show();
        }

        if (!password.equals(confirmPassword) || !confirmPassword.equals(password))
        {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

        else
        {

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Log.d(TAG,"createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                NoteUserData noteUserData = new NoteUserData(userName,age,gender);
                                userDataRef.document(""+email).set(noteUserData);
                                Toast.makeText(SignUPActivity.this, "Sign Up Successful.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUPActivity.this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Log.w(TAG,"createUserWithEmail:failure",task.getException());
                                Toast.makeText(SignUPActivity.this, "Vacant Field/Email Already Exists.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        db.close();
    }
}