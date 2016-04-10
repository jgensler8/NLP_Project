package com.sanchez.QuestionPrinter;

/**
 * Created by genslerj on 4/10/16.
 */
public class QuestionPrinter {
    public static String getQuestionOutput(String question, String category, String parseTree) {
        return String.format("<QUESTION> %s\n<CATEGORY> %s\n<PARSETREE> %s\n", question, category, parseTree);
    }
}
