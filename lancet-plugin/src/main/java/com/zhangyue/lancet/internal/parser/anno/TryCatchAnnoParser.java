package com.zhangyue.lancet.internal.parser.anno;

import com.zhangyue.lancet.internal.meta.TryCatchAnnoMeta;
import com.zhangyue.lancet.internal.parser.AnnoParser;
import com.zhangyue.lancet.internal.parser.AnnotationMeta;

import org.objectweb.asm.tree.AnnotationNode;

/**
 * Created by Knight-ZXW
 */
public class TryCatchAnnoParser implements AnnoParser {

    @Override
    public AnnotationMeta parseAnnotation(AnnotationNode annotationNode) {
        return new TryCatchAnnoMeta();
    }

}
