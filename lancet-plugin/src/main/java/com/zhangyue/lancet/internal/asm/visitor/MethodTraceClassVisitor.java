package com.zhangyue.lancet.internal.asm.visitor;

import com.zhangyue.lancet.plugin.LancetContext;

import org.objectweb.asm.MethodVisitor;


public class MethodTraceClassVisitor extends BaseWeaveClassVisitor {

    String mClassName;
    public MethodTraceClassVisitor() {

    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        if (name != null) {
            mClassName = name;
        }

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        String myTrace = LancetContext.instance().extension.getMyTrace();
        if (shouldTrace(mClassName, name) && !myTrace.isEmpty()) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
            return new TraceMethodVisitor(methodVisitor, access, name, desc, this.mClassName, myTrace);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    private boolean shouldTrace(String className, String name) {
        boolean isNotConstruct = true;
        if(className.contains("MyTrace")){
            return  false;
        }
        if (!LancetContext.instance().extension.shouldTrace(mClassName,name)) {
            return false;
        }
        if (name != null) {
            if (name.contains("<clinit>") || name.contains("<init>")) {
                isNotConstruct = false;
            }
        }

        return isNotConstruct;
    }

}
