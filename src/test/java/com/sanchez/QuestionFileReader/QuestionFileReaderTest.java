package com.sanchez.QuestionFileReader;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/10/16.
 */
public class QuestionFileReaderTest {

    String filePath = "src/main/resources/questions.txt";

    @Test
    public void testWhenOpenFileCanOpenFile() {
        QuestionFileReader reader = new QuestionFileReader(filePath);

        try {
            String[] questions = reader.parseQuestions();
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testWhenOpenFileCanReadFirstQuestion() throws IOException {
        QuestionFileReader reader = new QuestionFileReader(filePath);

        String[] questions = reader.parseQuestions();

        assert(Arrays.asList(questions).get(0).contains("Is Rome the capital of Italy?"));
    }

    @Test
    public void testWhenOpenFileCanReadSecondQuestion() throws IOException {
        QuestionFileReader reader = new QuestionFileReader(filePath);

        String[] questions = reader.parseQuestions();

        assert(Arrays.asList(questions).get(1).contains("Is France in Europe?"));
    }
}