package growOnlearningInstituteitaberia.example.growonlearninginstitute.ui.contact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import growOnlearningInstituteitaberia.example.growonlearninginstitute.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class ContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        Element versionElement = new Element();
        versionElement.setTitle("Version 6.2");

        return new AboutPage(getContext())
                .isRTL(false)
                .setDescription(getString(R.string.app_name))
                .addGroup(getString(R.string.app_name))
                .addGroup("Connect with us")
                .addEmail("elmehdi.sakout@gmail.com")
                .addWebsite("https://mehdisakout.com/")
                .addFacebook("the.medy")
                .addTwitter("medyo80")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addInstagram("medyo80")
                .addEmail("us@example.com", "Email")
                .addGroup(getString(R.string.app_name))
                .addItem(versionElement)
                .create();

    }
}