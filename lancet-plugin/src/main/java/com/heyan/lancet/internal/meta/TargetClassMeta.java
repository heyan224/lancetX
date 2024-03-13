package com.heyan.lancet.internal.meta;

import com.heyan.lancet.internal.parser.AnnotationMeta;
import com.knightboost.lancet.api.Scope;

public class TargetClassMeta extends AnnotationMeta {

    public String className;

    public Scope scope;

    public TargetClassMeta(String className,
                           Scope scope) {
        this.className = className;
        this.scope = scope;
    }

}
