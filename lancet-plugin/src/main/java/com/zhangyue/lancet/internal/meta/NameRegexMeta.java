package com.zhangyue.lancet.internal.meta;

import com.zhangyue.lancet.internal.parser.AnnotationMeta;

public class NameRegexMeta extends AnnotationMeta {

    public String regex;
    public boolean revert;

    public NameRegexMeta(String regex,
                         boolean revert) {
        this.regex = regex;
        this.revert = revert;
    }

}
