package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

import java.util.Arrays;

/**
 * Created by genslerj on 5/2/16.
 */
public class AdjectiveHelper {

    public static boolean isPlaceOfBirth(String semanticText) {
        String[] placesOfBirth = new String[]{"french", "american"};
        for(String placeOfBirth : Arrays.asList(placesOfBirth)) {
            if(semanticText.contains(placeOfBirth)) return true;
        }
        return false;
    }

    public static String semanticTextToPlaceOfBirth(String semanticText) {
        if(semanticText.contains("france") || semanticText.contains("french")) {
            return "%France%";
        }
        if(semanticText.contains("american") || semanticText.contains("america")){
            return "%United States%";
        }
        return "";
    }


    public static Condition adjectiveAndTableNameToCondition(JJSemanticObject jjSemanticObject, DbTable table) {
        if(table.equals(DatabaseResources.actorTable)) {
            if(isPlaceOfBirth(jjSemanticObject.semanticText.toLowerCase())) {
                return BinaryCondition.like(DatabaseResources.person_pob, semanticTextToPlaceOfBirth(jjSemanticObject.semanticText.toLowerCase()));
            }
        }
        return null;
    }

    public static Condition superlativeAndTableNameToCondition(JJSSemanticObject jjsSemanticObject, DbTable table) {
        if(table.equals(DatabaseResources.actorTable)) {
            if(isPlaceOfBirth(jjsSemanticObject.semanticText.toLowerCase())) {
                return BinaryCondition.like(DatabaseResources.person_pob, semanticTextToPlaceOfBirth(jjsSemanticObject.semanticText.toLowerCase()));
            }
        }
        return null;
    }
}
