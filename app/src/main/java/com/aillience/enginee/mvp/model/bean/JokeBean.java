package com.aillience.enginee.mvp.model.bean;

import java.util.List;
import java.util.jar.JarEntry;

/**
 * Happy every day.
 * Created by yfl on 2017/9/28 0028
 * explain: 笑话
 */

public class JokeBean {
    private List<JarEntry> JSON;

    public List<JarEntry> getJSON() {
        return JSON;
    }

    public void setJSON(List<JarEntry> JSON) {
        this.JSON = JSON;
    }
}
