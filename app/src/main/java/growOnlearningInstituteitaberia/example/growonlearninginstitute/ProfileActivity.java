package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {



    TextView User_name,User_Email,User_Id,User_Mobile_No,User_Gender,user_Class,Logout;
    ImageView User_profile_Image,camera,qImageView;
    private DatabaseReference UserRef;
    private StorageReference UserProfileImagesRef;
    private FirebaseAuth mAuth;
    private String currentUserID,hello_banglo,cheaker="";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        UserProfileImagesRef = FirebaseStorage.getInstance ().getReference ().child ( "Profile Images" );
        hello_banglo = getIntent().getExtras().get("Users").toString();


        mAuth= FirebaseAuth.getInstance ();
        currentUserID = mAuth.getCurrentUser ().getUid ();
        UserRef= FirebaseDatabase.getInstance ().getReference ().child ( hello_banglo);

        ImageView editprofile = findViewById(R.id.edit_profile_name);
        ImageView numberadd = findViewById(R.id.numberadd_button);

        qImageView = findViewById(R.id.profile_not_verified);

        User_name = findViewById(R.id.profile_profile_name);
        User_Email= findViewById(R.id.profile_profile_email);
        User_Id= findViewById(R.id.profile_profile_id);
        User_Mobile_No= findViewById(R.id.profile_profile_numbet);
        User_Gender= findViewById(R.id.profile_profile_gender);
        user_Class= findViewById(R.id.profile_profile_class);
        Logout= findViewById(R.id.profile_logout);
        camera= findViewById(R.id.profile_camera);
        User_profile_Image= findViewById(R.id.profile_profile_image);




        progressDialog = new ProgressDialog( this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );



        HelloRetriveData();


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheaker = "image";
                Intent intent = new Intent ();
                intent.setAction ( Intent.ACTION_GET_CONTENT );
                intent.setType ( "image/*" );
                startActivityForResult ( intent.createChooser ( intent, "Select Image" ), 438 );
            }
        });



        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog mDialog = new MaterialDialog.Builder ( ProfileActivity.this)
                        .setTitle ( "Are you sure , you want to logout ?" )
                        .setCancelable ( false )
                        .setPositiveButton ( "Yes",R.drawable.not_verified, new MaterialDialog.OnClickListener () {
                            @Override
                            public void onClick(com.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                                mAuth.signOut();
                                Intent loginIntenttt = new Intent ( ProfileActivity.this,LoginActivity.class );
                                loginIntenttt.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                                startActivity ( loginIntenttt );
                                finish ();
                            }


                        } )
                        .setNegativeButton ( "Later", new MaterialDialog.OnClickListener () {
                            @Override
                            public void onClick(com.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel ();
                            }

                        } )
                        .build ();

                // Show Dialog
                mDialog.show ();

            }
        });




        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
                alertDialog.setTitle("Set up Your Name");
                alertDialog.setMessage("Enter your name..");
                final EditText input = new EditText(ProfileActivity.this);
                input.setHint("Enter Your Name");
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String nameouser = input.getText().toString();

                                if (TextUtils.isEmpty(nameouser))
                                {
                                    Toast.makeText(ProfileActivity.this, "Enter your name ..", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    progressDialog.show();

                                    mAuth= FirebaseAuth.getInstance ();
                                    currentUserID = mAuth.getCurrentUser ().getUid ();
                                    UserRef.child(currentUserID).child("Name").setValue(nameouser);
                                    dialogInterface.cancel();
                                    progressDialog.cancel();
                                }

                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alertDialog.show();

            }
        });






        numberadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
                alertDialog.setTitle("Update the Mobile No");
                alertDialog.setMessage("Enter your mobile number");
                final EditText input = new EditText(ProfileActivity.this);
                input.setHint("Enter Your Mobile Number");
                input.setInputType(InputType.TYPE_CLASS_PHONE);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String nameouser = input.getText().toString();
                                if (TextUtils.isEmpty(nameouser))
                                {
                                    Toast.makeText(ProfileActivity.this, "Enter correct mobile no.", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    progressDialog.show();
                                    mAuth= FirebaseAuth.getInstance ();
                                    currentUserID = mAuth.getCurrentUser ().getUid ();
                                    UserRef.child(currentUserID).child("MobileNo").setValue(nameouser);
                                    dialogInterface.cancel();
                                    progressDialog.cancel();
                                }

                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alertDialog.show();



            }
        });


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult ( requestCode, resultCode, data );

        progressDialog.show ();

        if (requestCode == 438 && resultCode == RESULT_OK && data != null && data.getData () != null) {


            Uri resultUri = data.getData ();
            StorageReference filepath = UserProfileImagesRef.child ( currentUserID + ".jpg");

            filepath.putFile ( resultUri ).addOnSuccessListener ( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    final Task<Uri> firebaseUri  = taskSnapshot.getStorage ().getDownloadUrl ();
                    firebaseUri.addOnSuccessListener ( new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final  String downloadUrl = uri.toString ();
                            UserRef.child ( currentUserID ).child ( "Image" ).setValue ( downloadUrl )
                                    .addOnCompleteListener ( new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful ()){
                                                Toast.makeText ( ProfileActivity.this, "Succeed", Toast.LENGTH_SHORT ).show ();
                                                progressDialog.dismiss ();
                                            }
                                            else {
                                                progressDialog.dismiss ();
                                                String message = task.getException ().toString ();
                                                Toast.makeText ( ProfileActivity.this, "Error"+message, Toast.LENGTH_SHORT ).show ();
                                            }
                                        }
                                    } );
                        }
                    } );
                }
            } );


        }
        else {
            progressDialog.dismiss ();
        }
    }

    private void HelloRetriveData() {

        progressDialog.show();


        UserRef.child ( currentUserID )
                .addValueEventListener ( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String retrieveUserNAme = dataSnapshot.child ( "Name" ).getValue ().toString ();
                        String retrieveEmail = dataSnapshot.child ( "Email" ).getValue ().toString ();
                        String retrieveImage = dataSnapshot.child ( "Image" ).getValue ().toString ();
                        String retrievemobile = dataSnapshot.child ( "MobileNo" ).getValue ().toString ();
                        String retrievegender = dataSnapshot.child ( "Gender" ).getValue ().toString ();
                        String retrieveid = dataSnapshot.child ( "ID" ).getValue ().toString ();
                        String retrieveclass = dataSnapshot.child ( "Classes" ).getValue ().toString ();


                        if (TextUtils.isEmpty(retrieveid))
                        {
                            String ft = "Your Account Not Verified.";
                            User_Id.setText(ft);
                            qImageView.setBackgroundResource(R.drawable.not_verified);
                        }
                        else{
                            User_Id.setText(retrieveid);
                            qImageView.setBackgroundResource(R.drawable.ic_baseline_verified_24);
                        }



                        User_name.setText(retrieveUserNAme);
                        User_Email.setText(retrieveEmail);
                        User_Mobile_No.setText(retrievemobile);
                        user_Class.setText(retrieveclass);
                        User_Gender.setText(retrievegender);

                        Picasso.get ().load (retrieveImage ).placeholder ( R.drawable.profile ).error ( R.drawable.profile).into (User_profile_Image);



                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        progressDialog.dismiss();
                    }
                } );
    }
}