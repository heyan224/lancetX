package com.heyan.lancet.internal.asm.visitor;
import com.heyan.lancet.plugin.LancetContext;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


public class TraceMethodVisitor extends AdviceAdapter {
    private final String className;
    private final String methodName;
    private final String myTrace;

    protected TraceMethodVisitor(MethodVisitor mv,
                                 final int access,
                                 String methodName,
                                 final String desc,
                                 String className,
                                 String myTrace) {
        super(Opcodes.ASM5, mv, access, methodName, desc);
        this.className = className;
        this.methodDesc = desc;
        this.methodName = methodName;
        this.myTrace = myTrace;
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }

    protected void onMethodEnter() {
        super.onMethodEnter();
        if (methodName != null) {
            String shortClassName = transFormClassName(className);
            mv.visitLdcInsn(shortClassName+"&"+methodName);
            mv.visitMethodInsn(INVOKESTATIC, myTrace, "beginSection", "(Ljava/lang/String;)V", false);
        }
    }

    private String transFormClassName(String originClassName) {
        String pluginInfo = LancetContext.instance().extension.getPluginInfo();
        if (pluginInfo == null || pluginInfo.isEmpty()) {
            return originClassName;
        }
        String[] strs = originClassName.split("/");
        String cutClassName = "";
        if (strs.length >= 1) {
            cutClassName = strs[strs.length - 1];
            return pluginInfo + "/" + cutClassName;
        }
        return originClassName;
    }

    protected void onMethodExit(final int opcode) {
        super.onMethodExit(opcode);
        if (!LancetContext.instance().extension.isNeedExitTrace) {
            return;
        }
        String shortClassName = transFormClassName(className);
        mv.visitLdcInsn(shortClassName + "&" + methodName);
        mv.visitMethodInsn(INVOKESTATIC, myTrace, "endSection", "(Ljava/lang/String;)V", false);
    }

}
