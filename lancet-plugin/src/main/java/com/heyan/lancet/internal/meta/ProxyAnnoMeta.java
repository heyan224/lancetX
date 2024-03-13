package com.heyan.lancet.internal.meta;

import com.heyan.lancet.internal.parser.AnnotationMeta;

public class ProxyAnnoMeta extends AnnotationMeta {

    public final String targetMethod;

    public ProxyAnnoMeta(String targetMethod) {
        this.targetMethod = targetMethod;
    }

}
