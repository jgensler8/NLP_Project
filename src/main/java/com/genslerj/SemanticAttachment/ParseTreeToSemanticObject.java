package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class ParseTreeToSemanticObject {

    private static SemanticLibrary semanticLibrary = new SemanticLibrary();

    private static Function getSemanticFunction(Tree t, ArrayList<SemanticObject> children_semantic_objects) throws TreebankTagNotSupportedException {
        if(semanticLibrary.treebankTagToSemanticObject.containsKey(t.value())) {
            return semanticLibrary.treebankTagToSemanticObject.get(t.value()).getCreationFunction(t, children_semantic_objects);
        }
        throw new TreebankTagNotSupportedException(String.format("Penn Treebank Tag '%s' is not supported by the SemanticObject conversion subsystem", t.value()));
    }

    private static SemanticObject parse_helper(Tree t) throws TreebankTagNotSupportedException {
        // gather the children's semantic objects
        ArrayList<SemanticObject> children_semantic_objects = new ArrayList<>(t.children().length);
        for(Tree child : t.children()) {
            children_semantic_objects.add(parse_helper(child));
        }

        // get the correct semantic function
        Function currentSemanticFunction;
        if(t.numChildren() == 0) {
//            if(semanticLibrary.actualizedWordToSemanticFunction.containsKey(t.value().toLowerCase())) {
//                return new ActualizedSemanticObject(semanticLibrary.actualizedWordToSemanticFunction.get(t.value().toLowerCase()));
//            }
//            else {
                return new ActualizedSemanticObject(t.value());
//            }
        }
        else
        {
            // find a most relevant semantic function based on this tree
            currentSemanticFunction = getSemanticFunction(t, children_semantic_objects);
            if(currentSemanticFunction == null) return null;

            // evaluate the arguments
            // we assume that the function actually does take all of the arguments specified
            Object intermediate_semantic_function = currentSemanticFunction;
            for(SemanticObject semanticObject : children_semantic_objects) {
                if(intermediate_semantic_function.getClass().getSuperclass().equals(SemanticObject.class)) {
                    break;
                }
                else {
                    // else, we have a lambda function (which really isn't a "Function" class)
                    intermediate_semantic_function = ((Function) intermediate_semantic_function).apply(semanticObject);
                }
            }
            return (SemanticObject) intermediate_semantic_function;
        }
    }

    public static SemanticObject parse(Tree t) throws TreebankTagNotSupportedException {
//        System.out.println(t.pennString());
        return parse_helper(t);
    }
}
