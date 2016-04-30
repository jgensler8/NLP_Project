package com.genslerj.SemanticAttachment;

/**
 * Created by genslerj on 4/29/16.
 */
public class TreebankTagNotSupportedException extends Exception {
    public TreebankTagNotSupportedException(String tag) {
        super(tag);
    }
}
