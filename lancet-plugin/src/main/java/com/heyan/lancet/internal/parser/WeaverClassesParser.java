package com.heyan.lancet.internal.parser;

import com.heyan.lancet.plugin.LancetContext;
import com.heyan.lancet.internal.core.WeaverMethodParser;
import com.heyan.lancet.internal.log.WeaverLog;
import com.knightboost.lancet.api.annotations.Group;
import com.heyan.lancet.internal.util.AsmUtil;
import com.ss.android.ugc.bytex.common.graph.Graph;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Weaver类 元信息类 信息解析
 */
public class WeaverClassesParser {

    private final List<ClassNode> weaverClasses =new ArrayList<>();


    private static final String GROUP = Type.getDescriptor(Group.class);

    public Graph graph;


    public WeaverClassesParser(){
    }


    public void addWeaverClass(ClassNode classNode){
        weaverClasses.add(classNode);
    }

    public void parse(){
        for (ClassNode classNode : weaverClasses) {
            parseWeaver(classNode);
        }
    }


    @SuppressWarnings("unchecked")
    private void checkNode(ClassNode cn) {
        if (cn.fields.size() > 0) {
            String s = ((List<FieldNode>) cn.fields)
                    .stream()
                    .map(fieldNode -> fieldNode.name)
                    .collect(Collectors.joining(","));
            WeaverLog.w("shouldn't declare fields '" + s + "' in weaver class " + cn.name);
        }

    }

    public void parseWeaver(ClassNode cn) {
        checkNode(cn);
        LancetContext weaveContext = LancetContext.instance();
        String className = cn.name;

        weaveContext.getTransformInfo().addWeaverClass(className);

        for (AnnotationNode classAnnotations : cn.visibleAnnotations) {
            if (classAnnotations.desc.equals(GROUP)) {
                String value = AsmUtil.findAnnotationStringValue(classAnnotations, "value");
                if (value != null && value.length() > 0) {
                    weaveContext.registerGroupWeaverClass(className, value);
                }
            }
        }
        boolean isEnable = weaveContext.isWeaveEnable(className);
        if (!isEnable){
            return;
        }

        for (MethodNode method : cn.methods) {
            if (WeaverMethodParser.isWeaverMethodNode(method)){
                WeaverMethodParser weaverInfoParser = new WeaverMethodParser(
                        graph,cn, method);
                weaverInfoParser.parse();
            }
        }
    }


}
