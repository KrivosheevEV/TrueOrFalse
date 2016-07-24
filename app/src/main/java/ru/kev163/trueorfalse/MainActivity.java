package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.NonSkippableVideoCallbacks;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends Activity implements View.OnClickListener {

    private static long back_pressed;
    private SharedPreferences settingsArrayOfNumQuestions, settingsIndexOfLastQuiestions, settingsCountOfLastQuestions, settingsArrayOfUserAnswers;
    Handler h;
    Boolean bannerAppodealIsShowed;

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

        Button buttonTrue = (Button) findViewById(R.id.buttonTrue);
        Button buttonFalse = (Button) findViewById(R.id.buttonFalse);
        buttonTrue.setOnClickListener(this);
        buttonFalse.setOnClickListener(this);

        Questions.isDebuging = false;    // set "false" in release.

        // Реклама.
        Appodeal.disableLocationPermissionCheck();
        Appodeal.setTesting(Questions.isDebuging);

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {

                String appKey = "4104827fe461278c982e57e7438fda0de8618d6c521912db";
                Appodeal.initialize(MainActivity.this, appKey, Appodeal.NON_SKIPPABLE_VIDEO);
                Appodeal.initialize(MainActivity.this, appKey, Appodeal.INTERSTITIAL);
                Appodeal.initialize(MainActivity.this, appKey, Appodeal.BANNER);

            }
        };
        h.sendEmptyMessage(0);

        bannerAppodealIsShowed = false;
        showBannerAppodeal(false);
        ///

        if (Questions.countOfLives <= 0 || Questions.indexOfQuestion >= Questions.ArrayOfQuestions.length) {
            finishThisActivity();
        } else {
            SetNewQuestion(Questions.indexOfQuestion);
        }

        SetAnswerBar(Questions.indexOfQuestion + 1, Questions.GetCountCurrentUserAnswers());
        SetQuestionBar(Questions.indexOfQuestion + 1, Questions.ArrayOfQuestions.length);
        SetLivesBar(Questions.countOfLives, Questions.countOfAntiLives);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    private void showBannerAppodeal(boolean bannerIsShowed) {
        if (Appodeal.isLoaded(Appodeal.BANNER) & !bannerIsShowed) {
            Appodeal.show(this, Appodeal.BANNER_BOTTOM);
            bannerAppodealIsShowed = true;
        }
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

    private void SetLivesBar(int givenCountOfLives, int givenCountOfAntiLives) {

        float weightLivesBar = (float) (givenCountOfLives);
        float weightAntiLivesBar = (float) (givenCountOfAntiLives);

        TextView textViewLives = (TextView) findViewById(R.id.textViewLives);
        TextView textViewAntiLives = (TextView) findViewById(R.id.textViewAntiLives);

        textViewLives.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightLivesBar));
        textViewAntiLives.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightAntiLivesBar));

        textViewLives.setText(Integer.toString(givenCountOfLives));
        //textViewAntiLives.setText(Integer.toString(givenCountOfAntiLives));
    }

    private void SetNewQuestion(int IndexOfNewQuestion) {

        String TextOfNewQuestion = Questions.GetQuestionTextByIndex(IndexOfNewQuestion);
        TextView textView = (TextView) findViewById(R.id.textViewQuestionText);
        textView.setText(TextOfNewQuestion);
    }

    public void saveSettings(){

        String stringOfNumQuestions = "";
        for (int countOfArray = 0; countOfArray < Questions.ArrayOfNumQuestions.length; countOfArray++) {
            stringOfNumQuestions = stringOfNumQuestions + Integer.toString(Questions.ArrayOfNumQuestions[countOfArray]) + ((countOfArray != Questions.ArrayOfNumQuestions.length - 1) ? "," : "");
        }

        String stringOfUserAnswers = "";
        for (int countOfArray = 0; countOfArray <= Questions.indexOfQuestion ; countOfArray++) {
            if(Questions.indexOfQuestion < Questions.ArrayOfUserAnswer.length) {
                stringOfUserAnswers = stringOfUserAnswers + Boolean.toString(Questions.ArrayOfUserAnswer[countOfArray]) + ((countOfArray != Questions.indexOfQuestion || Questions.indexOfQuestion < Questions.ArrayOfUserAnswer.length) ? "," : "");
            }
        }

        SharedPreferences.Editor e1 = MenuActivity.settingsOfApp.edit();
        e1.putString(MenuActivity.STRING_OF_NUM_QUESTIONS, stringOfNumQuestions);
        e1.putString(MenuActivity.STRING_OF_USER_ANSWERS, stringOfUserAnswers);
        e1.putInt(MenuActivity.INDEX_OF_LAST_QUESTIONS, Questions.indexOfQuestion);
        e1.putInt(MenuActivity.COUNT_OF_LIVES, Questions.countOfLives);
        e1.putInt(MenuActivity.COUNT_OF_ANTILIVES, Questions.countOfAntiLives);
        e1.apply();

        //Toast.makeText(getApplicationContext(), Integer.toString(preferencesNumOfQuestions.size()), Toast.LENGTH_SHORT).show();
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

        showBannerAppodeal(bannerAppodealIsShowed);

        Questions.SetUserAnswer(Questions.indexOfQuestion, userAnswer);

//        Toast.makeText(getApplicationContext(), Boolean.toString(userAnswer), Toast.LENGTH_SHORT).show();

        saveSettings();
        startActivity(new Intent(this, current_answer.class));
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Questions.indexOfQuestion >= Questions.ArrayOfQuestions.length || Questions.countOfLives <= 0) {
            finishThisActivity();
        } else {
            SetNewQuestion(Questions.indexOfQuestion);
        }

        SetAnswerBar(Questions.indexOfQuestion + 1, Questions.GetCountCurrentUserAnswers());
        SetQuestionBar(Questions.indexOfQuestion + 1, Questions.ArrayOfQuestions.length);
        SetLivesBar(Questions.countOfLives, Questions.countOfAntiLives);

        showBannerAppodeal(bannerAppodealIsShowed);
    }

    private void finishThisActivity() {

        if (Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)) {
            Appodeal.show(MainActivity.this, Appodeal.NON_SKIPPABLE_VIDEO);
        }else if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
            Appodeal.show(MainActivity.this, Appodeal.INTERSTITIAL);
        }else{
            finish();
            startActivity(new Intent(this, FinishActivity.class));
        }

        Appodeal.setNonSkippableVideoCallbacks(new NonSkippableVideoCallbacks() {
            private Toast mToast;

            @Override
            public void onNonSkippableVideoLoaded() {
                if (Questions.isDebuging) showToast("onNonSkippableLoaded");
            }

            @Override
            public void onNonSkippableVideoFailedToLoad() {
                if (Questions.isDebuging) showToast("onNonSkippableFailedToLoad");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onNonSkippableVideoShown() {
                if (Questions.isDebuging) showToast("onNonSkippableShown");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onNonSkippableVideoFinished() {
                if (Questions.isDebuging) showToast("onNonSkippableClicked");

            }

            @Override
            public void onNonSkippableVideoClosed(boolean b) {
                if (Questions.isDebuging) showToast("onNonSkippableClosed");
                finish();
                startActivity(new Intent(MainActivity.this, FinishActivity.class));
            }

            void showToast(final String text) {
                if (mToast == null) {
                    mToast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
                }
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            private Toast mToast;

            @Override
            public void onInterstitialLoaded(boolean isPrecache)  {
                if (Questions.isDebuging) showToast("onInterstitialLoaded");
            }

            @Override
            public void onInterstitialFailedToLoad() {
                if (Questions.isDebuging) showToast("onInterstitialFailedToLoad");

            }

            @Override
            public void onInterstitialShown() {
                if (Questions.isDebuging) showToast("onInterstitialShown");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onInterstitialClicked() {
                if (Questions.isDebuging) showToast("onInterstitialClicked");
                //startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onInterstitialClosed() {
                if (Questions.isDebuging) showToast("onInterstitialClosed");
                finish();
                startActivity(new Intent(MainActivity.this, FinishActivity.class));
            }

            void showToast(final String text) {
                if (mToast == null) {
                    mToast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
                }
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            saveSettings();
            finishThisActivity();
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        saveSettings();
    }


}
