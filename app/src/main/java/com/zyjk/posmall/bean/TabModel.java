package com.zyjk.posmall.bean;

import java.io.Serializable;

/**
 * Created by Sword God on 2018/9/14.
 * 订单切换
 */

public class TabModel implements Serializable {
    public String id;
    public String title;
    public int img;

    public TabModel(String id, String title, int img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }
}
