package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeacherdashBoardsActivity extends AppCompatActivity {

    private TextView chat,upcoming,myProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherdash_boards);

        chat = findViewById(R.id.teachers_chat);

        upcoming = findViewById(R.id.teachers_upcoming_class);

        myProfile = findViewById(R.id.teachers_my_profile);


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentl = new Intent(TeacherdashBoardsActivity.this,ChatListActivity.class);
                intentl.putExtra("Users","Teachers");
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


    }
}