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

public class FinishActivity extends Activity implements View.OnClickListener  {

    private static long back_pressed;
    Button buttonExit_Result;
    Intent activityFinishActivity;
    TextView textView_Result_CountOfQuestionsResult, textView_Result_CurrentAnswers_Result, textView_Result_NotCurrentAnswers_Result, textView_Result_RatioAnswers_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_finish);

        activityFinishActivity = new Intent(this, MainActivity.class);

        buttonExit_Result = (Button) findViewById(R.id.buttonExit_Result);
        buttonExit_Result.setOnClickListener(this);

        textView_Result_CountOfQuestionsResult = (TextView)findViewById(R.id.textView_Result_CountOfQuestionsResult);
        textView_Result_CurrentAnswers_Result = (TextView)findViewById(R.id.textView_Result_CurrentAnswers_Result);
        textView_Result_NotCurrentAnswers_Result = (TextView)findViewById(R.id.textView_Result_NotCurrentAnswers_Result);
        textView_Result_RatioAnswers_Result = (TextView)findViewById(R.id.textView_Result_RatioAnswers_Result);

        int countOfQuestion = Questions.countOfQuestion;
        int countCurrentUserAnswers = Questions.GetCountCurrentUserAnswers();
        int countNotCurrentUserAnswers = countOfQuestion - countCurrentUserAnswers;
        int ratioOfAnswers = (countCurrentUserAnswers * 100) / countOfQuestion;

        textView_Result_CountOfQuestionsResult.setText(Integer.toString(countOfQuestion));
        textView_Result_CurrentAnswers_Result.setText(Integer.toString(countCurrentUserAnswers));
        textView_Result_NotCurrentAnswers_Result.setText(Integer.toString(countNotCurrentUserAnswers));
        textView_Result_RatioAnswers_Result.setText(Integer.toString(ratioOfAnswers) + "%");

    }

    @Override
    public void onClick(View v) {

        Boolean userAnswer;

        switch(v.getId()) {
            case R.id.buttonExit_Result:
                finish();
                System.exit(0);
                break;
            default:
                finish();
                System.exit(0);
                break;
        }

    }

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        }

        back_pressed = System.currentTimeMillis();
    }

}
