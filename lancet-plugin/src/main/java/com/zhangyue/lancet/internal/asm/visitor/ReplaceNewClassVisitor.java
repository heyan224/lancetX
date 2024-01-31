package com.zhangyue.lancet.internal.asm.visitor;

import com.zhangyue.lancet.internal.entity.ReplaceInvokeInfo;
import com.zhangyue.lancet.internal.entity.TransformInfo;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class ReplaceNewClassVisitor extends BaseWeaveClassVisitor{
    private final List<ReplaceInvokeInfo> replaceInvokes;
    private String thread_factory = "java/util/concurrent/ThreadFactory";
    String className;
    boolean isThreadFactory;
    public ReplaceNewClassVisitor(TransformInfo transformInfo){
        replaceInvokes = transformInfo.replaceInvokes;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        if (interfaces != null) {
            for (String anInterface : interfaces) {
                if (anInterface.contains(thread_factory)) {
                    isThreadFactory = true;
                    break;
                }
            }
        }

        className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {

        if (className == null || className.isEmpty()) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        if (isThreadFactory) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        if (className.contains("com/weaver")) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new ReplaceNewInstructionVisitor(methodVisitor);
    }


    class ReplaceNewInstructionVisitor  extends MethodVisitor{

        private ReplaceInvokeInfo replaceInvokeInfo = null;
        public ReplaceNewInstructionVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM5, methodVisitor);

        }

        @Override
        public void visitTypeInsn(int opcode, String type) {
            if (opcode == Opcodes.NEW){
                for (ReplaceInvokeInfo invokeInfo : replaceInvokes) {
                    if (invokeInfo.getTargetClassType().equals(type)){
                        String newType = invokeInfo.getNewClassType();
                        replaceInvokeInfo = invokeInfo;
                        System.out.println("ReplaceInvokeInfo=type=" + type + ",newType= " + newType);

                        type = newType;
                        break;
                    }
                }
            }
            super.visitTypeInsn(opcode, type);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            if (replaceInvokeInfo != null && "<init>".equals(name) && owner.equals(replaceInvokeInfo.getTargetClassType())) {
                owner = replaceInvokeInfo.getNewClassType();
            }

            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
}
