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
                loadSettings();
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

    public void loadSettings()
    {
        Set<String> ret = mySharedPreferences.getStringSet("preferencesTrueOrFalse", new HashSet<String>());
//        for(String r : ret) {
//           // Log.i("Share", "Имя кота: " + r);

        Toast.makeText(getApplicationContext(), Integer.toString(ret.size()), Toast.LENGTH_SHORT).show();
        }

}
