package com.jrumpe.surveyfirebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jrumpe.surveyfirebase.Common.Common;
import com.jrumpe.surveyfirebase.model.Experience;
import com.jrumpe.surveyfirebase.model.User;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sdsmdg.tastytoast.TastyToast;


//import avfont.com.aviator.aviatorfontlib.AvFonts;

public class ExperienceActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    private static final int TIME_LIMIT = 1500;

    private static long backPressed;

    TextView txtCommon;

    TextView tvdearCustomer, tvIntroduction, tvSincerely, tvGeneralManager;

    TextView tvQuizOne, tvQuizTwo, tvQuizThree, tvQuizFour;

    /*the questions */

    TextView tvOneOften, tvTwoSatisfy, tvThreeLikely, tvFourRecommend;

    /*the radio group Buttons*/
    RadioGroup rgOneOften, rgTwoSatisfy, rgThreeLikely, rgFourRecommend;

    /*Submit button*/

    Button btnSubmitData;

    TextView tvOneAns, tvTwoAns, tvThreeAns, tvFourAns;

    RelativeLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);


        btnSubmitData = (Button) findViewById(R.id.btnSubmit);

        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

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

        tvOneOften = (TextView) findViewById(R.id.tv_one_often);

        tvTwoSatisfy = (TextView) findViewById(R.id.tv_two_satisfy);

        tvThreeLikely = (TextView) findViewById(R.id.tv_three_likely);

        tvFourRecommend = (TextView) findViewById(R.id.tv_four_recommend);

        /*radio groups */
        rgOneOften = (RadioGroup) findViewById(R.id.rg_one_often);

        rgTwoSatisfy = (RadioGroup) findViewById(R.id.rg_two_satisfy);

        rgThreeLikely = (RadioGroup) findViewById(R.id.rg_three_likely);

        rgFourRecommend = (RadioGroup) findViewById(R.id.rg_four_recommend);

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
        tvThreeAns = (TextView) findViewById(R.id.tv_three_answer);
        tvFourAns = (TextView) findViewById(R.id.tv_four_answer);
        txtCommon = (TextView) findViewById(R.id.tvCommon);

        mAuth= FirebaseAuth.getInstance();

        txtCommon.setText(mAuth.getUid());

       // txtCommon.setText(Common.Current_user.getPhone());


        rgOneOften.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = rgOneOften.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.rb_one_often:
                        tvOneAns.setText("Daily");
                        break;
                    case R.id.rb_two_often:
                        tvOneAns.setText("once a week or more");
                        break;
                    case R.id.rb_three_often:
                        tvOneAns.setText("Daily");
                        break;
                    case R.id.rb_four_often:
                        tvOneAns.setText("Daily");

                        break;
                    case R.id.rb_five_often:
                        tvOneAns.setText("Daily");

                        break;
                    case R.id.rb_six_often:
                        tvOneAns.setText("Daily");

                        break;
                }
            }
        });

        rgTwoSatisfy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = rgTwoSatisfy.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.rb_one_satisfy:
                        tvTwoAns.setText("Extremely Satisfied");

                        break;
                    case R.id.rb_two_satisfy:
                        tvTwoAns.setText("Somewhat Satisfied");
                        break;
                    case R.id.rb_three_satisfy:
                        tvTwoAns.setText("Neutral");
                        break;
                    case R.id.rb_four_satisfy:
                        tvTwoAns.setText("Somewhat Unsatisfied");
                        break;
                    case R.id.rb_five_satisfy:
                        tvTwoAns.setText("Extremely Unsatisfied");
                        break;

                }
            }
        });

        rgThreeLikely.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = rgThreeLikely.getCheckedRadioButtonId();


                switch (checkedId) {
                    case R.id.rb_one_likely:
                        tvThreeAns.setText("Definitely");
                        break;
                    case R.id.rb_two_likely:
                        tvThreeAns.setText("Probably");
                        break;
                    case R.id.rb_three_likely:
                        tvThreeAns.setText("Might or might not");

                        break;
                    case R.id.rb_four_likely:
                        tvThreeAns.setText("Probably not");

                        break;
                    case R.id.rb_five_likely:
                        tvThreeAns.setText("Definitely not");
                        break;
                    case R.id.rb_six_likely:
                        tvThreeAns.setText("Never used");
                        break;
                }
            }
        });

        rgFourRecommend.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                checkedId = rgFourRecommend.getCheckedRadioButtonId();


                switch (checkedId) {
                    case R.id.rb_one_recommend:
                        tvFourAns.setText("Definitely");
                        break;
                    case R.id.rb_two_recommend:
                        tvFourAns.setText("Probably");

                        break;
                    case R.id.rb_three_recommend:
                        tvFourAns.setText("Might or might not");
                        break;
                    case R.id.rb_four_recommend:
                        tvFourAns.setText("Probably not");
                        break;
                    case R.id.rb_five_recommend:
                        tvFourAns.setText("Definitely not");
                        break;
                    case R.id.rb_six_recommend:
                        tvFourAns.setText("N/A");
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
        final String A = tvOneAns.getText().toString();
        final String B = tvTwoAns.getText().toString();
        final String C = tvThreeAns.getText().toString();
        final String D = tvFourAns.getText().toString();

        final String uid = txtCommon.getText().toString();
        //  final String phone = txtCommon.getText().toString();

        if (A.isEmpty() && B.isEmpty() && C.isEmpty() && D.isEmpty()) {

            TastyToast.makeText(ExperienceActivity.this, "Please choose an answer", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;
        } else if (A.isEmpty()) {
            TastyToast.makeText(ExperienceActivity.this, "Please choose an answer for question one", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else if (B.isEmpty()) {
            TastyToast.makeText(ExperienceActivity.this, "Please choose an answer for question Two", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else if (C.isEmpty()) {
            TastyToast.makeText(ExperienceActivity.this, "Please choose an answer for question Three", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
            return;

        } else if (D.isEmpty()) {
            TastyToast.makeText(ExperienceActivity.this, "Please choose an answer for question Four", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

            return;

        } else {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference table_experience = database.getReference("Experience_Survey");

            final ProgressDialog progressDialog = new ProgressDialog(ExperienceActivity.this);
            progressDialog.setMessage("Submitting Survey...");
            progressDialog.show();

            table_experience.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(uid).exists()) {

                        progressDialog.dismiss();

                        TastyToast.makeText(ExperienceActivity.this, "Cannot submit Twice", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();

                    } else {

                        progressDialog.dismiss();

                        Experience experience = new Experience(A, B, C, D);

                        table_experience.child(uid).setValue(experience);

                        TastyToast.makeText(ExperienceActivity.this, "Survey Submitted, Thank you", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();

                        Intent home = new Intent(ExperienceActivity.this, SurveysHome.class);
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
                //showInputIpDialog();
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
        AlertDialog.Builder al = new AlertDialog.Builder(ExperienceActivity.this);

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
        TastyToast.makeText(ExperienceActivity.this, "will be implemented soon ", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

    }

    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            TastyToast.makeText(ExperienceActivity.this, "Click Back Again to exit", TastyToast.LENGTH_LONG, TastyToast.WARNING).show();

        }
        backPressed = System.currentTimeMillis();
    }


}
