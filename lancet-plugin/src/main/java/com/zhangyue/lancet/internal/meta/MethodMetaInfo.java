package com.zhangyue.lancet.internal.meta;

import com.zhangyue.lancet.internal.parser.AnnotationMeta;

import org.objectweb.asm.tree.MethodNode;

import java.util.List;


public class MethodMetaInfo {

    public MethodNode sourceNode;
    public List<AnnotationMeta> metaList;

    public MethodMetaInfo(MethodNode sourceNode) {
        this.sourceNode = sourceNode;
    }
}
