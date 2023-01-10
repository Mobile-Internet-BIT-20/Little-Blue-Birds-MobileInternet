package com.example.demo.entity;

public class vuce {

    private String vu;
    private String ce;

    public String getVu() {
        return vu;
    }

    public String getCe() {
        return ce;
    }

    public void setVu(String vu) {
        this.vu = vu;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public vuce() {}
    public vuce(String vu, String ce) {

        this.vu = vu;
        this.ce = ce;
    }

    @Override
    public String toString() {
        return "vuce{" +
                "vu='" + vu + '\'' +
                ", ce='" + ce + '\'' +
                '}';
    }
}
