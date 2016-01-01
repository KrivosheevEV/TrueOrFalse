package ru.kev163.trueorfalse;

import java.util.Random;

class Questions {

    public static String[][] ArrayOfQuestions;
    static boolean[] ArrayOfUserAnswer;
    static int[] ArrayOfNumQuestions;
    static int indexOfQuestion, countOfQuestion;

    public static String GetQuestionTextByIndex(int IndexOfQuestion) {

        if (ArrayOfQuestions == null || indexOfQuestion > ArrayOfQuestions.length - 1){return "";}
        return ArrayOfQuestions[ArrayOfNumQuestions[IndexOfQuestion]][0];
    }
    public static String GetAnswerTextByIndex(int IndexOfQuestion) {

        if (ArrayOfQuestions == null || indexOfQuestion > ArrayOfQuestions.length - 1){return "";}
        return ArrayOfQuestions[ArrayOfNumQuestions[IndexOfQuestion]][1];
    }

    public static Boolean GetCurrentAnswerByIndex(int IndexOfQuestion) {

        if (ArrayOfQuestions == null || indexOfQuestion > ArrayOfQuestions.length - 1){return false;}
        return ArrayOfQuestions[ArrayOfNumQuestions[IndexOfQuestion]][2].equals("+");
    }

    public static int GetCountCurrentUserAnswers() {

        int countOfCurrentUserAnswers = 0;

        if (ArrayOfUserAnswer == null){
            return countOfCurrentUserAnswers;
        }

        for (int countAnswers = 0; countAnswers < ArrayOfUserAnswer.length; countAnswers++) {
            if (ArrayOfUserAnswer[countAnswers]) {
                countOfCurrentUserAnswers++;
            }
        }
        return countOfCurrentUserAnswers;
    }

    public static void SetUserAnswer(int indexOfUserAnswer, boolean userAnswer){

        if (ArrayOfUserAnswer == null || indexOfQuestion > ArrayOfQuestions.length){
            ArrayOfUserAnswer = new boolean[ArrayOfQuestions.length];
        }

        ArrayOfUserAnswer[indexOfUserAnswer] = userAnswer;

    }

    public static void insertQuestionsInArray(String lineFromFile, int indexOfAddedQuestion){

        String arrayFromString[] = lineFromFile.split("//");

        if (ArrayOfQuestions == null){
            ArrayOfQuestions = new String[1][3];
        }

        if (ArrayOfQuestions.length <= indexOfAddedQuestion){
            try {
                String[][] copyArrayOfQuestion = ArrayOfQuestions;
                ArrayOfQuestions = new String[indexOfAddedQuestion + 1][3];
                System.arraycopy(copyArrayOfQuestion, 0, ArrayOfQuestions, 0, copyArrayOfQuestion.length);

            }catch (Exception e){
                return;
            }
        }

        ArrayOfQuestions[indexOfAddedQuestion] = arrayFromString;
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
            ArrayOfNumQuestions[countOfArray] = countOfArray + 1;
        }
        shuffleIntArray(ArrayOfNumQuestions);
    }
}
