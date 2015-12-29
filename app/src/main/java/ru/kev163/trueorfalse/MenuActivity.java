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
    private SharedPreferences mySharedPreferences;
    private int[] ArrayOfUserAnswerFromSetting;


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

        mySharedPreferences = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
//        ArrayOfUserAnswerFromSetting = loadSettings();


        Questions.countOfQuestion = 0;
        Questions.indexOfQuestion = 0;
        if (Questions.ArrayOfUserAnswer != null){
            Arrays.fill(Questions.ArrayOfUserAnswer, false);
        }

        readFileQuestions(this, R.raw.filequestions);


        Questions.fillArrayOfNumQuestions(Questions.ArrayOfQuestions.length);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.buttonStart:
                finish();
                startActivity(activityMainActivity);
                break;
            case R.id.buttonResume:
                ArrayOfUserAnswerFromSetting = loadSettings();
                if (ArrayOfUserAnswerFromSetting.length > 0) {
                    System.arraycopy(ArrayOfUserAnswerFromSetting, 0, Questions.ArrayOfNumQuestions, 0, ArrayOfUserAnswerFromSetting.length);
                }
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

    private int[] loadSettings()
    {
        Set<String> arrayFromSettingString = mySharedPreferences.getStringSet(MainActivity.APP_PREFERENCES, new HashSet<String>());

        if (arrayFromSettingString.isEmpty()){
            return new int[0];
        }else{
            String stringOfArray = arrayFromSettingString.toString();
            stringOfArray = stringOfArray.substring(1, stringOfArray.length() - 1);
            stringOfArray = stringOfArray.replaceAll(" ", "");
            String[] arrayOfString = stringOfArray.split(",");
            int[] arrayOfResult = new int[arrayFromSettingString.size()];
            for(int count = 0; count < arrayOfString.length; count++){
                arrayOfResult[count] = Integer.parseInt(arrayOfString[count]);
            }
            return arrayOfResult;
        }
        //Toast.makeText(getApplicationContext(), Integer.toString(ret.size()), Toast.LENGTH_SHORT).show();
    }

}
