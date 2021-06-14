package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.course.CourseFragment;

public class CourseListActivityforTeachersActivity extends AppCompatActivity {


    private DatabaseReference RootRef;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_activityfor_teachers);


        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( "Institute" ).child("Course");
        progressDialog = new ProgressDialog( this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );
        recyclerView = findViewById(R.id.Course_List_ForTeachers);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onStart() {
        super.onStart ();

        progressDialog.show();

        FirebaseRecyclerOptions<CourseModel> options =
                new FirebaseRecyclerOptions.Builder<CourseModel> ()
                        .setQuery ( RootRef,CourseModel.class )
                        .build ();


        FirebaseRecyclerAdapter<CourseModel, StudentViewHolder2> adapter =
                new FirebaseRecyclerAdapter<CourseModel, StudentViewHolder2> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolder2 holder, final int position, @NonNull final CourseModel model) {



                        holder.name.setText(model.getText());
                        holder.price.setText("\uD83D\uDCB0 "+model.getPrice());
                        holder.duration.setText("⌛ "+model.getDuration());
                        Picasso.get ().load ( model.getImage() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder).into ( holder.imageView );


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String string = getRef(position).getKey();
                                Intent intent1 = new Intent(CourseListActivityforTeachersActivity.this,CourseDashBoardforTeachers.class);
                                intent1.putExtra("CR_ID",string);
                                startActivity(intent1);

                            }
                        });


                        progressDialog.dismiss ();





                    }

                    @NonNull
                    @Override
                    public StudentViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.courses_layout,viewGroup,false );
                      StudentViewHolder2 viewHolder  = new StudentViewHolder2(  view);
                        return viewHolder;

                    }

                    @Override
                    public void onDataChanged() {
                        super.onDataChanged();


                    }
                };
        recyclerView.setAdapter ( adapter );
        adapter.startListening ();




    }


    public static class StudentViewHolder2 extends  RecyclerView.ViewHolder
    {

        TextView name,price,duration;
        ImageView imageView;
        public StudentViewHolder2(@NonNull View itemView) {
            super ( itemView );
            name = itemView.findViewById ( R.id.course_name );
            imageView = itemView.findViewById(R.id.course_image);
            price = itemView.findViewById ( R.id.course_price );
            duration = itemView.findViewById(R.id.course_duration);


        }
    }
}