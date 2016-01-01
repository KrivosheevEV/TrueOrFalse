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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MenuActivity extends Activity implements View.OnClickListener {

    private Intent activityMainActivity;
    private SharedPreferences settingArrayOfNumQuestions, settingsIndexOfLastQuiestions;
    public static Set<String> preferencesNumOfQuestions;

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

        createArrayForSaveSettings();
        settingArrayOfNumQuestions = getSharedPreferences(MainActivity.ARRAY_OF_NUM_QUESTIONS, Context.MODE_PRIVATE);
        settingsIndexOfLastQuiestions = getSharedPreferences(MainActivity.INDEX_OF_LAST_QUESTIONS, Context.MODE_PRIVATE);

        readFileQuestions(this, R.raw.filequestions);
        Questions.fillArrayOfNumQuestions(Questions.ArrayOfQuestions.length);
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

    private void loadSettings()
    {
        Set<String> arrayFromSettingString = settingArrayOfNumQuestions.getStringSet(MainActivity.ARRAY_OF_NUM_QUESTIONS, new HashSet<String>());

        if (!arrayFromSettingString.isEmpty()) {
            String[] middleArray = {};
            middleArray = arrayFromSettingString.toArray(new String[arrayFromSettingString.size()]);

            int[] arrayOfResult = new int[middleArray.length];
            for (int count = 0; count < middleArray.length; count++) {
                arrayOfResult[count] = Integer.parseInt(middleArray[count]);
            }

            if (arrayOfResult.length > 0) {
                System.arraycopy(arrayOfResult, 0, Questions.ArrayOfNumQuestions, 0, arrayOfResult.length);
            }

            Questions.indexOfQuestion = settingsIndexOfLastQuiestions.getInt(MainActivity.INDEX_OF_LAST_QUESTIONS, 0);

        }else{
            resetSettings();
        }
        //Toast.makeText(getApplicationContext(), Integer.toString(ret.size()), Toast.LENGTH_SHORT).show();
    }

    private void resetSettings(){

        Questions.countOfQuestion = 0;
        Questions.indexOfQuestion = 0;
        if (Questions.ArrayOfUserAnswer != null){
            Arrays.fill(Questions.ArrayOfUserAnswer, false);
        }
    }

    private void createArrayForSaveSettings(){

        preferencesNumOfQuestions = new HashSet<>();

    }
}
