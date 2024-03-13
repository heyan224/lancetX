package com.heyan.lancet.plugin;


import org.gradle.api.NamedDomainObjectContainer;

public class MethodGroup {

    private String name;

    public MethodGroup(String name) {
        this.name = name;
    }

    private boolean enable;



    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void enable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
