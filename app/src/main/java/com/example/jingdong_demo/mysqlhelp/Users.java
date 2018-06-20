package com.example.jingdong_demo.mysqlhelp;

public class Users {
    private String a;
    private String aa;
    private String aaa;
    private String aaaa;

    public Users(String a) {
        this.a = a;
    }

    public Users(String a, String aa, String aaa, String aaaa) {
        this.a = a;
        this.aa = aa;
        this.aaa = aaa;
        this.aaaa = aaaa;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public String getAaaa() {
        return aaaa;
    }

    public void setAaaa(String aaaa) {
        this.aaaa = aaaa;
    }
}