package com.dayanidhid.jussave.On_Boarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dayanidhid.jussave.Adapters.CustomPagerAdapter;
import com.dayanidhid.jussave.HomeActivity;
import com.dayanidhid.jussave.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private int dotscount;
    private ImageView[] dots;

    @BindView(R.id.back)
    TextView back;

    @BindView(R.id.next)
    TextView next;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.SliderDots)
    LinearLayout sliderDotspanel;

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_screen);
        ButterKnife.bind(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSlide();
            }
        });
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(this);

        viewPager.setAdapter(customPagerAdapter);

        dotscount = customPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){
            back.setText("Skip");
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                if(position == 0){
                    back.setText("Skip");
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //todo: link to Finish activity
                            signIn();
                        }
                    });
                }else {
                    back.setText("Back");
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(position-1);
                        }
                    });
                }

                if (position == dotscount-1) {
                    next.setText("Finish");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          //todo: link to Finish activity
                          signIn();
                        }
                    });
                }else {
                    next.setText("Next");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            changeSlide();
                        }
                    });
                }

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    System.out.println("Aldready Loged in");
                } else {
                    System.out.println("Sign in via google");
                }

            }
        };
//        signIn();

    }

    public void changeSlide(){
        if(viewPager.getCurrentItem() == 0){
            viewPager.setCurrentItem(1);
        } else if(viewPager.getCurrentItem() == 1){
            viewPager.setCurrentItem(2);
        } else {
            viewPager.setCurrentItem(0);
        }
    }

    //



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        System.out.println("****CURRENT USER " + currentUser);
    }

    @Override
    public void onStop() {

        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                SharedPreferences sharedpreferences = getSharedPreferences("JUSSAVE_PREF", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();

                GoogleSignInAccount account = result.getSignInAccount();
                GoogleSignInAccount acct = result.getSignInAccount();
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                editor.putString("uname",personName);
                editor.putString("gmailid",personEmail);
                editor.putString("familyname",personFamilyName);
                editor.putString("photourl",personPhoto.toString());
//                editor.putString("isLogin","yes1");
                editor.putBoolean("isLogin",true);
                editor.commit();

                Toast.makeText(getApplicationContext(),"Sucessfully Loged IN AS "+personName ,Toast.LENGTH_LONG).show();
                //if needed add creds to shared pref

                System.out.println("CREDS ARE "+ personEmail+"\n"+personFamilyName+"\n"+personGivenName+"\n"+personId+"\n"+personName+"\n"+personPhoto);
                firebaseAuthWithGoogle(account);
            } else {

                System.out.println("****Google Sign In failed");
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("****USER " + user);
                            Toast.makeText(getApplicationContext(),"Sucessfully Loged IN AS "+user,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            finish();

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(EducationScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        mAuth.signOut();
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(getApplicationContext(),"Sucessfully Loged Out",Toast.LENGTH_LONG).show();
            }
        });

    }
}
