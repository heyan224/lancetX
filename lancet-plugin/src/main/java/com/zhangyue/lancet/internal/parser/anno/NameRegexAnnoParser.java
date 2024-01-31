package com.zhangyue.lancet.internal.parser.anno;

import com.google.common.base.Strings;
import com.zhangyue.lancet.internal.exception.IllegalAnnotationException;
import com.zhangyue.lancet.internal.meta.NameRegexMeta;
import com.zhangyue.lancet.internal.parser.AnnotationMeta;
import com.zhangyue.lancet.internal.parser.BaseAnnoParser;

import org.objectweb.asm.tree.AnnotationNode;

import java.util.List;

/**
 * Created by Knight-ZXW on 17/5/5.
 */
public class NameRegexAnnoParser extends BaseAnnoParser {

    @SuppressWarnings("unchecked")
    @Override
    public AnnotationMeta parseAnnotation(AnnotationNode annotationNode) {
        List<Object> values;
        String regex = findAnnotationStringValue(annotationNode, "value");
        if (Strings.isNullOrEmpty(regex)) {
            throw new IllegalAnnotationException("@NameRegexAnnoParser value can't be empty or null");
        }

        Boolean reverse = (Boolean) findAnnotationValue(annotationNode, "reverse");

        return new NameRegexMeta(regex, reverse != null && reverse);

    }
}
