package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import org.junit.Test;

/**
 * Created by genslerj on 4/10/16.
 */
public class MLEStrategy_NN_NNP__Critical_CountsTest {

    DatabaseWordNetResult geography = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
            .setRelatedStrings(new String[]{"person", "Paris", "Spain"})
            .setCategory(Categories.GEOGRAPHY)
            .build();

    DatabaseWordNetResult music = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
            .setRelatedStrings(new String[]{"person", "Paris", "Beyonce"})
            .setCategory(Categories.MUSIC)
            .build();

    DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{geography, music};

    @Test
    public void testIsCriticalWordReturnsTrueWhenInOneCorpora() {
        MLEStrategy_NN_NNP_Critical_Counts s = new MLEStrategy_NN_NNP_Critical_Counts();

        Boolean isCriticalWord = s.isCriticalWord("Beyonce", corpus);

        assert(isCriticalWord.equals(true));
    }

    @Test
    public void testIsCriticalWordReturnsFalseWhenInAllCorpora() {
        MLEStrategy_NN_NNP_Critical_Counts s = new MLEStrategy_NN_NNP_Critical_Counts();

        Boolean isCriticalWord = s.isCriticalWord("Paris", corpus);

        assert(isCriticalWord.equals(false));
    }
}