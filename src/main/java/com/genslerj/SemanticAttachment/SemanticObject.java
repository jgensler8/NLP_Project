package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
abstract public class SemanticObject {
    String semanticText = "";
    Function semanticFunction = null;
    // Query semanticQuery = null;

    public SemanticObject(String semanticText) { this.semanticText = semanticText; }
    public SemanticObject(Function semanticFunction) { this.semanticFunction = semanticFunction; }

    public String getSemanticText() { return this.semanticText; }
}
