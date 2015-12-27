package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MenuActivity extends Activity implements View.OnClickListener {

    private Intent activityMainActivity;
    //Button buttonStart, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        activityMainActivity = new Intent(this, MainActivity.class);

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        Button buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonStart.setOnClickListener(this);
        buttonExit.setOnClickListener(this);

        Questions.countOfQuestion = 0;
        Questions.indexOfQuestion = 0;
        if (Questions.ArrayOfUserAnswer != null){
            Arrays.fill(Questions.ArrayOfUserAnswer, false);
        }

        readFileQuestions(this, R.raw.filequestions);
        Questions.fillArraOfNumQuestions();
        Questions.shuffleArray(Questions.ArrayOfQuestions);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.buttonStart:
                startActivity(activityMainActivity);
                break;
            case R.id.buttonExit:
                finish();
                System.exit(0);
                break;
        }
        finish();

    }

    private void readFileQuestions(Context context, Integer resourceID) {

        InputStream inputStream = context.getResources().openRawResource(resourceID);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String lineFromFile;
        int countOfLines = 0;

        try {
            while (( lineFromFile = buffReader.readLine()) != null) {
                if (lineFromFile.isEmpty()){
                    return;
                }else {
                    Questions.insertQuestionsInArray(lineFromFile, countOfLines++);
                }
            }
        } catch (IOException e) {
            //return null;
        }
    }
}
