package com.heyan.lancet.internal.entity;

import com.android.ddmlib.Log;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class ReplaceInvokeInfo {
    private String targetClassType;

    private String newClassType;
    private MethodNode methodNode;

    private String extraClassType;
    public  ClassNode classNode;


    public ReplaceInvokeInfo(String classType, String newClassType, ClassNode classNode, MethodNode methodNode, String extraClassType) {
        this.targetClassType = classType;
        this.classNode = classNode;
        this.methodNode = methodNode;
        this.newClassType = newClassType;
        this.extraClassType = extraClassType;
        System.out.println("ReplaceInvokeInfo=targetClassType=" + targetClassType + ",newClassType= " + newClassType + ",methodNode=" + methodNode);
    }
    public String getTargetClassType() {
        return targetClassType;
    }

    public String getNewClassType() {
        return newClassType;
    }

    public String getExtraClassType() {
        return extraClassType;
    }

    public void setTargetClassType(String targetClassType) {
        this.targetClassType = targetClassType;
    }
}
