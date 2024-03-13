package com.heyan.lancet.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * weave 插桩 功能组 及其开关
 */
public class WeaveGroup {

    private String name;

    public WeaveGroup(String name) {
        this.name = name;
    }

    private List<String> blackNames = new ArrayList<>();

    private List<String> whiteNames = new ArrayList<>();

    public List<String> getBlackNames() {
        return blackNames;
    }

    public void setBlackNames(List<String> blackNames) {
        System.out.println("ReplaceInvokeInfo=setBlackNames=" + blackNames);
        System.out.println("ReplaceInvokeInfo=whiteNamessssssss=" + whiteNames);
        this.blackNames = blackNames;
    }

    public List<String> getWhiteNames() {
        return whiteNames;
    }

    public void setWhiteNames(List<String> whiteNames) {
        System.out.println("ReplaceInvokeInfo=whiteNamess23ss=" + whiteNames);
        this.whiteNames = whiteNames;
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
