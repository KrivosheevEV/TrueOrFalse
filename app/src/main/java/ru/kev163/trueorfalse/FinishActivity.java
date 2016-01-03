package ru.kev163.trueorfalse;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class FinishActivity extends Activity implements View.OnClickListener {

    private static long back_pressed;
    //Intent activityFinishActivity;
    //TextView textView_Result_CountOfQuestionsResult, textView_Result_CurrentAnswers_Result, textView_Result_NotCurrentAnswers_Result, textView_Result_RatioAnswers_Result;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        if (Questions.indexOfQuestion > 0) {
            countCurrentUserAnswers = Questions.GetCountCurrentUserAnswers();
            countNotCurrentUserAnswers = Questions.indexOfQuestion - countCurrentUserAnswers;
            ratioOfAnswers = (countCurrentUserAnswers * 100) / Questions.indexOfQuestion;
        } else {
            countCurrentUserAnswers = 0;
            countNotCurrentUserAnswers = 0;
            ratioOfAnswers = 0;
        }

        textView_Result_CountOfQuestionsResult.setText(Integer.toString(Questions.indexOfQuestion));
        textView_Result_CurrentAnswers_Result.setText(Integer.toString(countCurrentUserAnswers));
        textView_Result_NotCurrentAnswers_Result.setText(Integer.toString(countNotCurrentUserAnswers));
        textView_Result_RatioAnswers_Result.setText(Integer.toString(ratioOfAnswers) + "%");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Finish Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://ru.kev163.trueorfalse/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Finish Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://ru.kev163.trueorfalse/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }
}
