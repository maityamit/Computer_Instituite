package growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import growOnlearningInstituteitaberia.example.growonlearninginstitute.GalaryModel;
import growOnlearningInstituteitaberia.example.growonlearninginstitute.R;

public class GalleryFragment extends Fragment {



    private DatabaseReference RootRef;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( "Institute" ).child("Gallary");
        progressDialog = new ProgressDialog( getContext());
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );
        recyclerView = view.findViewById(R.id.Galary_RecyclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    @Override
    public void onStart() {
        super.onStart ();
progressDialog.show();

        FirebaseRecyclerOptions<GalaryModel> options =
                new FirebaseRecyclerOptions.Builder<GalaryModel> ()
                        .setQuery ( RootRef,GalaryModel.class )
                        .build ();


        FirebaseRecyclerAdapter<GalaryModel, StudentViewHolder2> adapter =
                new FirebaseRecyclerAdapter<GalaryModel, StudentViewHolder2> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolder2 holder, final int position, @NonNull final GalaryModel model) {


                        Picasso.get ().load ( model.getImage1() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder).into ( holder.imageView1 );

                        Picasso.get ().load ( model.getImage2() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder).into ( holder.imageView2 );


                        progressDialog.dismiss ();





                    }

                    @NonNull
                    @Override
                    public StudentViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.galary_layout,viewGroup,false );
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


        ImageView imageView1,imageView2;
        public StudentViewHolder2(@NonNull View itemView) {
            super ( itemView );
           imageView1 = itemView.findViewById(R.id.galary_image_1);
            imageView2 = itemView.findViewById(R.id.galary_image_2);


        }
    }
}