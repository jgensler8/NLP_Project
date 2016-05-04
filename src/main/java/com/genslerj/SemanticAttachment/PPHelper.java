package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbJoin;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by genslerj on 5/2/16.
 */
public class PPHelper {
    public static boolean isYear(String possibleDate) {
        try {
            Year.parse(possibleDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isOscarType(String semanticText) {
        String lower = semanticText.toLowerCase();
        String[] words = new String[]{"picture", "actor", "actress", "supporting actor", "supporting actress", "director"};
        if(lower.contains("best"))
        {
            for(String best_word : Arrays.asList(words)) {
                if(lower.contains( best_word)) return true;
            }
        }
        return false;
    }

    public static String semanticTextToOscarType(String semanticText) {
        String lower = semanticText.toLowerCase();
        if(lower.contains("picture")) return "BEST-PICTURE";
        if(lower.contains("supporting")) {
            if(lower.contains("actor")) return "BEST-SUPPORTING-ACTOR";
            if(lower.contains("actress")) return "BEST-SUPPORTING-ACTRESS";
        }
        if(lower.contains("actor")) return "BEST-ACTOR";
        if(lower.contains("actress")) return "BEST-ACTRESS";
        if(lower.contains("director")) return "BEST-DIRECTOR";
        return "";
    }

    public static boolean isUpperCase(char c) {
        return c >= 65 && c <= 90;
    }
    public static boolean isProperNoun(String semanticText) {
        return isUpperCase(semanticText.charAt(0));
    }

    public static Condition getConditionFromPhrase(NPSemanticObject npSemanticObject, PPSemanticObject ppSemanticObject) {
        if(npSemanticObject.semanticQuery.toString().toLowerCase().contains("oscar")) {
            if(isYear(ppSemanticObject.semanticText)) {
                return BinaryCondition.equalTo(DatabaseResources.oscar_year, ppSemanticObject.semanticText);
            }
            if(isOscarType(ppSemanticObject.semanticText)) {
                return BinaryCondition.equalTo(DatabaseResources.oscar_type, semanticTextToOscarType(ppSemanticObject.semanticText));
            }
        }
        if(isProperNoun(ppSemanticObject.semanticText)) {
            return BinaryCondition.like(DatabaseResources.person_name, ppSemanticObject.getSemanticTextAsLikeClause());
        }
        return null;
    }

    public static List<DbJoin> getJoinsFromPhrase(NPSemanticObject npSemanticObject, PPSemanticObject ppSemanticObject) {
        ArrayList<DbJoin> toReturn = new ArrayList<>();
//        if(npSemanticObject.semanticQuery.toString().toLowerCase().contains("movie")) {
//            return null;
//        }
        if (isProperNoun(ppSemanticObject.semanticText)) {
            toReturn.add(DatabaseResources.Movie_Actor_Join);
            toReturn.add(DatabaseResources.Movie_Director_Join);
            return toReturn;
        }

        return null;
    }
}
