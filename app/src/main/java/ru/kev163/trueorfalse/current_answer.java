package ru.kev163.trueorfalse;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class current_answer extends Activity implements View.OnClickListener{

    Intent activityMainActivity;
    LinearLayout layoutCurrentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.current_answer);

        activityMainActivity = new Intent(this, MainActivity.class);
        layoutCurrentAnswer = (LinearLayout) findViewById(R.id.layoutCurrentAnswer);
        layoutCurrentAnswer.setOnClickListener(this);

        boolean userAnswer = Questions.ArrayOfUserAnswer[Questions.indexOfQuestion];

        Boolean CurrentAnswer = Questions.GetCurrentAnswerByIndex(Questions.indexOfQuestion);
        Boolean AnswerIsCorrect = userAnswer == CurrentAnswer;
        Questions.SetUserAnswer(Questions.indexOfQuestion, AnswerIsCorrect);

        String  TextOfAnswer = AnswerIsCorrect ? "Да, ": "Нет, ";
        TextOfAnswer = TextOfAnswer + (CurrentAnswer ? "утверждение правдиво!": "утверждение ложно.");
        TextOfAnswer = TextOfAnswer + "\n\n" + Questions.GetAnswerTextByIndex(Questions.indexOfQuestion);
        TextView textView = (TextView) findViewById(R.id.textView_CurrentAnswer);
        textView.setText(TextOfAnswer);

        int BackgroundColor = AnswerIsCorrect ? R.color.colorMy1Green : R.color.colorMy1Red;
        textView.setBackgroundColor(ContextCompat.getColor(this, BackgroundColor));

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
}
