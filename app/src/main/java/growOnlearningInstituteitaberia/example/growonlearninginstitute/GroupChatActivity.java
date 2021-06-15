package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class GroupChatActivity extends AppCompatActivity {


    private String gr_image_link="";

    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private String currentUserID;
    private DatabaseReference mRef;
    private RelativeLayout relativeLayout;
    private GifImageView gifImageView;
    private ProgressDialog progressDialog;
    private EditText editText;
    private ImageView submit;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        gr_image_link = getIntent().getExtras().get("Image").toString();




        mAuth= FirebaseAuth.getInstance ();

        currentUserID = mAuth.getCurrentUser ().getUid ();



        mRef= FirebaseDatabase.getInstance ().getReference ().child("Group_Chat");





        progressDialog = new ProgressDialog(GroupChatActivity.this);
        progressDialog.setContentView ( R.layout.loading );
        relativeLayout = findViewById(R.id.sharvanmes_2);
        gifImageView = findViewById(R.id.chat_loading_gif_2);
        editText = findViewById(R.id.user_message_text_2);
        submit = findViewById(R.id.user_message_submit_2);
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );

        submit.setEnabled(false);
        submit.setVisibility(View.GONE);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                submit.setEnabled(true);
                submit.setVisibility(View.VISIBLE);


            }

            @Override
            public void afterTextChanged(Editable s) {
                submit.setEnabled(true);
                submit.setVisibility(View.VISIBLE);
            }
        });



        recyclerView = findViewById(R.id.Message_Recyler_View_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(GroupChatActivity.this));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkUploadaMessage();
            }
        });



    }

    private void OkUploadaMessage() {


        progressDialog.show();

        String saveCurrentTime,SaveCurrentData;
        Calendar calendar = Calendar.getInstance ();


        SimpleDateFormat currentdate = new SimpleDateFormat ( "dd:MM:yyyy" );
        SaveCurrentData = currentdate.format ( calendar.getTime () );
        SimpleDateFormat currenttime = new SimpleDateFormat ( "hh:mm a" );
        saveCurrentTime = currenttime.format ( calendar.getTime () );



        String Messgaes = editText.getText().toString();
        if (TextUtils.isEmpty ( Messgaes))
        {
            Toast.makeText ( GroupChatActivity.this, "Enter Something Message..", Toast.LENGTH_SHORT ).show ();
            progressDialog.dismiss();
        }
        else
        {
            String key = mRef.push().getKey();
            Map<String,Object> updatee = new HashMap<>();
            updatee.put("Text",Messgaes);
            updatee.put("Sender",currentUserID);
            updatee.put("SenderDate",SaveCurrentData+" - "+saveCurrentTime);
            updatee.put("ReceiverDate","");
            updatee.put("Image",gr_image_link);
            mRef.child(key).updateChildren(updatee).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        editText.setText(null);
                        Toast.makeText(GroupChatActivity.this,"Sent..", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(GroupChatActivity.this, "Error ! Send Again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }




    @Override
    public void onStart() {
        super.onStart();



        relativeLayout.setVisibility(View.GONE);
        gifImageView.setVisibility(View.VISIBLE);



        FirebaseRecyclerOptions<Contact> optionss =
                new FirebaseRecyclerOptions.Builder<Contact> ()
                        .setQuery(mRef,Contact.class)
                        .build ();


        FirebaseRecyclerAdapter<Contact, StudentViewHolderP> adapter =
                new FirebaseRecyclerAdapter<Contact, StudentViewHolderP> (optionss) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolderP holder, final int position, @NonNull final Contact model) {
                        mAuth= FirebaseAuth.getInstance ();
                        currentUserID = mAuth.getCurrentUser ().getUid ();

                        recyclerView.smoothScrollToPosition(getItemCount());

                        String cheakstring = model.getSender();



                        if (cheakstring.equals(currentUserID))
                        {
                            holder.receive.setVisibility(View.GONE);
                            holder.imageView.setVisibility(View.GONE);
                            holder.r_date.setVisibility(View.GONE);
                            holder.send.setText(model.getText());
                            holder.s_date.setText(model.getSenderDate());
                        }
                        else {

                            Picasso.get ().load ( model.getImage() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder).into ( holder.imageView );

                            holder.send.setVisibility(View.GONE);
                            holder.s_date.setVisibility(View.GONE);
                            holder.receive.setText(model.getText());
                            holder.r_date.setText(model.getSenderDate());
                        }





                    }

                    @NonNull
                    @Override
                    public StudentViewHolderP onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.custom_message_layout,viewGroup,false );
                        StudentViewHolderP viewHolder  = new StudentViewHolderP(  view);
                        return viewHolder;

                    }

                    @Override
                    public void onDataChanged() {
                        super.onDataChanged();
                        relativeLayout.setVisibility(View.VISIBLE);
                        gifImageView.setVisibility(View.GONE);
                    }
                };

        recyclerView.setAdapter ( adapter );
        adapter.startListening ();






    }

    public static class StudentViewHolderP extends  RecyclerView.ViewHolder
    {

        TextView send,receive,s_date,r_date;
        ImageView imageView;
        public StudentViewHolderP(@NonNull View itemView) {
            super ( itemView );
            send = itemView.findViewById ( R.id.sender_message_text );
            receive = itemView.findViewById ( R.id.recever_message_text);
            imageView = itemView.findViewById(R.id.messege_profle_images);
            s_date = itemView.findViewById(R.id.sender_message_time);
            r_date = itemView.findViewById(R.id.recever_message_time);

        }
    }

}