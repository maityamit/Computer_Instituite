package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CourseDetailsActivity extends AppCompatActivity {


    private TextView name,price,duration,course;
    private ImageView imageView;
    private DatabaseReference RootRef;
    private LinearLayout contact;
    private String cr_id="";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        cr_id = getIntent().getExtras().get("CR_ID").toString();

        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( "Institute" ).child("Course").child(cr_id);

        name = findViewById(R.id.cr_view_main_text);
        price =  findViewById(R.id.cr_view_main_price);
        duration =  findViewById(R.id.cr_view_main_duration);
        course =  findViewById(R.id.cr_view_main_syllabus);
        imageView =  findViewById(R.id.cr_view_main_image);
        contact = findViewById(R.id.cr_view_main_official_link);


        progressDialog = new ProgressDialog( this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );




        fetchdat();






    }

    private void fetchdat() {

        progressDialog.show();

        RootRef.addValueEventListener ( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String stringname = dataSnapshot.child ( "Text" ).getValue ().toString ();
                String stringprice = String.valueOf(dataSnapshot.child("Price").getValue()).toString();
                String string_main_image = dataSnapshot.child("Image").getValue().toString();
                String stringrduration = dataSnapshot.child("Duration").getValue().toString();
                String string_deatils = dataSnapshot.child("Details").getValue().toString();

                name.setText(stringname);
                price.setText("\uD83D\uDCB0 Price: "+stringprice);
                duration.setText("⌛ Duration: "+stringrduration);
                course.setText(string_deatils);

                Picasso.get ().load (string_main_image ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder).into (imageView);


                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        } );


    }
}