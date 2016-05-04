package com.sanchez.QuestionPrinter;

/**
 * Created by genslerj on 4/10/16.
 */
public class QuestionPrinter {
    public static String getPart1Output(String question, String category, String parseTree) {
        return String.format("<QUESTION> %s\n<CATEGORY> %s\n<PARSETREE> %s\n", question, category, parseTree);
    }

    public static String getPart2Output(String question, String query, String answer) {
        return String.format("<QUESTION> %s\n<QUERY> %s\n<ANSWER> %s\n", question, query, answer);
    }
}
