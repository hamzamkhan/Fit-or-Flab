package com.example.hp.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Symptoms extends Activity {
    CheckBox chronic_coughing,grating_joints,bloody_vomit,Nausea, spatum_cough, frquentUrination, pain_abdomen, fever, diaorhea, vomiting, fatigue, breathing_pain;
    Button diagnosis_btn;
    TextView textViewSymptom;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore dbFirestore = FirebaseFirestore.getInstance();
    private static final String KEY_DISEASE = "disease";
    private static final String TAG = "Symptoms";
    private String email;
    private String name;
    private String disease;
    private String runTimeDisease;
    private String diseaseExisting;
    private CollectionReference userDiseaseRef = dbFirestore.collection("Disease");
    private CollectionReference userDataRef = dbFirestore.collection("User Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

//        userDiseaseRef.document(""+email).get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.exists())
//                        {
//                            String disease = documentSnapshot.getString(KEY_DISEASE);
//                            textViewSymptom.setText(disease);
//                        }
//                        else
//                        {
//                            textViewSymptom.setText("No Disease Detected.");
//                        }
//                    }
//                });
//        textViewSymptom.setText();
        addListenerOnButtonClick();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        textViewSymptom = (TextView)findViewById(R.id.textViewSymptom);
        email  = user.getEmail();
        userDiseaseRef.document(""+email)
                .addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (e!=null)
                        {
                            Toast.makeText(Symptoms.this, "Error While Loading!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG,e.toString());
                        }
                        if(documentSnapshot.exists())
                        {
                            diseaseExisting = documentSnapshot.getString(KEY_DISEASE);
                            textViewSymptom.setText(diseaseExisting);
                        }
                        else
                        {
                            textViewSymptom.setText("No Disease Predicted");
                        }
                    }
                });
    }
    public void addListenerOnButtonClick(){
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        chronic_coughing=(CheckBox)findViewById(R.id.chronic_coughing);
        grating_joints=(CheckBox)findViewById(R.id.grating_joints);
        bloody_vomit=(CheckBox)findViewById(R.id.bloody_vomit);
        Nausea=(CheckBox)findViewById(R.id.Nausea);
        frquentUrination=(CheckBox)findViewById(R.id.frquentUrination);
        spatum_cough=(CheckBox)findViewById(R.id.spatum_cough);
        pain_abdomen=(CheckBox)findViewById(R.id.pain_abdomen);
        fever=(CheckBox)findViewById(R.id.fever);
        diaorhea=(CheckBox)findViewById(R.id.diaorhea);
        vomiting=(CheckBox)findViewById(R.id.vomiting);
        fatigue=(CheckBox)findViewById(R.id.fatigue);
        breathing_pain=(CheckBox)findViewById(R.id.breathing_pain);
        diagnosis_btn=(Button)findViewById(R.id.diagnosis_btn);

        //Applying the Listener on the Button click
        diagnosis_btn.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View view) {
                if ( chronic_coughing.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Asthama\n");
                    Disease.append("Influenza\n");
                    disease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease.toString(), Toast.LENGTH_LONG).show();
                }

                if ( chronic_coughing.isChecked() && grating_joints.isChecked() )
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Asthama\n");
                    Disease.append("Influenza\n");
                    Disease.append("Chickungunya");
                    runTimeDisease = Disease.toString();
                    Toast.makeText(getApplicationContext(),Disease.toString(), Toast.LENGTH_LONG).show();
                }

                if ( grating_joints.isChecked())
                {

                    StringBuilder Disease = new StringBuilder(15);
//                    Disease.append("Chickungunya");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if ( bloody_vomit.isChecked())
                {
                    String Disease= "Chikungunya";
                    runTimeDisease = Disease;
                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if ( bloody_vomit.isChecked() &&  Nausea.isChecked() )
                {
                    StringBuilder Disease = new StringBuilder(15);

//                    Disease.append("Chikungunya\n");
//                    Disease.append("Weakness\n");
//                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }
                if ( Nausea.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
//                    Disease.append("Chikungunya\n");
                    Disease.append("Weakness\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if (frquentUrination.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Diabetes\n");
                    Disease.append("Kidney Stone\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }


                if (spatum_cough.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Pneumonia\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if ( pain_abdomen.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Chikungunya\n");
                    Disease.append("Gallstone\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if (fever.isChecked())
                {
                    String Disease= "Fever";
                    runTimeDisease = Disease;

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if ( diaorhea.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Pneumonia\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if (vomiting.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
//                    Disease.append("Yellow Fever\n");
                    Disease.append("High Fever\n");
                    runTimeDisease = Disease.toString();


                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if (fatigue.isChecked() && fever.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Arthiris\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if (fatigue.isChecked() && Nausea.isChecked() )
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Chikungunya\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }
                if (fatigue.isChecked() && frquentUrination.isChecked() )
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Diabetes Type 2\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }
                if (vomiting.isChecked() && diaorhea.isChecked())
                {
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Yellow Fever\n");
//                    Disease.append("Pneumonia\n");
//                    Disease.append("High Fever\n");
                    runTimeDisease = Disease.toString();

                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if(breathing_pain.isChecked()){
                    StringBuilder Disease = new StringBuilder(15);
                    Disease.append("Asthama\n");
                    runTimeDisease = Disease.toString();



                    Toast.makeText(getApplicationContext(),Disease, Toast.LENGTH_LONG).show();
                }

                if (diseaseExisting.equals(runTimeDisease))
                {

                }
                else
                {
                    runTimeDisease = runTimeDisease+ ", "+ diseaseExisting ;
                    NoteDiseaseData noteDiseaseData = new NoteDiseaseData(runTimeDisease);
                    userDiseaseRef.document(""+email).set(noteDiseaseData);
                }


//                if(burger.isChecked()){
//                    result.append("\nBurger 120Rs");
//                    totalamount+=120;
//                }
//                result.append("\nTotal: "+totalamount+"Rs");
//                //Displaying the message on the toast
//                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
//            }
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.symptoms, menu);
//        return true;
//    }

}