package com.jrumpe.surveyfirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.sdsmdg.tastytoast.TastyToast;

public class SurveysHome extends AppCompatActivity {
    Database database;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;

    private static final int TIME_LIMIT = 1500;
    private static long backPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveys_home);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);
        image10 = (ImageView) findViewById(R.id.image10);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                Intent intent = new Intent(SurveysHome.this, RetailStore.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

                Intent intent = new Intent(SurveysHome.this, ExperienceActivity.class);
                startActivity(intent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

                Intent intent = new Intent(SurveysHome.this, RetailStore.class);
                startActivity(intent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

        image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TastyToast.makeText(SurveysHome.this, "welcome, Your feedback is appreciated", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.inputip:

              //  showInputIpDialog();
                return true;

            case R.id.printpdf:
                printpdf();
                return true;

            case R.id.exitapp:
                exitApp();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void exitApp() {
        AlertDialog.Builder al = new AlertDialog.Builder(SurveysHome.this);

        al.setTitle("Exit");

        al.setMessage("Are you sure You want to Exit?");

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = al.create();
        alertDialog.show();

    }

    private void printpdf() {
        TastyToast.makeText(SurveysHome.this, "will be implemented soon ", TastyToast.LENGTH_LONG, TastyToast.CONFUSING).show();

    }

    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            TastyToast.makeText(SurveysHome.this, "Press back again to exit", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
        }
        backPressed = System.currentTimeMillis();
    }
}
