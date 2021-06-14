package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shreyaspatil.MaterialDialog.MaterialDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private LinearLayout linearLayout,linearLayout2;
    private EditText password,email,name,mobile_no,gender,classs;
    private TextView signin;
    private RelativeLayout submit;
    private DatabaseReference RootRef;
    private DatabaseReference RootRefmain;
    private FirebaseAuth mauth;
    private TextView tandc,pp;
    private ProgressDialog progressDialog;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        linearLayout = findViewById(R.id.hacking_register);
        linearLayout2 = findViewById(R.id.hacking_register2);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout2.setVisibility(View.VISIBLE);
            }
        });

        mauth = FirebaseAuth.getInstance ();
        tandc = findViewById(R.id.termsandcondition);
        pp=findViewById(R.id.privacypolicy);
        RootRefmain = FirebaseDatabase.getInstance ().getReference ();
        password = findViewById(R.id.signup_password);
        email = findViewById(R.id.signup_email_adress);
        checkBox = findViewById(R.id.checkbox);
        name = findViewById(R.id.signup_name);
        mobile_no = findViewById(R.id.signup_mobile);
        gender = findViewById(R.id.signup_gender);
        classs = findViewById(R.id.signup_class);
        signin = findViewById(R.id.signup_signin_button);
        submit = findViewById(R.id.signup_button);
        progressDialog = new ProgressDialog (this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait" );
        progressDialog.setMessage ( "Tips: Please Cheak your Internet or Wi-fi Connection" );
        progressDialog.setCanceledOnTouchOutside ( false );

        RootRef= FirebaseDatabase.getInstance ().getReference ();


        tandc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog mDialog = new MaterialDialog.Builder ( RegisterActivity.this)
                        .setTitle ( "Terms and Conditions" )
                        .setCancelable ( true )

                        .build ();

                // Show Dialog
                mDialog.show ();
            }
        });


        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog mDialog = new MaterialDialog.Builder ( RegisterActivity.this)
                        .setTitle ( "Privacy Policy" )
                        .setCancelable ( true )

                        .build ();

                // Show Dialog
                mDialog.show ();
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoLoginActivity();
            }
        });

        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String passwordstring = password.getText ().toString ();
                String emialiIDstring= email.getText ().toString ();
                String namestring= name.getText ().toString ();
                String mobile_string = mobile_no.getText().toString();
                        String gender_string = gender.getText().toString();
                        String class_string = classs.getText().toString();


                if (TextUtils.isEmpty ( passwordstring ))
                {
                    Toast.makeText ( RegisterActivity.this, "You do not input Password", Toast.LENGTH_SHORT ).show ();
                }

                else if (TextUtils.isEmpty ( emialiIDstring ))
                {
                    Toast.makeText ( RegisterActivity.this, "You do not input Email ID ", Toast.LENGTH_SHORT ).show ();
                }
                else if (TextUtils.isEmpty ( namestring ))
                {
                    Toast.makeText ( RegisterActivity.this, "You do not input Name ", Toast.LENGTH_SHORT ).show ();
                }
                else if (TextUtils.isEmpty ( class_string ))
                {
                    Toast.makeText ( RegisterActivity.this, "You do not input Name ", Toast.LENGTH_SHORT ).show ();
                }
                else if (TextUtils.isEmpty ( gender_string))
                {
                    Toast.makeText ( RegisterActivity.this, "You do not input Name ", Toast.LENGTH_SHORT ).show ();
                }
                else if (TextUtils.isEmpty ( mobile_string ))
                {
                    Toast.makeText ( RegisterActivity.this, "You do not input Name ", Toast.LENGTH_SHORT ).show ();
                }
                else
                {
                    CreateNewAccount ();
                }


            }
        } );
    }

    private void  CreateNewAccount()
    {
        String emailstring = email.getText ().toString ();
        String passwordstring = password.getText ().toString ();
        String namestring = name.getText ().toString ();
        String mobile_string = mobile_no.getText().toString();
        String gender_string = gender.getText().toString();
        String class_string = classs.getText().toString();

        progressDialog.show();



        mauth.createUserWithEmailAndPassword ( emailstring,passwordstring )
                .addOnCompleteListener ( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful ())
                        {
                            String cuurrentUserID = mauth.getCurrentUser ().getUid ();



                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "Name" ).setValue ( namestring );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "Email" ).setValue ( emailstring );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "MobileNo" ).setValue ( mobile_string );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "Gender" ).setValue ( gender_string );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "Classes" ).setValue ( class_string );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "ID" ).setValue ( "" );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "MyCourse" ).setValue ( "" );
                            RootRef.child ( "Users" ).child ( cuurrentUserID ).child ( "Image" ).setValue ( "https://firebasestorage.googleapis.com/v0/b/grow-on-computer-center.appspot.com/o/profile%20(2).png?alt=media&token=28a91235-8196-478f-b570-574aed5c7de0" );

                                        Toast.makeText (RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT ).show ();

                                        GotoLoginActivity();
                                        progressDialog.dismiss();


                        }
                        else
                        {
                            String message = task.getException ().toString ();
                            progressDialog.dismiss();
                            Toast.makeText ( RegisterActivity.this, "Error" + message, Toast.LENGTH_SHORT ).show ();

                        }
                    }
                } );






    }
    private void GotoLoginActivity() {


        Intent loginIntent = new Intent ( RegisterActivity.this,LoginActivity.class );
        loginIntent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity ( loginIntent );
        finish ();
    }
}