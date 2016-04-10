package com.sanchez.QuestionFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by genslerj on 4/10/16.
 */
public class QuestionFileReader {

    String filePath;

    public QuestionFileReader(String filePath) {
        this.filePath = filePath;
    }

    public String[] parseQuestions() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.filePath));
        ArrayList<String> questions = new ArrayList<String>();
        try {
            StringBuilder sb = new StringBuilder();
            String inputLine = br.readLine();

            while (inputLine != null) {
                questions.add(inputLine);
                inputLine = br.readLine();
            }

            String inputFile = sb.toString();
        }
        finally {
            br.close();
        }
        String[] result_array = new String[questions.size()];
        result_array = questions.toArray(result_array);
        return result_array;
    }
}

