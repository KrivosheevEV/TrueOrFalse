package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class current_answer extends Activity implements View.OnClickListener {

    private Intent activityMainActivity;
    //private LinearLayout layoutCurrentAnswer;
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
        setContentView(R.layout.current_answer);

        activityMainActivity = new Intent(this, MainActivity.class);
        LinearLayout layoutCurrentAnswer = (LinearLayout) findViewById(R.id.layoutCurrentAnswer);
        layoutCurrentAnswer.setOnClickListener(this);

        boolean userAnswer = Questions.ArrayOfUserAnswer[Questions.indexOfQuestion];

        Boolean CurrentAnswer = Questions.GetCurrentAnswerByIndex(Questions.indexOfQuestion);
        Boolean AnswerIsCorrect = userAnswer == CurrentAnswer;
        Questions.SetUserAnswer(Questions.indexOfQuestion, AnswerIsCorrect);

        String TextOfAnswer = AnswerIsCorrect ? "Да, " : "Нет, ";
        TextOfAnswer = TextOfAnswer + (CurrentAnswer ? "утверждение правдиво!" : "утверждение ложно.");
        TextOfAnswer = TextOfAnswer + "\n\n" + Questions.GetAnswerTextByIndex(Questions.indexOfQuestion);
        TextView textView = (TextView) findViewById(R.id.textView_CurrentAnswer);
        textView.setText(TextOfAnswer);

        int BackgroundColor = AnswerIsCorrect ? R.color.colorMy1Green : R.color.colorMy1Red;
        textView.setBackgroundColor(ContextCompat.getColor(this, BackgroundColor));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {

        Questions.indexOfQuestion++;
        startActivity(activityMainActivity);
        finish();
    }

    @Override
    public void onBackPressed() {

        Questions.indexOfQuestion++;
        startActivity(activityMainActivity);
        finish();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "current_answer Page", // TODO: Define a title for the content shown.
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
                "current_answer Page", // TODO: Define a title for the content shown.
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
