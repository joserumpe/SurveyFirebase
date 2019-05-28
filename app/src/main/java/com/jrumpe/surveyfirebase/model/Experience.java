package com.jrumpe.surveyfirebase.model;

public class Experience {

    String often;
    String satisfy;
    String likely;
    String recommend;


    public Experience() {
    }

    public Experience(String often, String satisfy, String likely, String recommend) {
        this.often = often;
        this.satisfy = satisfy;
        this.likely = likely;
        this.recommend = recommend;
    }

    public String getOften() {
        return often;
    }

    public void setOften(String often) {
        this.often = often;
    }

    public String getSatisfy() {
        return satisfy;
    }

    public void setSatisfy(String satisfy) {
        this.satisfy = satisfy;
    }

    public String getLikely() {
        return likely;
    }

    public void setLikely(String likely) {
        this.likely = likely;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
