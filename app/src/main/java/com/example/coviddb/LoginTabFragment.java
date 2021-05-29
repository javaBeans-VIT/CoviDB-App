package com.example.coviddb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {
    EditText email,pass;
    TextView forgetPass;
    Button login;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    float v=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragement,container,false);
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forgetpass);
        login = root.findViewById(R.id.button);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(getActivity(), "Logged In!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Please Login!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String p = pass.getText().toString();
                if(e.isEmpty()){
                    email.setError("Please enter email Id");
                    email.requestFocus();

                }

                else if(p.isEmpty()){
                    pass.setError("Please enter password");
                    pass.requestFocus();
                }
                else if (!(e.isEmpty() && p.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getActivity(), "Login Error, Login Again!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent = new Intent(getActivity(),MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(), "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
