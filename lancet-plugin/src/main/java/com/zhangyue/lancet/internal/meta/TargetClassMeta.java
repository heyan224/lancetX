package com.zhangyue.lancet.internal.meta;

import com.knightboost.lancet.api.Scope;
import com.zhangyue.lancet.internal.parser.AnnotationMeta;

public class TargetClassMeta extends AnnotationMeta {

    public String className;

    public Scope scope;

    public TargetClassMeta(String className,
                           Scope scope) {
        this.className = className;
        this.scope = scope;
    }

}
