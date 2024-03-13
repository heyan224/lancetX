package com.heyan.lancet.plugin;


import java.util.ArrayList;
import java.util.List;

public class BlackGroup {

    private String name;

    public BlackGroup(String name) {
        this.name = name;
    }

    private boolean enable;
    private List<String> blackMethodList = new ArrayList<>();

    public List<String> getBlackMethodList() {
        return blackMethodList;
    }

    public void setBlackMethodList(List<String> blackMethodList) {
        this.blackMethodList = blackMethodList;
    }

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
