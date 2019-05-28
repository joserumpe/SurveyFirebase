package com.jrumpe.surveyfirebase;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jrumpe.surveyfirebase.Common.Common;
import com.jrumpe.surveyfirebase.model.Experience;
import com.jrumpe.surveyfirebase.model.Retail;
import com.sdsmdg.tastytoast.TastyToast;


public class RetailStore extends AppCompatActivity {

    private static final int TIME_LIMIT = 1500;
    private static long backPressed;


    TextView tvdearCustomer, tvIntroduction, tvSincerely, tvGeneralManager;

    TextView tvQuizOne, tvQuizTwo, tvQuizThree, tvQuizFour;

    /*the questions */
    TextView tvOneLocated, tvTwoAppr, tvThreeSelection, tvFourAppealing;

    /*the radio group Buttons*/
    RadioGroup rgOneLocated, rgTwoAppr, rgThreeSelection, rgFourAppealing;

    Button btnSubmitData;

    TextView tvOneAns, tvTwoAns, tvThreeAns, tvFourAns;

    ScrollView rootLayout;

    TextView tvtCommon;

FirebaseAuth mAuth;

    DatabaseReference table_retail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_store);




        btnSubmitData = (Button) findViewById(R.id.btnSubmit);

        rootLayout = (ScrollView) findViewById(R.id.rootLayout);

        /*Components of the first cardview layout*/

        tvdearCustomer = (TextView) findViewById(R.id.tvDearCustomer);

        tvIntroduction = (TextView) findViewById(R.id.tvIntroduction);

        tvSincerely = (TextView) findViewById(R.id.tvsincerely);

        tvGeneralManager = (TextView) findViewById(R.id.tvGeneralManager);

        /*Question headings*/

        tvQuizOne = (TextView) findViewById(R.id.tvQuizOne);

        tvQuizTwo = (TextView) findViewById(R.id.tvQuizTwo);

        tvQuizThree = (TextView) findViewById(R.id.tvQuizThree);

        tvQuizFour = (TextView) findViewById(R.id.tvQuizFour);

        /*the Questions*/

        tvOneLocated = (TextView) findViewById(R.id.tv_one_located);

        tvTwoAppr = (TextView) findViewById(R.id.tv_one_appr);

        tvThreeSelection = (TextView) findViewById(R.id.tv_one_selection);

        tvFourAppealing = (TextView) findViewById(R.id.tv_one_appealing);

        /*radio groups */
        rgOneLocated = (RadioGroup) findViewById(R.id.rg_one_located);

        rgTwoAppr = (RadioGroup) findViewById(R.id.rg_one_appr);

        rgThreeSelection = (RadioGroup) findViewById(R.id.rg_one_selection);

        rgFourAppealing = (RadioGroup) findViewById(R.id.rg_one_appealing);

//        /*set fonts*/
//        tvdearCustomer.setTypeface(AvFonts.RobotoRegular(MainActivity.this));
//        tvIntroduction.setTypeface(AvFonts.Comfortaa(MainActivity.this));
//        tvSincerely.setTypeface(AvFonts.RobotoLight(MainActivity.this));
//        tvGeneralManager.setTypeface(AvFonts.Comfortaa(MainActivity.this));
//
//        tvQuizOne.setTypeface(AvFonts.RobotoRegular(MainActivity.this));
//        tvQuizTwo.setTypeface(AvFonts.RobotoRegular(MainActivity.this));
//        tvQuizFour.setTypeface(AvFonts.RobotoRegular(MainActivity.this));
//        tvQuizThree.setTypeface(AvFonts.RobotoRegular(MainActivity.this));

        /*Invisible textviews*/
        tvOneAns = (TextView) findViewById(R.id.tv_one_answer);
        tvTwoAns = (TextView) findViewById(R.id.tv_two_answer);
        tvThreeAns = (TextView) findViewById(R.id.tv_Three_answer);
        tvFourAns = (TextView) findViewById(R.id.tv_four_answer);

        tvtCommon = (TextView) findViewById(R.id.tvCommon);

        mAuth= FirebaseAuth.getInstance();

        tvtCommon.setText(mAuth.getUid());

       // tvtCommon.setText(Common.Current_user.);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        table_retail = database.getReference("RetailStore");

        rgOneLocated.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = rgOneLocated.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.rb_one_located:
                        tvOneAns.setText("Very Strongly Agree");
                        break;
                    case R.id.rb_two_located:
                        tvOneAns.setText("Strongly Agree");
                        break;
                    case R.id.rb_three_located:
                        tvOneAns.setText("Agree");
                        break;
                    case R.id.rb_four_located:
                        tvOneAns.setText("Disagree");

                        break;
                    case R.id.rb_five_located:
                        tvOneAns.setText("Strongly Disagree");

                        break;
                    case R.id.rb_six_located:
                        tvOneAns.setText("Very Strongly Disagree");
                        break;
                }
            }
        });

        rgTwoAppr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = rgTwoAppr.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.rb_one_appr:
                        tvTwoAns.setText("Very Strongly Agree");
                        break;
                    case R.id.rb_two_appr:
                        tvTwoAns.setText("Strongly Agree");
                        break;
                    case R.id.rb_three_appr:
                        tvTwoAns.setText("Agree");
                        break;
                    case R.id.rb_four_appr:
                        tvTwoAns.setText("Disagree");

                        break;
                    case R.id.rb_five_appr:
                        tvTwoAns.setText("Strongly Disagree");

                        break;
                    case R.id.rb_six_appr:
                        tvTwoAns.setText("Very Strongly Disagree");
                        break;
                }
            }
        });

        rgThreeSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = rgThreeSelection.getCheckedRadioButtonId();


                switch (checkedId) {
                    case R.id.rb_one_selection:
                        tvThreeAns.setText("Very strongly Agree");
                        break;
                    case R.id.rb_two_selection:
                        tvThreeAns.setText("Strongly Agree");
                        break;
                    case R.id.rb_three_selection:
                        tvThreeAns.setText("Agree");

                        break;
                    case R.id.rb_four_selection:
                        tvThreeAns.setText("Disagree");

                        break;
                    case R.id.rb_five_selection:
                        tvThreeAns.setText("Strongly Disagree");
                        break;
                    case R.id.rb_six_selection:
                        tvThreeAns.setText("Very Strongly Disagree");
                        break;
                }
            }
        });

        rgFourAppealing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                checkedId = rgFourAppealing.getCheckedRadioButtonId();


                switch (checkedId) {
                    case R.id.rb_one_appealing:
                        tvFourAns.setText("Very Strongly Agree");
                        break;
                    case R.id.rb_two_appealing:
                        tvFourAns.setText("Strongly Agree");

                        break;
                    case R.id.rb_three_appealing:
                        tvFourAns.setText("Agree");
                        break;
                    case R.id.rb_four_appealing:
                        tvFourAns.setText("Disagree");
                        break;
                    case R.id.rb_five_appealing:
                        tvFourAns.setText("Strongly Disagree");
                        break;
                    case R.id.rb_six_appealing:
                        tvFourAns.setText("Very Strongly Disagree");
                        break;
                }
            }
        });

        btnSubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          submitSurvey();

            }
        });


    }

    private void submitSurvey() {

        final String located = tvOneAns.getText().toString().trim();
        final String appropriate = tvTwoAns.getText().toString().trim();
        final String selection = tvThreeAns.getText().toString().trim();
        final String appealing = tvFourAns.getText().toString().trim();

        final String uid = tvtCommon.getText().toString();



        if (located.isEmpty() && appropriate.isEmpty() && selection.isEmpty() && appealing.isEmpty()) {

            TastyToast.makeText(RetailStore.this, "Please choose an answer", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
            return;
        } else if (located.isEmpty()) {
            TastyToast.makeText(RetailStore.this, "Please choose an answer for question one", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else if (appropriate.isEmpty()) {
            TastyToast.makeText(RetailStore.this, "Please choose an answer for question Two", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else if (selection.isEmpty()) {
            TastyToast.makeText(RetailStore.this, "Please choose an answer for question Three", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else if (appealing.isEmpty()) {

            TastyToast.makeText(RetailStore.this, "Please choose an answer for question Four", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else {
            table_retail.addValueEventListener(new ValueEventListener() {

                ProgressDialog progressDialog = new ProgressDialog(RetailStore.this);

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(uid).exists()){

                        TastyToast.makeText(RetailStore.this, "Cannot submit Twice", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();


                    }else{

                        progressDialog.dismiss();

                        Retail retail = new Retail(located, appropriate, selection, appealing);

                        table_retail.child(uid).setValue(retail);

                        TastyToast.makeText(RetailStore.this, "Survey Submitted, Thank you", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();


                        Intent home = new Intent(RetailStore.this, SurveysHome.class);
                        startActivity(home);

                        count();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
    }

    private void count() {

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                finish();
            }
        }.start();

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
               // showInputIpDialog();
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
        AlertDialog.Builder al = new AlertDialog.Builder(RetailStore.this);

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
        TastyToast.makeText(RetailStore.this, "will be implemented soon ", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

    }


    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            TastyToast.makeText(RetailStore.this, "Click Back Again to exit", TastyToast.LENGTH_LONG, TastyToast.WARNING).show();

        }
        backPressed = System.currentTimeMillis();
    }
}

