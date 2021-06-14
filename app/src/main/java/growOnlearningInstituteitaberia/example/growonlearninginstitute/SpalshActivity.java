package growOnlearningInstituteitaberia.example.growonlearninginstitute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SpalshActivity extends AppCompatActivity {



    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);




        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SpalshActivity.this,AuthtestActivity.class);
                SpalshActivity.this.startActivity(mainIntent);
                SpalshActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);



    }


    @Override
    protected void onPause() {
        super.onPause ();


        finish ();

    }

}
