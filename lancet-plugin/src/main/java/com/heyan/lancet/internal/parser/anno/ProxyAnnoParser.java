package com.heyan.lancet.internal.parser.anno;

import com.google.common.base.Strings;
import com.heyan.lancet.internal.parser.AnnoParser;
import com.heyan.lancet.internal.parser.AnnotationMeta;
import com.heyan.lancet.internal.exception.IllegalAnnotationException;
import com.heyan.lancet.internal.meta.ProxyAnnoMeta;

import org.objectweb.asm.tree.AnnotationNode;

import java.util.List;

/**
 * Created by Knight-ZXW
 */
public class ProxyAnnoParser implements AnnoParser {


    @SuppressWarnings("unchecked")
    @Override
    public AnnotationMeta parseAnnotation(AnnotationNode annotationNode) {
        List<Object> values;
        String targetMethod = null;
        if ((values = annotationNode.values) != null) {
            for (int i = 0; i < values.size(); i += 2) {
                switch ((String) values.get(i)) {
                    case "value":
                        targetMethod = (String) values.get(i + 1);
                        if (Strings.isNullOrEmpty(targetMethod)) {
                            throw new IllegalAnnotationException("@Proxy value can't be empty or null");
                        }

                        break;
                    default:
                        throw new IllegalAnnotationException();
                }
            }
            return new ProxyAnnoMeta(targetMethod);
        }

        throw new IllegalAnnotationException("@Proxy is illegal, must specify value field");
    }


}
