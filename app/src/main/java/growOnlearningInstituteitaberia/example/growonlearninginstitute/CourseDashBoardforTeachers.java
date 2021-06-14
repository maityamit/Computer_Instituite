package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CourseDashBoardforTeachers extends AppCompatActivity {



    private EditText editText1,editText2,editText3,editText4,editText5,editText6;
    private Button button1,button2,button3,button4,button5,button6;
    private ProgressDialog progressDialog;
    private DatabaseReference RootRef;
    String cr_id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_dash_boardfor_teachers);


        cr_id = getIntent().getExtras().get("CR_ID").toString();


        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( "Institute" ).child("Course").child(cr_id);


        editText1 = findViewById(R.id.teachers_upcoming_class_edittext);
        editText2 = findViewById(R.id.teachers_qn_edittext);
        editText3 = findViewById(R.id.teachers_an_edittext);
        editText4 = findViewById(R.id.teachers_pastclass_edittext);
        editText5 = findViewById(R.id.teachers_newexaM_edittext);
        editText6 = findViewById(R.id.teachers_newexaM_past_results_editetext);


        button1 = findViewById(R.id.teachers_upcoming_class_button);
        button2 = findViewById(R.id.teachers_qn_button);
        button3 = findViewById(R.id.teachers_an_button);
        button4 = findViewById(R.id.teachers_pastclass_button);
        button5 = findViewById(R.id.teachers_newexaM_BUTTON);
        button6 = findViewById(R.id.teachers_newexaM_past_results_button);






        progressDialog = new ProgressDialog( this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = editText1.getText().toString();

                if (TextUtils.isEmpty(string)){
                    Toast.makeText(CourseDashBoardforTeachers.this, "Enter Something....", Toast.LENGTH_SHORT).show();
                }
                else {

                    Map<String,Object> updatee = new HashMap<>();
                    updatee.put("NextClass",string);
                    RootRef.child("Running").updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                editText1.setText(null);
                                Toast.makeText(CourseDashBoardforTeachers.this, "Done..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(CourseDashBoardforTeachers.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText2.getText().toString();

                if (TextUtils.isEmpty(string)){
                    Toast.makeText(CourseDashBoardforTeachers.this, "Enter Something....", Toast.LENGTH_SHORT).show();
                }
                else {

                    String key = RootRef.child("Running").child("ModelQn").child("Question").push().getKey();
                    Map<String,Object> updatee = new HashMap<>();
                    updatee.put("Question_Link",string);
                    RootRef.child("Running").child("ModelQn").child("Question").child(key).updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                editText2.setText(null);
                                Toast.makeText(CourseDashBoardforTeachers.this, "Done..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(CourseDashBoardforTeachers.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText3.getText().toString();

                if (TextUtils.isEmpty(string)){
                    Toast.makeText(CourseDashBoardforTeachers.this, "Enter Something....", Toast.LENGTH_SHORT).show();
                }
                else {

                    String key = RootRef.child("Running").child("ModelQn").child("Answer").push().getKey();
                    Map<String,Object> updatee = new HashMap<>();
                    updatee.put("Answer_Link",string);
                    RootRef.child("Running").child("ModelQn").child("Answer").child(key).updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                editText3.setText(null);
                                Toast.makeText(CourseDashBoardforTeachers.this, "Done..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(CourseDashBoardforTeachers.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText4.getText().toString();

                if (TextUtils.isEmpty(string)){
                    Toast.makeText(CourseDashBoardforTeachers.this, "Enter Something....", Toast.LENGTH_SHORT).show();
                }
                else {

                    String key = RootRef.child("Running").child("Classes").push().getKey();
                    Map<String,Object> updatee = new HashMap<>();
                    updatee.put("Class_Link",string);
                    RootRef.child("Running").child("Classes").child(key).updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                editText4.setText(null);
                                Toast.makeText(CourseDashBoardforTeachers.this, "Done..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(CourseDashBoardforTeachers.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = editText5.getText().toString();

                if (TextUtils.isEmpty(string)){
                    Toast.makeText(CourseDashBoardforTeachers.this, "Enter Something....", Toast.LENGTH_SHORT).show();
                }
                else {

                    Map<String,Object> updatee = new HashMap<>();
                    updatee.put("NextExam",string);
                    RootRef.child("Running").child("Exam").updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                editText5.setText(null);
                                Toast.makeText(CourseDashBoardforTeachers.this, "Done..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(CourseDashBoardforTeachers.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText6.getText().toString();

                if (TextUtils.isEmpty(string)){
                    Toast.makeText(CourseDashBoardforTeachers.this, "Enter Something....", Toast.LENGTH_SHORT).show();
                }
                else {

                    String key = RootRef.child("Running").child("Exam").child("PastResult").push().getKey();
                    Map<String,Object> updatee = new HashMap<>();
                    updatee.put("Past_Result",string);
                    RootRef.child("Running").child("Exam").child("PastResult").child(key).updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                editText6.setText(null);
                                Toast.makeText(CourseDashBoardforTeachers.this, "Done..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(CourseDashBoardforTeachers.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });








    }
}