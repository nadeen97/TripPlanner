package com.example.trippalnner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.google.firebase.database.FirebaseDatabase;

import UI.AddTripActivity;
import UI.HomeTripActivity;

import static android.os.ParcelFileDescriptor.MODE_APPEND;



public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;

    Button loginBtn,signupBtn;
    SignInButton googleBtn;
    LoginButton facbookBtn;
    ProgressBar progressBar;

    EditText emailText,passwordText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //intilizing ui component
        googleBtn = findViewById(R.id.google);
        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signup);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        facbookBtn = findViewById(R.id.facebook);
        progressBar=findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        facbookBtn.setReadPermissions("email", "public_profile");
    facbookBtn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
    @Override
    public void onSuccess(LoginResult loginResult) {
    Toast.makeText(LoginActivity.this,"Suceeful login to facebook",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
});



        //Sign in with google
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);


                mAuth = FirebaseAuth.getInstance();
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

                updateUI(mAuth.getCurrentUser());

            }

        });

        //Sign up button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailText.getText().toString().equals("") && !passwordText.getText().toString().equals("")) {
                        progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this,"You Are logged in",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                updateUI(user);
                                Intent goToHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(goToHome);
                            } else {
                                updateUI(null);
                            }
                        }
                    });
                }}
        });

//Login Button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailText.getText().toString().equals("") && !passwordText.getText().toString().equals("")) {

                    mAuth.signInWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseUser user=mAuth.getCurrentUser();

                                updateUI(user);
                            }
                            else
                            {
                                updateUI(null);
                            }
                        }
                    });
                }}
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }
    private void updateUI(FirebaseUser user)
    {

        if (user!=null)
        {
            Toast.makeText(LoginActivity.this,"You Are logged in",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this , HomeTripActivity.class);
            intent.putExtra("email",user.getEmail());
            intent.putExtra("uid",mAuth.getUid());
            System.out.println(user.getEmail());
            System.out.println(mAuth.getUid());

            startActivity(intent);
        }
        if (user == null)
        {
            Toast.makeText(LoginActivity.this,"Authontication failed",Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {


        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            updateUI(null);
                        }

                        // ...
                    }
                });
    }




    
    public void doSomething(View view) {

        Intent intent = new Intent(getApplicationContext(), AddTripActivity.class);

        LoginActivity.this.startActivity(intent);
}
}
