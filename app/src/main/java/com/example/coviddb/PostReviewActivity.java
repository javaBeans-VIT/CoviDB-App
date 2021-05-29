package com.example.coviddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PostReviewActivity extends AppCompatActivity {
    private EditText PatientName,PatientID,HospitalName,Address,Description;
    private Button button6,button7;
    private String patientname,patientid,hospitalname,address,description;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);



        PatientName = findViewById(R.id.postReviewPatientName);
        PatientID = findViewById(R.id.postReviewPatientID);
        HospitalName = findViewById(R.id.postReviewHospitalName);
        Address = findViewById(R.id.postReviewAddress);
        Description = findViewById(R.id.description);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        floatingActionButton = findViewById(R.id.floatingButtonToPosts);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostReviewActivity.this,ReviewActivity.class);
                startActivity(intent);
            }
        });


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidatePost();
            }
        });
    }

    private void ValidatePost() {
        patientname = PatientName.getText().toString();
        patientid = PatientID.getText().toString();
        hospitalname = HospitalName.getText().toString();
        address = Address.getText().toString();
        description = Description.getText().toString();

        if(TextUtils.isEmpty(patientname)){
            Toast.makeText(this, "Enter Patient's Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(patientid)){
            Toast.makeText(this, "Enter PatientID", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(hospitalname)){
            Toast.makeText(this, "Enter Hospital Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(address)){
            Toast.makeText(this, "Enter Hospital's Address", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(description)){
            Toast.makeText(this, "Enter your review", Toast.LENGTH_SHORT).show();
        }
        else {
                StoringInFirebase();
        }
    }

    private void StoringInFirebase() {
        HashMap<String  , String> userMap = new HashMap<>();
        userMap.put("PatientName",patientname);
        userMap.put("PatientID",patientid);
        userMap.put("HospitalName",hospitalname);
        userMap.put("Address",address);
        userMap.put("Description",description);

        root.push().setValue(userMap);
        Toast.makeText(this, "Review Added!", Toast.LENGTH_SHORT).show();

    }
}