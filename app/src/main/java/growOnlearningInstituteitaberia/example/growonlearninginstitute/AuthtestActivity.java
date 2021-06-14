package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AuthtestActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private String currentUserID;
    DatabaseReference rootRef;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authtest);

        mAuth = FirebaseAuth.getInstance ();
        RootRef= FirebaseDatabase.getInstance ().getReference ();

      rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    @Override
    protected void onStart() {


        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser ();

        if (currentUser == null)
        {

            SendUserToLoginActivity();
        }
        else
        {
            currentUserID = mAuth.getCurrentUser ().getUid ();


            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot snapshot) {

                    if (snapshot.hasChild(currentUserID)) {
                        UpdateUserStatus("online");
                        Intent loginIntentt = new Intent ( AuthtestActivity.this,MainActivity.class );
                        loginIntentt.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity ( loginIntentt );
                        finish ();
                    }
                    else {

                        Intent loginIntentt = new Intent ( AuthtestActivity.this,TeacherdashBoardsActivity.class );
                        loginIntentt.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity ( loginIntentt );
                        finish ();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
    }


    private void UpdateUserStatus(String state)
    {
        String saveCurrentTime,SaveCurrentData;
        Calendar calendar = Calendar.getInstance ();


        SimpleDateFormat currentdate = new SimpleDateFormat ( "MMM dd, yyyy" );
        SaveCurrentData = currentdate.format ( calendar.getTime () );
        SimpleDateFormat currenttime = new SimpleDateFormat ( "hh:mm a" );
        saveCurrentTime = currenttime.format ( calendar.getTime () );


        HashMap<String,Object> onlineStat = new HashMap<> (  );
        onlineStat.put ( "user_time",saveCurrentTime );
        onlineStat.put ( "user_date",SaveCurrentData );
        onlineStat.put ( "user_state",state );

        currentUserID = mAuth.getCurrentUser ().getUid ();
        RootRef.child ( "Users" ).child ( currentUserID )
                .updateChildren ( onlineStat );


    }




    private void SendUserToLoginActivity() {


        Intent loginIntent = new Intent ( AuthtestActivity.this,LoginActivity.class );
        loginIntent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity ( loginIntent );
        finish ();
    }
}