package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TextView User_name,User_Email;
    private DatabaseReference UserRef;
    private FirebaseAuth mAuth;
    private String currentUserID,Image_Link = "https://firebasestorage.googleapis.com/v0/b/grow-on-computer-center.appspot.com/o/profile%20(2).png?alt=media&token=28a91235-8196-478f-b570-574aed5c7de0";
    ImageView User_Image,Edit_Image;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        mAuth= FirebaseAuth.getInstance ();
        currentUserID = mAuth.getCurrentUser ().getUid ();
        UserRef= FirebaseDatabase.getInstance ().getReference ().child ( "Users");



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChatListActivity.class);
                intent.putExtra("Users","Teachers");
                intent.putExtra("Image",Image_Link);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_course,R.id.nav_exam, R.id.nav_gallery, R.id.nav_about_us,R.id.nav_faq,R.id.nav_contact,R.id.nav_developer)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View header = navigationView.getHeaderView(0);
        User_name = (TextView) header.findViewById(R.id.nav_header_name);
        User_Email = (TextView) header.findViewById(R.id.nav_header_email);
        User_Image= (ImageView) header.findViewById(R.id.nav_header_image);
        Edit_Image = (ImageView) header.findViewById(R.id.nav_header_edit);



       UserRef.child ( currentUserID )
                .addValueEventListener ( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String retrieveUserNAme = dataSnapshot.child ( "Name" ).getValue ().toString ();
                        String retrieveEmail = dataSnapshot.child ( "Email" ).getValue ().toString ();
                        String retrieveImage = dataSnapshot.child ( "Image" ).getValue ().toString ();

                        Image_Link = retrieveImage;

                        User_name.setText(retrieveUserNAme);
                        User_Email.setText(retrieveEmail);

                        Picasso.get ().load (retrieveImage ).placeholder ( R.drawable.profile ).error ( R.drawable.profile).into (User_Image);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );












        Edit_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentl = new Intent(MainActivity.this,ProfileActivity.class);
                intentl.putExtra("Users","Users");
                startActivity(intentl);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_my_profile:
                Intent intentl2 = new Intent(MainActivity.this,ProfileActivity.class);
                intentl2.putExtra("Users","Users");
                startActivity(intentl2);
                return true;

            case R.id.action_faculty:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_logout:
                 MaterialDialog mDialog = new MaterialDialog.Builder ( MainActivity.this)
                    .setTitle ( "Are you sure , you want to logout ?" )
                    .setCancelable ( false )
                    .setPositiveButton ( "Yes",R.drawable.not_verified, new MaterialDialog.OnClickListener () {
                        @Override
                        public void onClick(com.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                            mAuth.signOut();
                            Intent loginIntenttt = new Intent ( MainActivity.this,LoginActivity.class );
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
                return true;

            case R.id.action_how_to_use:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}