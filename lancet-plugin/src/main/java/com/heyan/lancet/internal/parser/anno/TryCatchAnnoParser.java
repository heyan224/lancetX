package com.heyan.lancet.internal.parser.anno;

import com.heyan.lancet.internal.meta.TryCatchAnnoMeta;
import com.heyan.lancet.internal.parser.AnnoParser;
import com.heyan.lancet.internal.parser.AnnotationMeta;

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
