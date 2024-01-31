package com.zhangyue.lancet.internal.parser.anno;

import com.zhangyue.lancet.internal.parser.AnnotationMeta;
import com.zhangyue.lancet.internal.parser.AcceptableAnnoParser;

import org.objectweb.asm.tree.AnnotationNode;

/**
 * Created by Knight-ZXW
 */
public class AcceptAny implements AcceptableAnnoParser {

    public static AcceptAny INSTANCE = new AcceptAny();

    private AcceptAny(){
    }

    @Override
    public boolean accept(String desc) {
        return true;
    }

    @Override
    public AnnotationMeta parseAnnotation(AnnotationNode annotationNode) {
        return new AnnotationMeta() {
        };
    }
}
