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

import static ru.kev163.trueorfalse.Questions.ArrayOfQuestions;

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
    public static final int SUM_OF_QUESTION = 300;

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

//        readFileQuestions(this, R.raw.filequestions);
        insertQuestionsInArray();

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

    public void insertQuestionsInArray(){

        if (ArrayOfQuestions == null){
            ArrayOfQuestions = new String[MenuActivity.SUM_OF_QUESTION][3];
        }

        for (int i = 0; i < SUM_OF_QUESTION; i++) {
            ArrayOfQuestions[i] = getStringArrayFromResources(i+1);
        }
    }

    public String[] getStringArrayFromResources(int givenIterator) {

        int questionId;
        int answerId;

        if (givenIterator < 10) {
            questionId = getResources().getIdentifier("_q00".concat(String.valueOf(givenIterator)), "string", getPackageName());
            answerId = getResources().getIdentifier("_a00".concat(String.valueOf(givenIterator)), "string", getPackageName());
        } else if (givenIterator < 100) {
            questionId = getResources().getIdentifier("_q0".concat(String.valueOf(givenIterator)), "string", getPackageName());
            answerId = getResources().getIdentifier("_a0".concat(String.valueOf(givenIterator)), "string", getPackageName());
        } else {
            questionId = getResources().getIdentifier("_q".concat(String.valueOf(givenIterator)), "string", getPackageName());
            answerId = getResources().getIdentifier("_a".concat(String.valueOf(givenIterator)), "string", getPackageName());
        }

        String [] resultArray;
        resultArray = new String[]{getString(questionId), getString(answerId), getTrueAnswer(givenIterator - 1)};

        return resultArray;
    }

    private String getTrueAnswer(int givenIterator){

        String result = "-";
        switch (givenIterator){
            case 0:result="+"; break;
            case 1:result="+"; break;
            case 2:result="-"; break;
            case 3:result="+"; break;
            case 4:result="+"; break;
            case 5:result="+"; break;
            case 6:result="-"; break;
            case 7:result="+"; break;
            case 8:result="+"; break;
            case 9:result="-"; break;
            case 10:result="+"; break;
            case 11:result="-"; break;
            case 12:result="+"; break;
            case 13:result="-"; break;
            case 14:result="-"; break;
            case 15:result="+"; break;
            case 16:result="+"; break;
            case 17:result="+"; break;
            case 18:result="+"; break;
            case 19:result="+"; break;
            case 21:result="+"; break;
            case 22:result="-"; break;
            case 23:result="+"; break;
            case 24:result="+"; break;
            case 25:result="-"; break;
            case 26:result="+"; break;
            case 27:result="+"; break;
            case 28:result="+"; break;
            case 29:result="-"; break;
            case 30:result="-"; break;
            case 31:result="+"; break;
            case 32:result="-"; break;
            case 33:result="+"; break;
            case 34:result="+"; break;
            case 35:result="-"; break;
            case 36:result="+"; break;
            case 37:result="+"; break;
            case 38:result="-"; break;
            case 39:result="+"; break;
            case 40:result="+"; break;
            case 41:result="+"; break;
            case 42:result="+"; break;
            case 43:result="+"; break;
            case 44:result="+"; break;
            case 45:result="+"; break;
            case 46:result="-"; break;
            case 47:result="+"; break;
            case 48:result="+"; break;
            case 49:result="-"; break;
            case 50:result="+"; break;
            case 51:result="+"; break;
            case 52:result="+"; break;
            case 53:result="+"; break;
            case 54:result="+"; break;
            case 55:result="-"; break;
            case 56:result="-"; break;
            case 57:result="-"; break;
            case 58:result="+"; break;
            case 59:result="-"; break;
            case 60:result="+"; break;
            case 61:result="+"; break;
            case 62:result="-"; break;
            case 63:result="-"; break;
            case 64:result="-"; break;
            case 65:result="-"; break;
            case 66:result="+"; break;
            case 67:result="-"; break;
            case 68:result="-"; break;
            case 69:result="-"; break;
            case 70:result="+"; break;
            case 71:result="-"; break;
            case 72:result="-"; break;
            case 73:result="-"; break;
            case 74:result="+"; break;
            case 75:result="-"; break;
            case 76:result="-"; break;
            case 77:result="-"; break;
            case 78:result="+"; break;
            case 79:result="+"; break;
            case 80:result="-"; break;
            case 81:result="-"; break;
            case 82:result="-"; break;
            case 83:result="+"; break;
            case 84:result="-"; break;
            case 85:result="+"; break;
            case 86:result="-"; break;
            case 87:result="-"; break;
            case 88:result="-"; break;
            case 89:result="-"; break;
            case 90:result="+"; break;
            case 91:result="-"; break;
            case 92:result="-"; break;
            case 93:result="-"; break;
            case 94:result="-"; break;
            case 95:result="+"; break;
            case 96:result="-"; break;
            case 97:result="+"; break;
            case 98:result="+"; break;
            case 99:result="+"; break;
            case 100:result="-"; break;
            case 101:result="+"; break;
            case 102:result="+"; break;
            case 103:result="-"; break;
            case 104:result="+"; break;
            case 105:result="+"; break;
            case 106:result="+"; break;
            case 107:result="+"; break;
            case 108:result="-"; break;
            case 109:result="+"; break;
            case 110:result="-"; break;
            case 111:result="-"; break;
            case 112:result="-"; break;
            case 113:result="+"; break;
            case 114:result="+"; break;
            case 115:result="-"; break;
            case 116:result="+"; break;
            case 117:result="-"; break;
            case 118:result="-"; break;
            case 119:result="-"; break;
            case 120:result="-"; break;
            case 121:result="-"; break;
            case 122:result="+"; break;
            case 123:result="+"; break;
            case 124:result="-"; break;
            case 125:result="+"; break;
            case 126:result="-"; break;
            case 127:result="+"; break;
            case 128:result="+"; break;
            case 129:result="-"; break;
            case 130:result="-"; break;
            case 131:result="+"; break;
            case 132:result="+"; break;
            case 133:result="+"; break;
            case 134:result="+"; break;
            case 135:result="+"; break;
            case 136:result="-"; break;
            case 137:result="+"; break;
            case 138:result="+"; break;
            case 139:result="-"; break;
            case 140:result="-"; break;
            case 141:result="+"; break;
            case 142:result="-"; break;
            case 143:result="+"; break;
            case 144:result="+"; break;
            case 145:result="+"; break;
            case 146:result="+"; break;
            case 147:result="-"; break;
            case 148:result="-"; break;
            case 149:result="+"; break;
            case 150:result="-"; break;
            case 151:result="+"; break;
            case 152:result="-"; break;
            case 153:result="-"; break;
            case 154:result="-"; break;
            case 155:result="+"; break;
            case 156:result="+"; break;
            case 157:result="+"; break;
            case 158:result="-"; break;
            case 159:result="-"; break;
            case 160:result="-"; break;
            case 161:result="-"; break;
            case 162:result="-"; break;
            case 163:result="-"; break;
            case 164:result="-"; break;
            case 165:result="-"; break;
            case 166:result="+"; break;
            case 167:result="+"; break;
            case 168:result="-"; break;
            case 169:result="+"; break;
            case 170:result="+"; break;
            case 171:result="+"; break;
            case 172:result="-"; break;
            case 173:result="+"; break;
            case 174:result="-"; break;
            case 175:result="-"; break;
            case 176:result="-"; break;
            case 177:result="+"; break;
            case 178:result="-"; break;
            case 179:result="+"; break;
            case 180:result="+"; break;
            case 181:result="-"; break;
            case 182:result="-"; break;
            case 183:result="-"; break;
            case 184:result="-"; break;
            case 185:result="-"; break;
            case 186:result="-"; break;
            case 187:result="+"; break;
            case 188:result="+"; break;
            case 189:result="+"; break;
            case 190:result="-"; break;
            case 191:result="+"; break;
            case 192:result="+"; break;
            case 193:result="+"; break;
            case 194:result="+"; break;
            case 195:result="-"; break;
            case 196:result="+"; break;
            case 197:result="+"; break;
            case 198:result="-"; break;
            case 199:result="-"; break;
            case 200:result="-"; break;
            case 201:result="-"; break;
            case 202:result="+"; break;
            case 203:result="-"; break;
            case 204:result="-"; break;
            case 205:result="-"; break;
            case 206:result="+"; break;
            case 207:result="-"; break;
            case 208:result="+"; break;
            case 209:result="+"; break;
            case 210:result="+"; break;
            case 211:result="+"; break;
            case 212:result="-"; break;
            case 213:result="+"; break;
            case 214:result="+"; break;
            case 215:result="-"; break;
            case 216:result="+"; break;
            case 217:result="-"; break;
            case 218:result="-"; break;
            case 219:result="-"; break;
            case 220:result="-"; break;
            case 221:result="+"; break;
            case 222:result="-"; break;
            case 223:result="-"; break;
            case 224:result="-"; break;
            case 225:result="+"; break;
            case 226:result="-"; break;
            case 227:result="-"; break;
            case 228:result="-"; break;
            case 229:result="-"; break;
            case 230:result="-"; break;
            case 231:result="-"; break;
            case 232:result="-"; break;
            case 233:result="-"; break;
            case 234:result="+"; break;
            case 235:result="-"; break;
            case 236:result="-"; break;
            case 237:result="-"; break;
            case 238:result="-"; break;
            case 239:result="-"; break;
            case 240:result="+"; break;
            case 241:result="-"; break;
            case 242:result="-"; break;
            case 243:result="+"; break;
            case 244:result="+"; break;
            case 245:result="+"; break;
            case 246:result="+"; break;
            case 247:result="-"; break;
            case 248:result="+"; break;
            case 249:result="-"; break;
            case 250:result="+"; break;
            case 251:result="-"; break;
            case 252:result="-"; break;
            case 253:result="-"; break;
            case 254:result="+"; break;
            case 255:result="-"; break;
            case 256:result="+"; break;
            case 257:result="+"; break;
            case 258:result="+"; break;
            case 259:result="-"; break;
            case 260:result="+"; break;
            case 261:result="-"; break;
            case 262:result="-"; break;
            case 263:result="+"; break;
            case 264:result="-"; break;
            case 265:result="-"; break;
            case 266:result="+"; break;
            case 267:result="+"; break;
            case 268:result="+"; break;
            case 269:result="-"; break;
            case 270:result="-"; break;
            case 271:result="-"; break;
            case 272:result="-"; break;
            case 273:result="-"; break;
            case 274:result="-"; break;
            case 275:result="-"; break;
            case 276:result="-"; break;
            case 277:result="-"; break;
            case 278:result="-"; break;
            case 279:result="-"; break;
            case 280:result="+"; break;
            case 281:result="-"; break;
            case 282:result="+"; break;
            case 283:result="-"; break;
            case 284:result="-"; break;
            case 285:result="-"; break;
            case 286:result="+"; break;
            case 287:result="+"; break;
            case 288:result="+"; break;
            case 289:result="-"; break;
            case 290:result="-"; break;
            case 291:result="-"; break;
            case 292:result="-"; break;
            case 293:result="-"; break;
            case 294:result="-"; break;
            case 295:result="-"; break;
            case 296:result="-"; break;
            case 297:result="-"; break;
            case 298:result="+"; break;
            case 299:result="-"; break;
            default:result="-"; break;
        }

        return result;
    }

    private void loadSettings() {

        String stringOfNumQuestions = settingsOfApp.getString(STRING_OF_NUM_QUESTIONS, "");
        String stringOfUserAnswers = settingsOfApp.getString(STRING_OF_USER_ANSWERS, "");
        int intIndexOfQuestions = settingsOfApp.getInt(INDEX_OF_LAST_QUESTIONS, -1);
        int intCountOfLives = settingsOfApp.getInt(COUNT_OF_LIVES, 5);
        int intCountOfAntiLives = settingsOfApp.getInt(COUNT_OF_LIVES, 0);

        if (!stringOfNumQuestions.isEmpty() || !stringOfUserAnswers.isEmpty() || intIndexOfQuestions == -1) {

            String[] middleArray = stringOfNumQuestions.split(",");

            if (Questions.ArrayOfNumQuestions == null) Questions.ArrayOfNumQuestions = new int[ArrayOfQuestions.length];
            for (int count = 0; count < middleArray.length; count++) {
                Questions.ArrayOfNumQuestions[count] = Integer.parseInt(middleArray[count]);
            }

            String[] middleArray2 = stringOfUserAnswers.split(",");

            if (Questions.ArrayOfUserAnswer == null) Questions.ArrayOfUserAnswer = new boolean[ArrayOfQuestions.length];
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
        Questions.fillArrayOfNumQuestions(ArrayOfQuestions.length);
    }

}
