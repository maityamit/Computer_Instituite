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

public class ChatListActivity extends AppCompatActivity {


    private String chat_list_user;

    private DatabaseReference RootRef;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        chat_list_user = getIntent().getExtras().get("Users").toString();




        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( chat_list_user );
        progressDialog = new ProgressDialog( ChatListActivity.this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );
        recyclerView = findViewById(R.id.ChatListRecycle);



        recyclerView.setLayoutManager(new LinearLayoutManager(ChatListActivity.this));









    }



    @Override
    public void onStart() {
        super.onStart ();

        progressDialog.show();

        FirebaseRecyclerOptions<User_Chat_Model> options =
                new FirebaseRecyclerOptions.Builder<User_Chat_Model> ()
                        .setQuery ( RootRef,User_Chat_Model.class )
                        .build ();


        FirebaseRecyclerAdapter<User_Chat_Model, StudentViewHolder2> adapter =
                new FirebaseRecyclerAdapter<User_Chat_Model, StudentViewHolder2> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolder2 holder, final int position, @NonNull final User_Chat_Model model) {



                        holder.name.setText(model.getName());
                        holder.details.setText(model.getClasses());
                        Picasso.get ().load ( model.getImage() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder).into ( holder.imageView );


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String string = getRef(position).getKey();

                            }
                        });


                        progressDialog.dismiss ();





                    }

                    @NonNull
                    @Override
                    public StudentViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.chat_list_layout,viewGroup,false );
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

        TextView name,details;
        ImageView imageView;
        public StudentViewHolder2(@NonNull View itemView) {
            super ( itemView );
            name = itemView.findViewById ( R.id.chat_list_user_name );
            imageView = itemView.findViewById(R.id.chat_list_user_image);
            details = itemView.findViewById ( R.id.chat_list_user_text );



        }
    }



}