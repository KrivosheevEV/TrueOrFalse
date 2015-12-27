package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends Activity implements View.OnClickListener {

    private Intent activityCurrentAnswer, activityFinishActivity;
    //private Button buttonTrue, buttonFalse;
    //private TextView answerBar_Current, answerBar_NotCurrent, questionBar_Quantity, questionBar_Count;
    private static long back_pressed;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        activityCurrentAnswer = new Intent(this, current_answer.class);
        activityFinishActivity = new Intent(this, FinishActivity.class);

//        activityMenu = new Intent(this, MenuActivity.class);


        Button buttonTrue = (Button) findViewById(R.id.buttonTrue);
        Button buttonFalse = (Button) findViewById(R.id.buttonFalse);
        buttonTrue.setOnClickListener(this);
        buttonFalse.setOnClickListener(this);

        if (Questions.countOfQuestion >= Questions.ArrayOfQuestions.length) {
            startActivity(activityFinishActivity);
            finish();
//            Questions.countOfQuestion = 0;
//            Questions.indexOfQuestion = 0;
//            Arrays.fill(Questions.ArrayOfUserAnswer, false);
        } else {
            SetNewQuestion(Questions.indexOfQuestion);
            Questions.countOfQuestion++;
        }

        SetAnswerBar(Questions.countOfQuestion, Questions.GetCountCurrentUserAnswers());
        SetQuestionBar(Questions.countOfQuestion, Questions.ArrayOfQuestions.length);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void SetAnswerBar(int countOfQuestion, int countCurrentUserAnswers) {

        int countNotCurrentUserAnswers = countOfQuestion - countCurrentUserAnswers - 1;

        float weightCurrentActionBar = (float) (countCurrentUserAnswers);
        float weightNotCurrentActionBar = (float) (countNotCurrentUserAnswers);

        if (weightCurrentActionBar == 0 & weightNotCurrentActionBar == 0) {
            weightCurrentActionBar = 1;
            weightNotCurrentActionBar = 1;
        }

        TextView answerBar_Current = (TextView) findViewById(R.id.textViewAnswerCurrent);
        TextView  answerBar_NotCurrent = (TextView) findViewById(R.id.textViewAnswerNotCurrent);


        answerBar_Current.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightCurrentActionBar));
        answerBar_NotCurrent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightNotCurrentActionBar));

        answerBar_Current.setText(Integer.toString(countCurrentUserAnswers));
        answerBar_NotCurrent.setText(Integer.toString(countNotCurrentUserAnswers));
    }

    private void SetQuestionBar(int countOfQuestion, int quantityQuestions) {

        float weightCountQuestionsBar = (float) (countOfQuestion);
        float weightQuantityQuestionsBar = (float) (quantityQuestions - countOfQuestion);

        if (weightCountQuestionsBar == 0 & weightQuantityQuestionsBar == 0) {
            weightCountQuestionsBar = 1;
            weightQuantityQuestionsBar = 1;
        }

        TextView questionBar_Quantity = (TextView) findViewById(R.id.textViewQuestionQuantity);
        TextView questionBar_Count = (TextView) findViewById(R.id.textViewQuestionCount);

        questionBar_Count.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightCountQuestionsBar));
        questionBar_Quantity.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightQuantityQuestionsBar));

        questionBar_Count.setText(Integer.toString(countOfQuestion));
        questionBar_Quantity.setText(Integer.toString(quantityQuestions));
    }

    private void SetNewQuestion(int IndexOfNewQuestion) {

        String TextOfNewQuestion = Questions.GetQuestionTextByIndex(IndexOfNewQuestion);
        TextView textView = (TextView) findViewById(R.id.textViewQuestionText);
        textView.setText(TextOfNewQuestion);
    }

    @Override
    public void onClick(View v) {

        Boolean userAnswer;

        switch (v.getId()) {
            case R.id.buttonTrue:
                userAnswer = true;
                break;
            case R.id.buttonFalse:
                userAnswer = false;
                break;
            default:
                userAnswer = false;
                break;
        }

        Questions.SetUserAnswer(Questions.indexOfQuestion, userAnswer);

        startActivity(activityCurrentAnswer);
        finish();
    }

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            startActivity(activityFinishActivity);
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Нажмите еще раз для завершения теста", Toast.LENGTH_SHORT).show();
        }

        back_pressed = System.currentTimeMillis();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ru.kev163.trueorfalse/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ru.kev163.trueorfalse/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
