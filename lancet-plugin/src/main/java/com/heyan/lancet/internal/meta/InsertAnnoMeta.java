package com.heyan.lancet.internal.meta;

import com.heyan.lancet.internal.parser.AnnotationMeta;

public class InsertAnnoMeta extends AnnotationMeta {

    public final String targetMethod;
    public final boolean mayCreateSuper;

    public InsertAnnoMeta(String targetMethod,
                          boolean mayCreateSuper) {
        this.targetMethod = targetMethod;
        this.mayCreateSuper = mayCreateSuper;
    }


}