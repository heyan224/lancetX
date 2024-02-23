package com.zhangyue.lancet.internal.asm.visitor;

import com.zhangyue.lancet.internal.entity.ReplaceInvokeInfo;
import com.zhangyue.lancet.internal.entity.TransformInfo;
import com.zhangyue.lancet.plugin.LancetContext;
import com.zhangyue.lancet.plugin.WeaveGroup;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class ThreadFactoryVisitor extends BaseWeaveClassVisitor{
    private final List<ReplaceInvokeInfo> replaceInvokes;
    private String thread_factory = "java/util/concurrent/ThreadFactory";
    String className;
    boolean isThreadFactory;
    public ThreadFactoryVisitor(TransformInfo transformInfo){
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

        if (!isThreadFactory) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        if (className.contains("com/weaver")) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new ReplaceThreadOfThreadFactoryVisitor(methodVisitor);
    }


    class ReplaceThreadOfThreadFactoryVisitor  extends MethodVisitor{

        private ReplaceInvokeInfo replaceInvokeInfo = null;
        public ReplaceThreadOfThreadFactoryVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM7, methodVisitor);

        }

        @Override
        public void visitTypeInsn(int opcode, String type) {
            if (opcode == Opcodes.NEW){
                for (ReplaceInvokeInfo invokeInfo : replaceInvokes) {
                    if (invokeInfo.getTargetClassType().equals(type) && shouldReplace(className,invokeInfo)){
                        String newType = invokeInfo.getExtraClassType();
                        replaceInvokeInfo = invokeInfo;
                        System.out.println("ReplaceInvokeInfo=type=" + type + ",newType= " + newType+",className==="+className);

                        type = newType;
                        break;
                    }
                }
            }
            super.visitTypeInsn(opcode, type);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            if (replaceInvokeInfo != null && "<init>".equals(name) && owner.equals(replaceInvokeInfo.getTargetClassType()) && shouldReplace(className,replaceInvokeInfo)) {
                owner = replaceInvokeInfo.getExtraClassType();
            }

            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }

    private boolean shouldReplace(String className, ReplaceInvokeInfo invokeInfo) {
        if (invokeInfo == null) {
            return false;
        }
        //java/lang/Thread
        if (!invokeInfo.getTargetClassType().contains("java/lang/Thread")) {
            return false;
        }

        String replaceInvokeClass = invokeInfo.classNode.name;
        String weaverGroupName = LancetContext.instance().getWeaverGroupName(replaceInvokeClass);

        WeaveGroup group = LancetContext.instance().extension.findWeaveGroup(weaverGroupName);
        if (group == null) {
            return true;
        }

        List<String> blackNames = group.getBlackNames();
        if (blackNames != null && blackNames.size() > 0) {
            for (String blackName : blackNames) {
                if (className.contains(blackName)) {
                    return false;
                }
            }

        }

        List<String> whiteNames = group.getWhiteNames();

        if (whiteNames != null && whiteNames.size() > 0) {
            for (String whiteName : whiteNames) {
                if (className.contains(whiteName)) {
                    return true;
                }
            }
        }

        // 这里的逻辑不知道如何处理了，如果没有白名单 和黑名单命中，应该如何呢
        // 这里暂且先制定一个规则吧，如果有白名单，则一律按照白名单来，如果没有白名单，走到这里一律通过
        if (whiteNames == null || whiteNames.size() == 0) {
            return true;
        } else {
            return false;
        }

    }
}
