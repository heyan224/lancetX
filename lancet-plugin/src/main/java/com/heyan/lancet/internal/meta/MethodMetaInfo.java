package com.heyan.lancet.internal.meta;

import com.heyan.lancet.internal.parser.AnnotationMeta;

import org.objectweb.asm.tree.MethodNode;

import java.util.List;


public class MethodMetaInfo {

    public MethodNode sourceNode;
    public List<AnnotationMeta> metaList;

    public MethodMetaInfo(MethodNode sourceNode) {
        this.sourceNode = sourceNode;
    }
}
