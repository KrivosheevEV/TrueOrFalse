package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

//import java.util.HashSet;

public class MenuActivity extends Activity implements View.OnClickListener {

    private Intent activityMainActivity;
    public static SharedPreferences settingsOfApp;
    public static final String SETTINGS_OF_APP = "settingsOfApp";
    public static final String STRING_OF_NUM_QUESTIONS = "settingsStringNumOfQuestions";
    public static final String INDEX_OF_LAST_QUESTIONS = "settingsIntIndexOfLastQuestions";
    public static final String STRING_OF_USER_ANSWERS = "settingsStringOfUserAnswers";
    public static final String COUNT_OF_LIVES = "settingsIntCountOfLives";
    public static final String COUNT_OF_ANTILIVES = "settingsIntCountOfAntiLives";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        activityMainActivity = new Intent(this, MainActivity.class);

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        Button buttonResume = (Button) findViewById(R.id.buttonResume);
        Button buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonStart.setOnClickListener(this);
        buttonResume.setOnClickListener(this);
        buttonExit.setOnClickListener(this);

        settingsOfApp = getSharedPreferences(SETTINGS_OF_APP, Context.MODE_PRIVATE);

        readFileQuestions(this, R.raw.filequestions);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.buttonStart:
                resetSettings();
                finish();
                startActivity(activityMainActivity);
                break;
            case R.id.buttonResume:
                loadSettings();
                finish();
                startActivity(activityMainActivity);
                break;
            case R.id.buttonExit:
                finish();
                System.exit(0);
                break;
        }
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

    private void loadSettings() {

        String stringOfNumQuestions = settingsOfApp.getString(STRING_OF_NUM_QUESTIONS, "");
        String stringOfUserAnswers = settingsOfApp.getString(STRING_OF_USER_ANSWERS, "");
        int intIndexOfQuestions = settingsOfApp.getInt(INDEX_OF_LAST_QUESTIONS, -1);
        int intCountOfLives = settingsOfApp.getInt(COUNT_OF_LIVES, 5);
        int intCountOfAntiLives = settingsOfApp.getInt(COUNT_OF_LIVES, 0);

        if (!stringOfNumQuestions.isEmpty() || !stringOfUserAnswers.isEmpty() || intIndexOfQuestions == -1) {

            String[] middleArray = stringOfNumQuestions.split(",");

            if (Questions.ArrayOfNumQuestions == null) Questions.ArrayOfNumQuestions = new int[Questions.ArrayOfQuestions.length];
            for (int count = 0; count < middleArray.length; count++) {
                Questions.ArrayOfNumQuestions[count] = Integer.parseInt(middleArray[count]);
            }

            String[] middleArray2 = stringOfUserAnswers.split(",");

            if (Questions.ArrayOfUserAnswer == null) Questions.ArrayOfUserAnswer = new boolean[Questions.ArrayOfQuestions.length];
            for (int count = 0; count < middleArray2.length; count++) {
                Questions.ArrayOfUserAnswer[count] = Boolean.parseBoolean(middleArray2[count]);
            }

            Questions.indexOfQuestion = intIndexOfQuestions;
            Questions.countOfLives = intCountOfLives;
            Questions.countOfAntiLives = intCountOfAntiLives;

        }else{
            resetSettings();
        }
        //Toast.makeText(getApplicationContext(), Integer.toString(ret.size()), Toast.LENGTH_SHORT).show();
    }

    private void resetSettings(){

        Questions.indexOfQuestion = 0;
        Questions.countOfLives = 5;
        Questions.countOfAntiLives = 0;
        if (Questions.ArrayOfUserAnswer != null){
            Arrays.fill(Questions.ArrayOfUserAnswer, false);
        }
        if (Questions.ArrayOfNumQuestions != null){
            Arrays.fill(Questions.ArrayOfNumQuestions, 0);
        }
        Questions.fillArrayOfNumQuestions(Questions.ArrayOfQuestions.length);
    }

}
