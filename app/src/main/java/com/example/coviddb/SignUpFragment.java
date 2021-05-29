package com.example.coviddb;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {
    EditText email,fullname,pass,confirmPass;
    Button signUp;
    FirebaseAuth mFirebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fargment,container,false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = root.findViewById(R.id.email);
        fullname = root.findViewById(R.id.fullname);
        pass = root.findViewById(R.id.pass);
        confirmPass = root.findViewById(R.id.confirmPass);
        signUp = root.findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String n = fullname.getText().toString();
                String p = pass.getText().toString();
                String cp = confirmPass.getText().toString();
                if(e.isEmpty()){
                    email.setError("Please enter email Id");
                    email.requestFocus();
                }
                else if(n.isEmpty()){
                    fullname.setError("Please enter Full Name");
                    fullname.requestFocus();
                }
                else if(p.isEmpty()){
                    pass.setError("Please enter password");
                    pass.requestFocus();
                }
                else if(cp.isEmpty()){
                    confirmPass.setError("Confirm your password");
                    confirmPass.requestFocus();
                }
                else if(!p.equals(cp)){
                    confirmPass.setError("Password don't match!");
                    confirmPass.requestFocus();
                }
                else{
                    mFirebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getActivity(), "Error: Sign Up unsuccessful!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(getActivity(),EnterMobileNumber.class));
                            }
                        }
                    });
                }
            }
        });


        return root;
    }
}
