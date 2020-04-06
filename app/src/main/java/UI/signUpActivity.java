package UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trippalnner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUpActivity extends AppCompatActivity {

    EditText emailText,passwordText;
    TextView errortxt ;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        errortxt = findViewById(R.id.error_txt);
        progressBar=findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        signup=findViewById(R.id.signUp_btn);
        if (savedInstanceState!= null){
            emailText.setText(savedInstanceState.getString("email"));
            passwordText.setText(savedInstanceState.getString("pass"));
            errortxt.setText(savedInstanceState.getString("error"));


        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailText.getText().toString().equals("") && !passwordText.getText().toString().equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString()).addOnCompleteListener(signUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(signUpActivity.this,"You Are Sign Up",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                updateUI(user);
                                Intent goToHome = new Intent(signUpActivity.this, HomeTripActivity.class);
                                startActivity(goToHome);
                            } else {
                                updateUI(null);
                            }
                        }
                    });
                }}
        });
        }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email",emailText.getText().toString());
        outState.putString("pass",passwordText.getText().toString());
        outState.putString("error",errortxt.getText().toString());

    }
    private void updateUI(FirebaseUser user)
    {

        if (user!=null)
        {
            Toast.makeText(signUpActivity.this,"You Are logged in",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this , HomeTripActivity.class);

            startActivity(intent);
        }
        if (user == null)
        {
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("Invalid Email or password");
            progressBar.setVisibility(View.GONE);
            //Toast.makeText(signUpActivity.this,"Authontication failed",Toast.LENGTH_LONG).show();
        }
    }
}
