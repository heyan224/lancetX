package com.heyan.lancet.internal.parser.anno;

import com.heyan.lancet.internal.parser.AcceptableAnnoParser;
import com.heyan.lancet.internal.parser.AnnoParser;
import com.heyan.lancet.internal.parser.AnnotationMeta;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;

import java.lang.annotation.Annotation;

/**
 * Created by Knight-ZXW
 */
public class DelegateAcceptableAnnoParser implements AcceptableAnnoParser {

    private final String desc;
    private final AnnoParser parser;

    public DelegateAcceptableAnnoParser(String desc, AnnoParser parser) {

        this.desc = desc;
        this.parser = parser;
    }

    public DelegateAcceptableAnnoParser(Class<?extends Annotation> annotation,AnnoParser parser){
        this.desc = Type.getDescriptor(annotation);
        this.parser =parser;
    }

    @Override
    public boolean accept(String desc) {
        return this.desc.equals(desc);
    }

    @Override
    public AnnotationMeta parseAnnotation(AnnotationNode annotationNode) {
        return parser.parseAnnotation(annotationNode);
    }
}
