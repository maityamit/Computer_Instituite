package growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.exam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import growOnlearningInstituteitaberia.example.growonlearninginstitute.R;


public class ExamFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        return  view;
    }
}