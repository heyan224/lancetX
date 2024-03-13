package com.heyan.lancet.internal.parser.anno;

import com.heyan.lancet.internal.parser.AnnotationMeta;
import com.heyan.lancet.internal.parser.BaseAnnoParser;
import com.heyan.lancet.internal.meta.ReplaceAnnoMeta;

import org.objectweb.asm.tree.AnnotationNode;


/**
 * Created by Knight-ZXW
 */
public class ReplaceAnnoParser extends BaseAnnoParser {

    private static final String TARGET_METHOD_NAME = "targetMethodName";
    private static final String TARGET_METHOD_DESC = "targetMethodDesc";
    private static final String REPLACE_CLASS = "replaceClassName";
    private static final String REPLACE_METHOD_NAME = "replaceMethodName";
    private static final String REPLACE_METHOD_DESC = "replaceMethodDesc";
    private static final String TARGET_METHOD_IS_STATIC = "isStatic";

    @SuppressWarnings("unchecked")
    @Override
    public AnnotationMeta parseAnnotation(AnnotationNode annotationNode) {
        return new ReplaceAnnoMeta(
                findAnnotationStringValue(annotationNode, TARGET_METHOD_NAME),
                findAnnotationStringValue(annotationNode, TARGET_METHOD_DESC),
                findAnnotationStringValue(annotationNode, REPLACE_CLASS),
                findAnnotationStringValue(annotationNode, REPLACE_METHOD_NAME),
                findAnnotationStringValue(annotationNode, REPLACE_METHOD_DESC),
                findAnnotationBoolValue(annotationNode, TARGET_METHOD_IS_STATIC,false)
        );
    }


}
