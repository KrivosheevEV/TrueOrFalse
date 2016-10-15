package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Context;

import java.util.Random;

import static ru.kev163.trueorfalse.R.string._a001;

class Questions {

    public static String[][] ArrayOfQuestions;
    static boolean[] ArrayOfUserAnswer;
    static int[] ArrayOfNumQuestions;
    static int indexOfQuestion, countOfLives, countOfAntiLives;
    static boolean isDebuging;

    public static String GetQuestionTextByIndex(int IndexOfQuestion1) {

        if (ArrayOfQuestions == null || IndexOfQuestion1 > ArrayOfQuestions.length - 1){return "";}
        return ArrayOfQuestions[ArrayOfNumQuestions[IndexOfQuestion1]][0];
    }
    public static String GetAnswerTextByIndex(int IndexOfQuestion2) {

        if (ArrayOfQuestions == null || IndexOfQuestion2 > ArrayOfQuestions.length - 1){return "";}
        return ArrayOfQuestions[ArrayOfNumQuestions[IndexOfQuestion2]][1];
    }

    public static Boolean GetCurrentAnswerByIndex(int IndexOfQuestion3) {

        if (ArrayOfQuestions == null || IndexOfQuestion3 > ArrayOfQuestions.length - 1){return false;}
        return ArrayOfQuestions[ArrayOfNumQuestions[IndexOfQuestion3]][2].equals("+");
    }

    public static int GetCountCurrentUserAnswers() {

        int countOfCurrentUserAnswers = 0;

        if (ArrayOfUserAnswer == null){
            return countOfCurrentUserAnswers;
        }

        for (int countAnswers = 0; countAnswers < Questions.indexOfQuestion; countAnswers++) {
            if (ArrayOfUserAnswer[countAnswers] == GetCurrentAnswerByIndex(countAnswers)) {
                countOfCurrentUserAnswers++;
            }
        }
        return countOfCurrentUserAnswers;
    }

    public static void SetUserAnswer(int indexOfUserAnswer, boolean userAnswer){

        if (ArrayOfUserAnswer == null || indexOfUserAnswer > ArrayOfQuestions.length){
            ArrayOfUserAnswer = new boolean[ArrayOfQuestions.length];
        }

        ArrayOfUserAnswer[indexOfUserAnswer] = userAnswer;

        if (userAnswer != GetCurrentAnswerByIndex(indexOfUserAnswer)){
            countOfLives--;
            countOfAntiLives++;
        }

        if ((indexOfUserAnswer + 1) % 5 == 0) countOfLives = countOfLives + 1;
    }

    static void shuffleDoubleArrayOfString(String[][] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String[] a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    static void shuffleIntArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static void fillArrayOfNumQuestions(int lengthOfArray) {

        ArrayOfNumQuestions = new int[lengthOfArray];
        for (int countOfArray = 0; countOfArray < ArrayOfQuestions.length; countOfArray++) {
            ArrayOfNumQuestions[countOfArray] = countOfArray;
        }
        shuffleIntArray(ArrayOfNumQuestions);
    }


}
