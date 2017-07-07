package com.davidhernandezvilaltagmail.projecte1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.davidhernandezvilaltagmail.projecte1.activities.Profile;
import com.davidhernandezvilaltagmail.projecte1.activities.ForgottenPassword;
import com.davidhernandezvilaltagmail.projecte1.activities.Loginfail;
import com.davidhernandezvilaltagmail.projecte1.database.MyDataBaseHelper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class Login extends AppCompatActivity{
    TextView tv;
    Button login, signup;
    EditText username, password;
    MyDataBaseHelper myDataBaseHelper;
    private final static String TAG = "_MAIN_";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle("Login");

       SharedPreferences settings = getSharedPreferences("SharedLogin", Context.MODE_PRIVATE);
        boolean islogged = settings.getBoolean("logged", false);
        String userlogged = settings.getString("userlogged", "noname");
        if (!islogged) {
            initFirebaseAuth();
            Log.v("LLego", "nofia");
            initUIComponents();

            initGoogleLogin();


            username = (EditText) findViewById(R.id.username);
            myDataBaseHelper = MyDataBaseHelper.getInstance(this);
            tv = (TextView) findViewById(R.id.passwordforg);
            tv.setText(R.string.passforg);
            final Context context = this;
            tv.setOnClickListener(new View.OnClickListener(){
                public void onClick (View v) {
                    Intent i = new Intent(context, ForgottenPassword.class);
                    startActivity(i);
                }
            });
            password = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.login);
            signup = (Button) findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                   /* if (username == null||password == null){
                        Toast.makeText(getApplicationContext(), "Falta algun camp per emplenar", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String s = username.getText().toString();
                    String s1 = password.getText().toString();
                    long id = myDataBaseHelper.createRow(s, s1);
                    Toast.makeText(v.getContext(),"Has singejat in!!", Toast.LENGTH_SHORT).show();*/
                    Intent i = new Intent(getApplicationContext(), Signup.class);
                    startActivity(i);
                }
            });
            login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (username == null||password == null){
                        Toast.makeText(getApplicationContext(), "Falta algun camp per emplenar", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String s = username.getText().toString();
                    String s1 = password.getText().toString();
                    String idnt = myDataBaseHelper.queryRow(s, s1);
                    if (idnt.equals("0")){
                        //Toast.makeText(v.getContext(),"Estàs en la base de dades", Toast.LENGTH_LONG).show();
                        SharedPreferences settings = getSharedPreferences("SharedLogin", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("logged", true);
                        editor.putString("userlogged", s);
                        editor.apply();
                        finish();
                        Intent i = new Intent(getApplicationContext(), Profile.class);
                        i.putExtra("usuario", s);
                        startActivity(i);
                    }
                    else if (myDataBaseHelper.queryUser(s).equals("0")) Toast.makeText(v.getContext(), "Incorrect password", Toast.LENGTH_LONG).show();
                    else {
                        Intent i = new Intent(getApplicationContext(), Loginfail.class);
                        startActivity(i);
                        //Toast.makeText(v.getContext(),"INTRUSO JOPUTAS CABRON NO ESTAS EN LA BASE DE DADES", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            finish();
            Intent i = new Intent(getApplicationContext(), Profile.class);
            startActivity(i);
        }
    }

    ////zona login google





    private void initUIComponents() {

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                    signInGoogle();
            }
        });

    }

    private void initFirebaseAuth() {

        //Install Firebase
        //https://firebase.google.com/docs/android/setup

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    updateUI();
                }
                else{
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    private void initGoogleLogin(){

        //https://developers.google.com/identity/sign-in/android/sign-in

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(),"Connection failed",Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser u = mAuth.getCurrentUser();
        updateUI();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void loginUser(String email, String password){
        //https://firebase.google.com/docs/auth/android/start/
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void registerUser(String email, String password){
        //https://firebase.google.com/docs/auth/android/start/
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signInGoogle(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct);
            updateUI();
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //https://firebase.google.com/docs/auth/android/google-signin?utm_source=studio
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_activity_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.menu_item_logout:
                    mAuth.signOut();
                    Toast.makeText(getApplicationContext(),"Log out",Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    */
    private void updateUI(){
        FirebaseUser u= mAuth.getCurrentUser();
    }
}
