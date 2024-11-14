
package com.heyan.lancet.plugin;

import com.heyan.lancet.internal.log.WeaverLog;
import com.ss.android.ugc.bytex.common.BaseExtension;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;


public class LancetExtension extends BaseExtension {

    protected static LancetExtension sLancetExtension;

    public static final LancetExtension instance() {
        return sLancetExtension;
    }


    private NamedDomainObjectContainer<WeaveGroup> weaveGroup;

    private NamedDomainObjectContainer<MethodGroup> methodGroups;

    private NamedDomainObjectContainer<BlackGroup> blackGroup;

    private String myTrace;

    private String pluginInfo;

    private boolean isFullTrace = false;

    public boolean isNeedExitTrace = false;

    private List<String> systemMethod = new ArrayList<>();

    private Project project;

    public LancetExtension(Project project) {
        this.project = project;
        System.out.println("heyan=======project=====" + project.getStatus());
        //默认不可用
        enable(false);
        enableInDebug(false);
        bindProject(project);
        systemMethod.add("androidx/");
        systemMethod.add("kotlin/");
        systemMethod.add("okhttp3/");
        systemMethod.add("okio/");
        systemMethod.add("android/support");
        systemMethod.add("com/google/");

    }


    public void bindProject(Project project) {
        weaveGroup = project.getObjects()
                .domainObjectContainer(WeaveGroup.class);
        methodGroups = project.getObjects().domainObjectContainer(MethodGroup.class);
        blackGroup = project.getObjects().domainObjectContainer(BlackGroup.class);
    }


    public void weaveGroup(Action<? super NamedDomainObjectContainer<WeaveGroup>> action) {
        action.execute(weaveGroup);
    }

    public void methodGroup(Action<? super NamedDomainObjectContainer<MethodGroup>> action) {
        action.execute(methodGroups);
    }

    public void blackGroup(Action<? super NamedDomainObjectContainer<BlackGroup>> action) {
        action.execute(blackGroup);
    }
    public void myTrace(String myTrace) {
        this.myTrace = myTrace;
    }

    public void pluginInfo(String pluginInfo) {
        this.pluginInfo = pluginInfo;
    }

    public void isFullTrace(boolean isFullTrace) {
        this.isFullTrace = isFullTrace;
    }

    public void isNeedExitTrace(boolean isNeedExitTrace) {
        this.isNeedExitTrace = isNeedExitTrace;
    }

    public boolean isFullTrace() {
        return isFullTrace;
    }

    public String getMyTrace() {

        return myTrace;
    }

    public String getPluginInfo() {
        return pluginInfo;
    }


    public WeaveGroup findWeaveGroup(String group) {
        return weaveGroup.findByName(group);
    }

    public MethodGroup findMethodGroup(String group) {
        return methodGroups.findByName(group);
    }

    public boolean shouldTrace(String className, String methodName) {
        //系统方法屏蔽
        for (int i = 0; i < systemMethod.size(); i++) {
            if (className.contains(systemMethod.get(i))) {
                return false;
            }
        }

        // 黑名单判断
        for (BlackGroup black : blackGroup) {
            List<String> methodlist = black.getBlackMethodList();
            if (className.contains(black.getName())) {
                if (methodlist == null || methodlist.size() == 0) {
                    return false;
                }
                for (int i = 0; i < methodlist.size(); i++) {
                    if (methodName.contains(methodlist.get(i))) {
                        return false;
                    }
                }
            }
        }

        if (LancetContext.instance().extension.isFullTrace()) {
            return true;
        }

        for (MethodGroup methodGroup : methodGroups) {
            if (className.contains(methodGroup.getName())) {
                return true;
            }
        }

        return false;
    }


    public boolean isWeaveGroupEnable(String group){
        if (group ==null){ // 没有使用@WeaveGroup 注解，则功能开关 依赖于 全局的插件开关
            return isEnable();
        }

        WeaveGroup weaveGroup = findWeaveGroup(group);
        if (weaveGroup == null){
            // 如果 配置了@Group 注解，但是 没有配置对应的 extension,则默认将该功能关闭
            // todo  ，允许在 注解上配置默认的功能开关
            WeaverLog.i("未发现 weaver group "+group+" 的gradle 配置 ,因此功能默认关闭");
            return false;
        }
        return weaveGroup.isEnable();
    }

    @Override
    public String getName() {
        return "LancetX";
    }
}