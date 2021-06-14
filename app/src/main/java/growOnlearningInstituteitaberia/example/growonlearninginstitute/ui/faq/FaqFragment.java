package growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.faq;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import growOnlearningInstituteitaberia.example.growonlearninginstitute.FAQModel;
import growOnlearningInstituteitaberia.example.growonlearninginstitute.GalaryModel;
import growOnlearningInstituteitaberia.example.growonlearninginstitute.R;
import growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.gallery.GalleryFragment;


public class FaqFragment extends Fragment {


    private DatabaseReference RootRef;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_faq, container, false);


        RootRef = FirebaseDatabase.getInstance ().getReference ().child ( "Institute" ).child("FAQ");
        progressDialog = new ProgressDialog( getContext());
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        progressDialog.setMessage ( "Tips: Please Check your Internet or Wi-fi Connection" );
        recyclerView = view.findViewById(R.id.FAQRecyclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


    @Override
    public void onStart() {
        super.onStart ();
        progressDialog.show();

        FirebaseRecyclerOptions<FAQModel> options =
                new FirebaseRecyclerOptions.Builder<FAQModel> ()
                        .setQuery ( RootRef,FAQModel.class )
                        .build ();


        FirebaseRecyclerAdapter<FAQModel, StudentViewHolder2> adapter =
                new FirebaseRecyclerAdapter<FAQModel, StudentViewHolder2> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolder2 holder, final int position, @NonNull final FAQModel model) {



                        holder.qn.setText(model.getQn());
                        holder.an.setText(model.getAn());

                        progressDialog.dismiss ();





                    }

                    @NonNull
                    @Override
                    public StudentViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.faq_layout,viewGroup,false );
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


        TextView qn,an;
        public StudentViewHolder2(@NonNull View itemView) {
            super ( itemView );
            qn = itemView.findViewById(R.id.faq_qn);
            an = itemView.findViewById(R.id.faq_an);


        }
    }
}