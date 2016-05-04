package com.sanchez.QuestionPrinter;

import com.genslerj.QuestionAnswerLibrary.Categories;
import org.junit.Test;

/**
 * Created by genslerj on 4/10/16.
 */
public class QuestionPrinterTest {

    String question = "Is France in Europe?";
    String category = Categories.GEOGRAPHY;
    String parseTree = "NNP";

    @Test
    public void testQuestionPrinterShouldPrintQuestion() {
        QuestionPrinter printer = new QuestionPrinter();

        String output = QuestionPrinter.getPart1Output(question, category, parseTree);

        assert(output.contains(question));
    }

    @Test
    public void testQuestionPrinterShouldPrintCategory() {
        QuestionPrinter printer = new QuestionPrinter();

        String output = QuestionPrinter.getPart1Output(question, category, parseTree);

        assert(output.contains(category));
    }

    @Test
    public void testQuestionPrinterShouldPrintParseTree() {
        QuestionPrinter printer = new QuestionPrinter();

        String output = QuestionPrinter.getPart1Output(question, category, parseTree);

        assert(output.contains(parseTree));
    }
}