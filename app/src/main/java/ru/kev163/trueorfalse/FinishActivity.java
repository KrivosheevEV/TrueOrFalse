package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinishActivity extends Activity implements View.OnClickListener {

    private static long back_pressed;
    //Intent activityFinishActivity;
    //TextView textView_Result_CountOfQuestionsResult, textView_Result_CurrentAnswers_Result, textView_Result_NotCurrentAnswers_Result, textView_Result_RatioAnswers_Result;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int countCurrentUserAnswers, countNotCurrentUserAnswers, ratioOfAnswers;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_finish);

        Button buttonExit_Result = (Button) findViewById(R.id.buttonExit_Result);
        buttonExit_Result.setOnClickListener(this);

        TextView textView_Result_CountOfQuestionsResult = (TextView) findViewById(R.id.textView_Result_CountOfQuestionsResult);
        TextView textView_Result_CurrentAnswers_Result = (TextView) findViewById(R.id.textView_Result_CurrentAnswers_Result);
        TextView textView_Result_NotCurrentAnswers_Result = (TextView) findViewById(R.id.textView_Result_NotCurrentAnswers_Result);
        TextView textView_Result_RatioAnswers_Result = (TextView) findViewById(R.id.textView_Result_RatioAnswers_Result);
        TextView textView_Result_HeadText = (TextView) findViewById(R.id.textView_Result_HeadText);
        TextView textView_Result_BodyText = (TextView) findViewById(R.id.textView_Result_BodyText);

        if (Questions.indexOfQuestion > 0) {
            countCurrentUserAnswers = Questions.GetCountCurrentUserAnswers();
            countNotCurrentUserAnswers = Questions.indexOfQuestion - countCurrentUserAnswers;
            ratioOfAnswers = (countCurrentUserAnswers * 100) / Questions.indexOfQuestion;
        } else {
            countCurrentUserAnswers = 0;
            countNotCurrentUserAnswers = 0;
            ratioOfAnswers = 0;
        }

        if (ratioOfAnswers < 10){
            textView_Result_HeadText.setText(getString(R.string.textView_Result1_HeadText));
            textView_Result_BodyText.setText(getString(R.string.textView_Result1_BodyText));
        }else if (ratioOfAnswers >= 10 & ratioOfAnswers < 40){
            textView_Result_HeadText.setText(getString(R.string.textView_Result2_HeadText));
            textView_Result_BodyText.setText(getString(R.string.textView_Result2_BodyText));
        }else if (ratioOfAnswers >= 40 & ratioOfAnswers < 70){
            textView_Result_HeadText.setText(getString(R.string.textView_Result3_HeadText));
            textView_Result_BodyText.setText(getString(R.string.textView_Result3_BodyText));
        }else if (ratioOfAnswers >= 70 & ratioOfAnswers < 90){
            textView_Result_HeadText.setText(getString(R.string.textView_Result4_HeadText));
            textView_Result_BodyText.setText(getString(R.string.textView_Result4_BodyText));
        }else if (ratioOfAnswers >= 90){
            textView_Result_HeadText.setText(getString(R.string.textView_Result5_HeadText));
            textView_Result_BodyText.setText(getString(R.string.textView_Result5_BodyText));
        }

        textView_Result_CountOfQuestionsResult.setText(Integer.toString(Questions.indexOfQuestion));
        textView_Result_CurrentAnswers_Result.setText(Integer.toString(countCurrentUserAnswers));
        textView_Result_NotCurrentAnswers_Result.setText(Integer.toString(countNotCurrentUserAnswers));
        textView_Result_RatioAnswers_Result.setText(Integer.toString(ratioOfAnswers) + "%");

    }

    @Override
    public void onClick(View v) {

        finish();
        startActivity(new Intent(this, RateActivity.class));
        /*switch (v.getId()) {
            case R.id.buttonExit_Result:
                finish();
                System.exit(0);
                break;
            default:
                finish();
                System.exit(0);
                break;
        }*/

    }

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish();
            startActivity(new Intent(this, RateActivity.class));
        } else {
            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        }

        back_pressed = System.currentTimeMillis();
    }

}
