package growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import growOnlearningInstituteitaberia.example.growonlearninginstitute.CourseDetailsActivity;
import growOnlearningInstituteitaberia.example.growonlearninginstitute.R;

public class HomeFragment extends Fragment {




    private LinearLayout course,classs,question,exam;
    private TextView my_course,my_course_details;

    private DatabaseReference UserRef,RootRef;
    private FirebaseAuth mAuth;
    private String my_Course_Name="";
    private String currentUserID;
    private ProgressDialog progressDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mAuth= FirebaseAuth.getInstance ();
        currentUserID = mAuth.getCurrentUser ().getUid ();
        UserRef= FirebaseDatabase.getInstance ().getReference ().child ( "Users");
        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( "Institute" ).child("Course");




        progressDialog = new ProgressDialog( getContext());
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );


        course = root.findViewById(R.id.my_course_button);
        classs = root.findViewById(R.id.my_class_button);
        question = root.findViewById(R.id.my_question_button);
        exam = root.findViewById(R.id.my_exam_button);


        my_course = root.findViewById(R.id.my_course_text_front);
        my_course_details = root.findViewById(R.id.my_course_details_front);


        HelloRetriveData();



        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getContext(), CourseDetailsActivity.class);
                intent1.putExtra("CR_ID",my_Course_Name);
                startActivity(intent1);

            }
        });




        return root;
    }



    private void HelloRetriveData() {

        progressDialog.show();


        UserRef.child ( currentUserID )
                .addValueEventListener ( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String retrieveUserNAme = dataSnapshot.child ( "MyCourse" ).getValue ().toString ();



                        if (TextUtils.isEmpty(retrieveUserNAme))
                        {
                            String ft = "You Have No Course";
                            String ft1 = "Sorry ! \n You Have no Course Contact with Center to Assign a Course";
                            my_course.setText(ft);
                            my_course_details.setText(ft1);
                            progressDialog.dismiss();
                            course.setEnabled(false);
                            classs.setEnabled(false);
                            question.setEnabled(false);
                            exam.setEnabled(false);

                        }
                        else{



                            my_Course_Name = retrieveUserNAme;
                            RootRef.child(retrieveUserNAme).addValueEventListener ( new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                    String stringname = dataSnapshot.child ( "Text" ).getValue ().toString ();
                                    String string_deatils = dataSnapshot.child("Running").child("NextClass").getValue().toString();

                                    my_course.setText(stringname);
                                    my_course_details.setText(string_deatils);


                                    progressDialog.dismiss();

                                    course.setEnabled(true);
                                    classs.setEnabled(true);
                                    question.setEnabled(true);
                                    exam.setEnabled(true);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                    progressDialog.dismiss();
                                }
                            } );



                        }





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        progressDialog.dismiss();
                    }
                } );
    }

}