package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TeacherdashBoardsActivity extends AppCompatActivity {

    private TextView chat,upcoming,myProfile;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    private String Namee= "";
    private String currentUserID,Image_Link = "https://firebasestorage.googleapis.com/v0/b/grow-on-computer-center.appspot.com/o/profile%20(2).png?alt=media&token=28a91235-8196-478f-b570-574aed5c7de0";;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherdash_boards);

        chat = findViewById(R.id.teachers_chat);

        upcoming = findViewById(R.id.teachers_upcoming_class);

        myProfile = findViewById(R.id.teachers_my_profile);



        mAuth= FirebaseAuth.getInstance ();
        currentUserID = mAuth.getCurrentUser ().getUid ();
        UserRef= FirebaseDatabase.getInstance ().getReference ().child ( "Teachers");


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentl = new Intent(TeacherdashBoardsActivity.this,ChatListActivity.class);
                intentl.putExtra("Users","Users");
                intentl.putExtra("Image",Image_Link);
                intentl.putExtra("Name",Namee);
                startActivity(intentl);
            }
        });


        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentchat= new Intent(TeacherdashBoardsActivity.this,ProfileActivity.class);
                intentchat.putExtra("Users","Teachers");
                startActivity(intentchat);
            }
        });



        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentq = new Intent(TeacherdashBoardsActivity.this,CourseListActivityforTeachersActivity.class);
                startActivity(intentq);
            }
        });


        UserRef.child ( currentUserID )
                .addValueEventListener ( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String retrieveImage = dataSnapshot.child ( "Image" ).getValue ().toString ();
                        String retrieveName = dataSnapshot.child ( "Name" ).getValue ().toString ();

                        Image_Link = retrieveImage;
                        Namee = retrieveName;



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );


    }
}