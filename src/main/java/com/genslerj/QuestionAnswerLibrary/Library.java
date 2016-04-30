package com.genslerj.QuestionAnswerLibrary;

/**
 * Created by genslerj on 4/3/16.
 */
public class Library {
    public static String ANSWER_YES = "true";
    public static String ANSWER_NO = "false";
    public static String ANSWER_UNKNOWN = "Unknown";

    public static QuestionAnswerPair questions[] = {
            // Yes/No Questions
            new QuestionAnswerPair("Is Rome the capital?",                          ANSWER_YES,         Categories.GEOGRAPHY),

            new QuestionAnswerPair("Is Rome the capital of Italy?",                 ANSWER_YES,         Categories.GEOGRAPHY),
            new QuestionAnswerPair("Is France in Europe?",                          ANSWER_YES,         Categories.GEOGRAPHY),
            new QuestionAnswerPair("Is the Pacific deeper than the Atlantic?",      ANSWER_UNKNOWN,     Categories.GEOGRAPHY),
            new QuestionAnswerPair("Did Neeson star in Shindler's List?",           ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("Did Swank win the oscar in 2000?",              ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("Is the Shining by Kubrik?",                     ANSWER_YES,         Categories.MOVIES),
            new QuestionAnswerPair("Did a French actor win the oscar in 2012?",     ANSWER_UNKNOWN, Categories.MOVIES),
            new QuestionAnswerPair("Did a movie by Spielberg with Neeson win the oscar for best film?",     ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("Did Madonna sing PapaDoNotPreach?",             ANSWER_UNKNOWN, Categories.MUSIC),
            new QuestionAnswerPair("Does the album Thriller include the track BeatIt?",                     ANSWER_YES,         Categories.MUSIC),
            new QuestionAnswerPair("Was Beyonce' born in the USA?",                 ANSWER_UNKNOWN, Categories.MUSIC),
            // Entity Questions
            new QuestionAnswerPair("Who directed Hugo?",                            ANSWER_UNKNOWN,     Categories.GEOGRAPHY),
            new QuestionAnswerPair("Which is the scary movie by Kubrik with Nicholson?", ANSWER_UNKNOWN, Categories.MOVIES),
            new QuestionAnswerPair("Who won the oscar for the best actor in 2005?", ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("Which actress won the oscar in 2012?",          ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("Who directed the best movie in 2010?",          ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("In which continent does Canada lie?",           ANSWER_UNKNOWN,     Categories.GEOGRAPHY),
            new QuestionAnswerPair("What is the capital of Spain?",                 "Madrid",           Categories.GEOGRAPHY),
            new QuestionAnswerPair("With which countries does France have a border?", ANSWER_UNKNOWN,   Categories.GEOGRAPHY),
            new QuestionAnswerPair("Which is the highest mountain in the world?",   ANSWER_UNKNOWN,     Categories.GEOGRAPHY),
            new QuestionAnswerPair("Where is the highest mountain?",                ANSWER_UNKNOWN,     Categories.GEOGRAPHY),
            new QuestionAnswerPair("Which pop artist sings CrazyInLove?",           ANSWER_UNKNOWN,     Categories.MUSIC),
            new QuestionAnswerPair("Where was Gaga born?",                          ANSWER_UNKNOWN,     Categories.MUSIC),
            new QuestionAnswerPair("In which album does Aura appear?",              ANSWER_UNKNOWN,     Categories.MUSIC),
            new QuestionAnswerPair("Which album by Swift was released in 2014?",    ANSWER_UNKNOWN,     Categories.MUSIC),
            // Custom Questions
            new QuestionAnswerPair("Daniel Craig",                                  ANSWER_UNKNOWN,     Categories.MOVIES),
            new QuestionAnswerPair("Kubrick directed Nemo?",                        ANSWER_YES,         Categories.MOVIES),
    };
}
