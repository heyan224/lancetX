package com.heyan.lancet.internal.asm.visitor;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OkHttpClientBuilder extends BaseWeaveClassVisitor{

    String mClassName;

    public OkHttpClientBuilder() {

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
        MethodVisitor methodVisitor;

        if (name == "<init>" && "(Lokhttp3/OkHttpClient$Builder;)V".equals(desc)) {
            // 修改OkHttpClient带参构造方法
            methodVisitor = super.visitMethod(ACC_PUBLIC, name, desc, signature, exceptions);
        } else {
            methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        }


        System.out.println("heyan visitMethod=" + mClassName+", name="+name+"  desc="+desc+", signature="+signature+",access="+access);


        if(mClassName.contains("okhttp3/OkHttpClient")){
            if(methodVisitor!=null){
                if ("build".equals(name) && "()Lokhttp3/OkHttpClient;".equals(desc)){
                    return new BuildMethodVisitor(methodVisitor);
                }
            }
        }

        return methodVisitor;
    }


    private class BuildMethodVisitor extends MethodVisitor {
        public BuildMethodVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM5, methodVisitor);
        }

        @Override
        public void visitCode() {
            // 完全替换原build方法
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "com/zy/okone/OkHttpHooker", "hookBuildOkHttpClient", "(Lokhttp3/OkHttpClient$Builder;)Lokhttp3/OkHttpClient;", false);
            mv.visitInsn(ARETURN);
        }

    }

}
