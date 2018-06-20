package com.example.jingdong_demo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 撩个小媳妇 on 2018/6/18.
 */
@Entity
public class Bean {
    public int id;
    public String a;
    public String getA() {
        return this.a;
    }
    public void setA(String a) {
        this.a = a;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Generated(hash = 1377906293)
    public Bean(int id, String a) {
        this.id = id;
        this.a = a;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }

}
